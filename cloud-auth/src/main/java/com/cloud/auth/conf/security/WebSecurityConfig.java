package com.cloud.auth.conf.security;

import com.cloud.auth.conf.security.service.MyUserDetailsService;
import com.cloud.auth.conf.session.SessionExpiredStrategy;
import com.cloud.common.result.Result;
import com.cloud.common.result.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C),Damon
 *
 * @Description: spring security conf
 * @Author: Damon(npf)
 * @Date: 2020-06-27:10:23
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http
//                .authenticationProvider(provider())
//                .authorizeRequests()
//                .antMatchers("/resources/**").permitAll()
//                .antMatchers("/auth/login").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .successHandler(new SuccessHandler())
//                .failureHandler(new FailHandler())
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .sessionManagement()
//                .maximumSessions(30)
//                .maxSessionsPreventsLogin(true)
//                .expiredSessionStrategy(new SessionExpiredStrategy());
//        http.cors().disable();
//        http.csrf().disable();

        http.authenticationProvider(provider())
                .httpBasic()
                .authenticationEntryPoint((request, response, e) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.getWriter().write(objectMapper.writeValueAsString(Result.failure(ResultCode.FORBIDDEN, ResultCode.FORBIDDEN.getMsg())));
                })
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .failureHandler(new FailHandler())
                .successHandler(new SuccessHandler())
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, e) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.getWriter().write(objectMapper.writeValueAsString(Result.failure(ResultCode.FORBIDDEN, ResultCode.FORBIDDEN.getMsg())));
                })
                .and()
                .logout()
                .logoutSuccessHandler((request, response, e) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpStatus.OK.value());
                    response.getWriter().write(objectMapper.writeValueAsString(Result.success("logout success")));
                })
                .permitAll()
                .and()
                .sessionManagement()
                .maximumSessions(30)
                .maxSessionsPreventsLogin(true)
                .expiredSessionStrategy(new SessionExpiredStrategy());
        http.cors().disable();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "*/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) {
        try {
            authenticationManagerBuilder.inMemoryAuthentication()
                    .withUser("user").password("password").roles("USER");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("auth error ...");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider provider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

}
