package com.cloud.auth.conf.security;

import com.cloud.auth.conf.security.service.MyUserDetailsService;
import com.cloud.auth.conf.session.SessionExpiredStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authenticationProvider(provider())
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/auth/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .successHandler(new SuccessHandler())
                .failureHandler(new FailHandler())
                .and()
                .logout()
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
    public void configure(WebSecurity web) throws Exception {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"*/**");
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
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider provider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

}
