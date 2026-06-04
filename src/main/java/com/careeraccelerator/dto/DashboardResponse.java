package com.careeraccelerator.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponse {

    private String fullName;

    private String email;

    private int atsScore;

    private int interviewScore;

    private int roadmapProgress;

    private int jobMatchScore;

    private int overallScore;
}