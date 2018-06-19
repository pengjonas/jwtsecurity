/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengjonas.jwtsecurity.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.BadCredentialsException;
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
        if (jwtAuthenticationToken == null) return null;
        String username = (String) jwtAuthenticationToken.getPrincipal();
        JwtUser jwtUser = null;
        if (jwtAuthenticationToken.getJwtToken() != null) {
            //Authorization: token has been passed in header
            jwtUser = new JwtUser(username, jwtAuthenticationToken.getJwtToken());
            return jwtUser;
        }
        //Authetication
        String password = (String) jwtAuthenticationToken.getCredentials();
        if (username.equals("jonas") && password.equals("123")) {
            jwtUser = new JwtUser(username);

            String token = Jwts.builder()
                    .setSubject(jwtUser.getName())
                    .signWith(SignatureAlgorithm.HS512, "pengjonas")
                    .compact();
            jwtUser.setJwtToken(token);

        }
        else {
            throw new BadCredentialsException("wrong username or password");
        }

        return jwtUser;

    }

}
