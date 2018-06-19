/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengjonas.jwtsecurity.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author Peng
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String jwtToken;

    public JwtAuthenticationToken(String username, String password) {
        super(username, password);
    }

    
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
    
    
    @Override
    public Object getPrincipal() {
        return super.getPrincipal(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getCredentials() {
        return super.getCredentials(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
