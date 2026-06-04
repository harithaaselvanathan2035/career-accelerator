package com.careeraccelerator.service;

import org.springframework.stereotype.Service;

import com.careeraccelerator.entity.User;
import com.careeraccelerator.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Long id) {

        return userRepository
                .findById(id)
                .orElseThrow();
    }
}