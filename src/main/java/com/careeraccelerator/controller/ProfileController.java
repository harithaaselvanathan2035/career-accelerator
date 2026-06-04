package com.careeraccelerator.controller;

import com.careeraccelerator.dto.ProfileRequest;
import com.careeraccelerator.dto.ProfileResponse;
import com.careeraccelerator.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{email}")
    public ProfileResponse getProfile(
            @PathVariable String email) {

        return profileService.getProfile(email);
    }

    @PutMapping("/update")
    public ProfileResponse updateProfile(
            @RequestBody ProfileRequest request) {

        return profileService.updateProfile(request);
    }
}