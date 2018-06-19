/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengjonas.jwtsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Peng
 */
@RestController
@RequestMapping("/api")
public class HelloController {
    
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    @PostMapping("/sign_up")
    public String sign_up() {
        return "sign_up";
    }
    
}
