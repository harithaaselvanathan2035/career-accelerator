package com.careeraccelerator.service;

import com.careeraccelerator.dto.EvaluationResponse;
import com.careeraccelerator.dto.InterviewRequest;
import com.careeraccelerator.dto.InterviewResponse;
import com.careeraccelerator.entity.InterviewQuestion;
import com.careeraccelerator.repository.InterviewQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MockInterviewService {

    private final InterviewQuestionRepository
            questionRepository;

    public List<InterviewResponse>
    startInterview(
            InterviewRequest request){

        return questionRepository
                .findByDomainAndDifficulty(
                        request.getDomain(),
                        request.getDifficulty()
                )
                .stream()
                .limit(
                        request.getQuestionCount()
                )
                .map(
                        q ->
                                InterviewResponse
                                        .builder()
                                        .id(q.getId())
                                        .question(q.getQuestion())
                                        .build()
                )
                .toList();
    }

    public EvaluationResponse
    evaluateAnswer(
            String answer){

        int score = 0;

        if(answer.length() > 50){
            score += 40;
        }

        if(answer.length() > 150){
            score += 30;
        }

        if(answer.toLowerCase()
                .contains("example")){
            score += 30;
        }

        return EvaluationResponse
                .builder()
                .score(score)
                .feedback(
                        score >= 80
                                ? "Excellent Answer"
                                : score >= 60
                                ? "Good Answer"
                                : "Needs Improvement"
                )
                .build();
    }
}