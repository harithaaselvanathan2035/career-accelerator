package com.careeraccelerator.repository;

import com.careeraccelerator.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository
        extends JpaRepository<Resume, Long> {
}