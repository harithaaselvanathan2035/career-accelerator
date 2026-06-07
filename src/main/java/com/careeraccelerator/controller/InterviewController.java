package com.careeraccelerator.controller;

import com.careeraccelerator.dto.EvaluationResponse;
import com.careeraccelerator.dto.InterviewEvaluationRequest;
import com.careeraccelerator.dto.InterviewRequest;
import com.careeraccelerator.dto.InterviewResponse;
import com.careeraccelerator.service.MockInterviewService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interview")
@RequiredArgsConstructor
@CrossOrigin("*")
public class InterviewController {

    private final MockInterviewService mockInterviewService;

    // ==========================================
    // START INTERVIEW
    // ==========================================

    @PostMapping("/start")
    public ResponseEntity<List<InterviewResponse>>
    startInterview(
            @RequestBody InterviewRequest request) {

        return ResponseEntity.ok(
                mockInterviewService.startInterview(request)
        );
    }

    // ==========================================
    // EVALUATE ANSWER
    // ==========================================

    @PostMapping("/evaluate")
    public ResponseEntity<EvaluationResponse>
    evaluateAnswer(
            @RequestBody InterviewEvaluationRequest request) {

        return ResponseEntity.ok(
                mockInterviewService.evaluateAnswer(
                        request.getAnswer()
                )
        );
    }

    // ==========================================
    // GET QUESTIONS BY FILTER
    // ==========================================

    @GetMapping("/questions")
    public ResponseEntity<List<InterviewResponse>>
    getQuestions(
            @RequestParam String domain,
            @RequestParam String difficulty,
            @RequestParam(defaultValue = "5")
            Integer count) {

        InterviewRequest request =
                new InterviewRequest();

        request.setDomain(domain);
        request.setDifficulty(difficulty);
        request.setQuestionCount(count);

        return ResponseEntity.ok(
                mockInterviewService.startInterview(request)
        );
    }

    // ==========================================
    // HEALTH CHECK
    // ==========================================

    @GetMapping("/status")
    public ResponseEntity<String> status() {

        return ResponseEntity.ok(
                "Mock Interview Module Running Successfully 🚀"
        );
    }
}