package com.careeraccelerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterviewPageController {

    @GetMapping("/mock-interview")
    public String interviewPage(){

        return "interview/mock-interview";
    }
}