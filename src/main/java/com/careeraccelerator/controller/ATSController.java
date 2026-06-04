package com.careeraccelerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ATSController {

    @GetMapping("/ats-analysis")
    public String atsAnalysis() {

        return "ats/ats-analysis";
    }
}