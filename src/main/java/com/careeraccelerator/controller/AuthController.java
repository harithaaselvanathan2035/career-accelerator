package com.careeraccelerator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.careeraccelerator.dto.*;
import com.careeraccelerator.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse>
    register(
            @RequestBody RegisterRequest request){

        return ResponseEntity.ok(
                authService.register(
                        request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse>
    login(
            @RequestBody LoginRequest request){

        return ResponseEntity.ok(
                authService.login(
                        request));
    }
}