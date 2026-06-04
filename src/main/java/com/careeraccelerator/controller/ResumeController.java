package com.careeraccelerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResumeController {

    @GetMapping("/resume-builder")
    public String resumeBuilder(){

        return "resume/resume-builder";
    }

    @GetMapping("/resume/upload")
    public String uploadResume(){

        return "resume/upload-resume";
    }
}