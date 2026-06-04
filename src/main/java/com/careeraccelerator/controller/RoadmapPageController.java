package com.careeraccelerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoadmapPageController {

    @GetMapping("/roadmap")
    public String roadmapPage() {

        return "roadmap/roadmap";
    }
}