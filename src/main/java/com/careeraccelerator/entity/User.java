package com.careeraccelerator.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    private String phone;

    private String profession;

    @Column(name = "current_role")
    private String currentRole;

    @Column(length = 2000)
    private String skills;

    private String location;

    private String linkedin;

    private String github;

    private String portfolio;

    @Lob
    @Column(name = "profile_photo", columnDefinition = "LONGTEXT")
    private String profilePhoto;

    private String experience;

    private String education;

    private String college;

    private String website;

    @Column(name = "about_me", length = 5000)
    private String aboutMe;
}