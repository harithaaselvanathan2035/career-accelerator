package com.careeraccelerator.dto;

import lombok.Data;

@Data
public class InterviewRequest {

    private String domain;

    private String difficulty;

    private Integer questionCount;
}