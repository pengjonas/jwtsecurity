/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengjonas.jwtsecurity.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Peng
 */
public class JwtUser implements UserDetails {
    private String name;
    private String jwtToken;

    public JwtUser(String name ) {
        this.name = name;
    }    
    public JwtUser(String name, String jwtToken ) {
        this.name = name;
        this.jwtToken = jwtToken;
    }    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("admin");
        return grantedAuthorities; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        return ""; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUsername() {
        return this.name; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnabled() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
