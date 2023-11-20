package com.kelaskoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kelaskoding.dto.AuthRequest;
import com.kelaskoding.entity.UserInfo;
import com.kelaskoding.service.JwtService;
import com.kelaskoding.service.UserInfoService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addUser(@RequestBody UserInfo userInfo) {
        userInfoService.addUser(userInfo);
        return "User added successfully";
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getName());
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    @GetMapping("/validateToken")
    public String validateToken(@RequestParam("token") String token) {
        jwtService.validateToken(token);
        return "Token is valid";
    }
}
