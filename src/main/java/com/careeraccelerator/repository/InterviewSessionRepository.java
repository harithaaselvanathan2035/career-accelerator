package com.careeraccelerator.repository;

import com.careeraccelerator.entity.InterviewSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewSessionRepository
        extends JpaRepository<InterviewSession, Long> {
}