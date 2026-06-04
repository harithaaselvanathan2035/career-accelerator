package com.careeraccelerator.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParsedResumeResponse {

    private String fullName;
    private String email;
    private String phone;

    private String linkedin;
    private String github;
    private String location;

    private String summary;
    private String education;
    private String skills;
    private String experience;
    private String projects;
    private String certifications;
    private String activities;
}