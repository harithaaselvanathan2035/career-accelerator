package com.careeraccelerator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.careeraccelerator.entity.User;
import com.careeraccelerator.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User>
    getUser(@PathVariable Long id){

        return ResponseEntity.ok(
                userService.getUser(id));
    }
}