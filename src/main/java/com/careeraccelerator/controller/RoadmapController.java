package com.careeraccelerator.controller;

import com.careeraccelerator.dto.RoadmapRequest;
import com.careeraccelerator.dto.RoadmapResponse;
import com.careeraccelerator.service.RoadmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roadmap")
@CrossOrigin("*")
public class RoadmapController {

    @Autowired
    private RoadmapService roadmapService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateRoadmap(
            @RequestBody RoadmapRequest request) {

        return ResponseEntity.ok(
                new RoadmapResponse(
                        roadmapService.generateRoadmap(
                                request.getDomain(),
                                request.getLevel()
                        )
                )
        );
    }
}