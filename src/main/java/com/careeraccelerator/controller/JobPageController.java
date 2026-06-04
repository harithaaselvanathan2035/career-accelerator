package com.careeraccelerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobPageController {

    @GetMapping("/jobs")
    public String jobsPage() {
        return "jobs/jobs";
    }
}
