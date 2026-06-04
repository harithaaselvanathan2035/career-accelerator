package com.careeraccelerator.repository;

import com.careeraccelerator.entity.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewQuestionRepository
        extends JpaRepository<InterviewQuestion, Long> {

    List<InterviewQuestion>
    findByDomainAndDifficulty(
            String domain,
            String difficulty
    );
}