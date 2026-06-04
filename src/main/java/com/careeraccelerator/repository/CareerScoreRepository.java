package com.careeraccelerator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careeraccelerator.entity.CareerScore;

public interface CareerScoreRepository
        extends JpaRepository<CareerScore, Long> {

    Optional<CareerScore> findByUserId(Long userId);
}