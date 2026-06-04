package com.careeraccelerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/analytics")
    public String analytics() {
        return "analytics/analytics";
    }
    @GetMapping("/profile")
    public String profile() {
        return "profile/profile";
    }
}