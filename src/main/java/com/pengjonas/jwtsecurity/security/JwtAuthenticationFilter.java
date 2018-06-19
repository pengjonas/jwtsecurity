/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengjonas.jwtsecurity.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

/**
 *
 * @author Peng
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter  {

    private AuthenticationManager authenticationManager;
     
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
    public JwtAuthenticationFilter() {
        super("/api/hello");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse response) throws AuthenticationException {
        
            String token = req.getHeader("Authorization");
            if (token == null) {
                throw new RuntimeException("Jonas said: No token");
            }
            
            JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(token);
            
            return getAuthenticationManager().authenticate(jwtAuthenticationToken);
        
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }  
    
}
