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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author Peng
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {

    private AuthenticationManager authenticationManager;
     
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse response) throws AuthenticationException {
        
            String name = req.getParameter("username");
            String password = req.getParameter("password");

            JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(name, password);
            
            return authenticationManager.authenticate(jwtAuthenticationToken);
        
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        
        super.successfulAuthentication(request, response, chain, authentication);
        
        String token = ((JwtUser)authentication.getPrincipal()).getJwtToken();
        response.addHeader("authorization", token);
//        chain.doFilter(request, response);
    }  
    
}
