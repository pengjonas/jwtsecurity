/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengjonas.jwtsecurity.security;

import io.jsonwebtoken.Jwts;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * @author Peng
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtToken = request.getHeader("Authorization");
        if (jwtToken == null) {
            chain.doFilter(request, response);
            return;
        }
        JwtAuthenticationToken jwtAuthenticationToken = null;
        try {
            String name = Jwts.parser()
                    .setSigningKey("pengjonas")
                    .parseClaimsJws(jwtToken)
                    .getBody()
                    .getSubject();
            if (name != null) {
                jwtAuthenticationToken = new JwtAuthenticationToken(name, "");
                jwtAuthenticationToken.setJwtToken(jwtToken);
            }
        } catch (Exception e) {
            System.out.println("JWT parser exception");
        }
        SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);
        chain.doFilter(request, response); //To change body of generated methods, choose Tools | Templates.
    }

}
