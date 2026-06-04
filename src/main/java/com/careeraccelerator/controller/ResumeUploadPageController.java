package com.careeraccelerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResumeUploadPageController {

    @GetMapping("/upload-resume")
    public String uploadPage(){

        return "resume/upload-resume";
    }
}