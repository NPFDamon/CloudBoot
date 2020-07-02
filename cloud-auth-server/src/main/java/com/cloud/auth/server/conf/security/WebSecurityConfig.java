package com.cloud.auth.server.conf.security;

import com.cloud.auth.server.conf.security.service.MyUserDetailsService;
import com.cloud.auth.server.conf.session.SessionExpiredStrategy;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        http.authenticationProvider(provider())
                .authorizeRequests()
                .antMatchers("/login/session-invalid","/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint((request, response, e) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.getWriter().write(objectMapper.writeValueAsString(Result.failure(ResultCode.FORBIDDEN, ResultCode.FORBIDDEN.getMsg())));
                })
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
                .invalidateHttpSession(true)//登出session失效
                .deleteCookies("JSESSIONID")//删除cookie
                .logoutSuccessHandler((request, response, e) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpStatus.OK.value());
                    response.getWriter().write(objectMapper.writeValueAsString(Result.success("logout success")));
                })
                .permitAll()
                .and()
                .sessionManagement()//session 管理
//                .invalidSessionUrl("/login/session-invalid")
                .maximumSessions(1)//最大session并发量
                .maxSessionsPreventsLogin(true)///Session达到最大有效数的时候，不再允许相同的账户登录
                .expiredSessionStrategy(new SessionExpiredStrategy())/// Session在并发下失效后的处理策略;
                .expiredUrl("/login")
        ;
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
            authenticationManagerBuilder.inMemoryAuthentication().passwordEncoder(passwordEncoder())
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
