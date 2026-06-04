package com.careeraccelerator.controller;

import com.careeraccelerator.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin("*")
public class JobController {

    private final JobService jobService;

    @GetMapping("/search")
    public ResponseEntity<String> searchJobs(

            @RequestParam String query,

            @RequestParam(required = false,
                    defaultValue = "")
            String location) {

        return ResponseEntity.ok(
                jobService.searchJobs(
                        query,
                        location
                )
        );
    }
}