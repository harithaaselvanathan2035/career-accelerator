package com.careeraccelerator.service;

import com.careeraccelerator.dto.ResumeRequest;
import com.careeraccelerator.entity.Resume;
import com.careeraccelerator.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public Resume saveResume(
            ResumeRequest request){

        Resume resume =
                Resume.builder()

                        .fullName(
                                request.getFullName())

                        .email(
                                request.getEmail())

                        .phone(
                                request.getPhone())

                        .linkedin(
                                request.getLinkedin())

                        .github(
                                request.getGithub())

                        .location(
                                request.getLocation())

                        .summary(
                                request.getSummary())

                        .education(
                                request.getEducation())

                        .skills(
                                request.getSkills())

                        .certifications(
                                request.getCertifications())

                        .projects(
                                request.getProjects())

                        .activities(
                                request.getActivities())

                        .build();

        return resumeRepository.save(
                resume);
    }
}