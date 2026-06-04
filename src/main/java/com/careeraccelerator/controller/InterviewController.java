package com.careeraccelerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterviewController {

    @GetMapping("/interview")
    public String interviewPage() {
        return "interview/mock-interview";
    }
}