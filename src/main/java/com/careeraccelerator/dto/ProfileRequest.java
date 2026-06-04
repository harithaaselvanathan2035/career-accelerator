package com.careeraccelerator.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRequest {

    private String email;
    private String phone;
    private String profession;
    private String currentRole;
    private String skills;
    private String location;
    private String linkedin;
    private String github;
    private String portfolio;
    private String profilePhoto;

    private String experience;
    private String education;
    private String college;
    private String website;
    private String aboutMe;
}