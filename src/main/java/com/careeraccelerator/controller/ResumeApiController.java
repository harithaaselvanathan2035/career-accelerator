package com.careeraccelerator.controller;

import com.careeraccelerator.dto.ResumeRequest;
import com.careeraccelerator.entity.Resume;
import com.careeraccelerator.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ResumeApiController {

    private final ResumeService resumeService;

    @PostMapping("/save")
    public ResponseEntity<Resume>
    saveResume(
            @RequestBody
            ResumeRequest request){

        Resume savedResume =
                resumeService
                        .saveResume(
                                request);

        return ResponseEntity.ok(
                savedResume);
    }
}