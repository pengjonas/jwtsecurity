/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengjonas.jwtsecurity.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author Peng
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    final String secret = "pengjonas";
    
    @Override
    protected void additionalAuthenticationChecks(UserDetails ud, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected UserDetails retrieveUser(String string, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) upat;
        String jwtToken = jwtAuthenticationToken.getJwtToken();
        JwtUser jwtUser = null;
        try {
            String name = Jwts.parser()
                    .setSigningKey("pengjonas")
                    .parseClaimsJws(jwtToken)
                    .getBody()
                    .getSubject();    
            jwtUser = new JwtUser();
            jwtUser.setName(name);
            
        }
        catch (Exception e) {
            System.out.println(e);
            throw e;
        }
        return jwtUser;
               
    }
    
    
    
}
