package com.careeraccelerator.service;

import com.careeraccelerator.dto.ProfileRequest;
import com.careeraccelerator.dto.ProfileResponse;
import com.careeraccelerator.entity.User;
import com.careeraccelerator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileResponse getProfile(String email) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return ProfileResponse.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .profession(user.getProfession())
                .currentRole(user.getCurrentRole())
                .skills(user.getSkills())
                .location(user.getLocation())
                .linkedin(user.getLinkedin())
                .github(user.getGithub())
                .portfolio(user.getPortfolio())
                .profilePhoto(user.getProfilePhoto())
                .experience(user.getExperience())
                .education(user.getEducation())
                .college(user.getCollege())
                .website(user.getWebsite())
                .aboutMe(user.getAboutMe())
                .build();
    }

    public ProfileResponse updateProfile(
            ProfileRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setPhone(request.getPhone());
        user.setProfession(request.getProfession());
        user.setCurrentRole(request.getCurrentRole());
        user.setSkills(request.getSkills());
        user.setLocation(request.getLocation());
        user.setLinkedin(request.getLinkedin());
        user.setGithub(request.getGithub());
        user.setPortfolio(request.getPortfolio());
        user.setProfilePhoto(request.getProfilePhoto());

        user.setExperience(request.getExperience());
        user.setEducation(request.getEducation());
        user.setCollege(request.getCollege());
        user.setWebsite(request.getWebsite());
        user.setAboutMe(request.getAboutMe());

        userRepository.save(user);

        return getProfile(request.getEmail());
    }
}