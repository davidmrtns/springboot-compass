package com.davidmrtns.springbootcompass.controller;

import com.davidmrtns.springbootcompass.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(Authentication authentication) {
        return authService.authenticate(authentication);
    }
}

