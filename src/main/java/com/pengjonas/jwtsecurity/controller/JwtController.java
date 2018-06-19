/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengjonas.jwtsecurity.controller;

import com.pengjonas.jwtsecurity.security.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Peng
 */
@RestController
@RequestMapping("/token")
public class JwtController {

    @PostMapping
    public String getToken(@RequestBody final JwtUser jwtUser) {

//        Claims claims = Jwts.claims()
//                .setSubject(jwtUser.getName());
//        claims.put("id", String.valueOf(jwtUser.getId()));
//        claims.put("role", jwtUser.getRole());
//        
        String token = Jwts.builder()
                .setSubject(jwtUser.getName())
                .signWith(SignatureAlgorithm.HS512, "pengjonas")
                .compact();
//        String s = Jwts.builder()
//                .setClaims(claims).signWith(SignatureAlgorithm.HS512, "pengjonas".getBytes()).compact();
//        Jwts.parser().setSigningKey("pengjonas".getBytes()).parseClaimsJwt(s).getBody();
        return token;

    }
}
