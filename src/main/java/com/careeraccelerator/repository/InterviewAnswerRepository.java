package com.careeraccelerator.repository;

import com.careeraccelerator.entity.InterviewAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewAnswerRepository
        extends JpaRepository<InterviewAnswer, Long> {
}