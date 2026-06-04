package com.careeraccelerator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeRequest {

    private String fullName;
    private String email;
    private String phone;
    private String linkedin;
    private String github;
    private String location;

    private String summary;
    private String education;
    private String skills;
    private String certifications;
    private String projects;
    private String activities;
}