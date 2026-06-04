package com.careeraccelerator.service;

import org.springframework.stereotype.Service;

import com.careeraccelerator.dto.DashboardResponse;
import com.careeraccelerator.entity.CareerScore;
import com.careeraccelerator.entity.User;
import com.careeraccelerator.repository.CareerScoreRepository;
import com.careeraccelerator.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;
    private final CareerScoreRepository scoreRepository;

    public DashboardResponse getDashboard(Long userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow();

        CareerScore score = scoreRepository
                .findByUserId(userId)
                .orElse(null);

        if(score == null) {

            score = CareerScore.builder()
                    .atsScore(0)
                    .interviewScore(0)
                    .roadmapProgress(0)
                    .jobMatchScore(0)
                    .overallScore(0)
                    .user(user)
                    .build();

            scoreRepository.save(score);
        }

        return new DashboardResponse(
                user.getFullName(),
                user.getEmail(),
                score.getAtsScore(),
                score.getInterviewScore(),
                score.getRoadmapProgress(),
                score.getJobMatchScore(),
                score.getOverallScore()
        );
    }
}