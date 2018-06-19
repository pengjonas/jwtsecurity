/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengjonas.jwtsecurity.config;

import com.pengjonas.jwtsecurity.security.JwtAuthenticationProvider;
import com.pengjonas.jwtsecurity.security.JwtAuthenticationEntryPoint;
import com.pengjonas.jwtsecurity.security.JwtAuthenticationFilter;
import com.pengjonas.jwtsecurity.security.JwtAuthorizationFilter;
import com.pengjonas.jwtsecurity.security.JwtFailureHandler;
import com.pengjonas.jwtsecurity.security.JwtSuccessHandler;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 *
 * @author Peng
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;
    
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
        return jwtAuthenticationEntryPoint;
    }
    
    @Override
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }
    
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
//        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager());
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        jwtAuthenticationFilter.setAuthenticationFailureHandler(new JwtFailureHandler());
        return jwtAuthenticationFilter;
    }
    
    public JwtAuthorizationFilter jwtAuthorizationFilter () {
        JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationManager());
        return jwtAuthorizationFilter;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
//                .authorizeRequests().antMatchers("/api/**").authenticated()
                .and()
//                .addFilter(jwtAuthenticationFilter())
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(jwtAuthenticationFilter())
//                .addFilterAfter(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(jwtAuthorizationFilter())
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      
    }
    
    
    
}
