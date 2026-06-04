package com.careeraccelerator.controller;

import com.careeraccelerator.dto.RoadmapRequest;
import com.careeraccelerator.dto.RoadmapResponse;
import com.careeraccelerator.service.RoadmapService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roadmap")
@RequiredArgsConstructor
public class RoadmapController {

    private final RoadmapService roadmapService;

    @PostMapping("/generate")
    public RoadmapResponse generateRoadmap(
            @RequestBody RoadmapRequest request) {

        return roadmapService.generateRoadmap(
                request
        );
    }
}