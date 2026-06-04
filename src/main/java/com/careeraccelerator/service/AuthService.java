package com.careeraccelerator.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.careeraccelerator.dto.*;
import com.careeraccelerator.entity.User;
import com.careeraccelerator.repository.UserRepository;
import com.careeraccelerator.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(
            RegisterRequest request){

        if(userRepository.existsByEmail(
                request.getEmail())){

            return new AuthResponse(
                    null,
                    "Email already exists");
        }

        User user = User.builder()
                .fullName(
                        request.getFullName())
                .email(
                        request.getEmail())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()))
                .role("USER")
                .build();

        userRepository.save(user);

        String token =
                jwtService.generateToken(
                        user.getEmail());

        return new AuthResponse(
                token,
                "Registration Success");
    }

    public AuthResponse login(
            LoginRequest request){

        User user =
                userRepository.findByEmail(
                                request.getEmail())
                        .orElse(null);

        if(user==null){

            return new AuthResponse(
                    null,
                    "User not found");
        }

        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())){

            return new AuthResponse(
                    null,
                    "Invalid Password");
        }

        String token =
                jwtService.generateToken(
                        user.getEmail());

        return new AuthResponse(
                token,
                "Login Success");
    }
}