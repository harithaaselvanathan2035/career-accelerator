package com.careeraccelerator.controller;

import com.careeraccelerator.dto.*;
import com.careeraccelerator.service.MockInterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview")
@RequiredArgsConstructor
public class MockInterviewController {

    private final
    MockInterviewService
            interviewService;

    @PostMapping("/start")
    public List<InterviewResponse>
    startInterview(
            @RequestBody
            InterviewRequest request){

        return interviewService
                .startInterview(
                        request
                );
    }

    @PostMapping("/evaluate")
    public EvaluationResponse
    evaluateAnswer(
            @RequestBody String answer){

        return interviewService
                .evaluateAnswer(
                        answer
                );
    }
}