package com.careeraccelerator.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String message;
}