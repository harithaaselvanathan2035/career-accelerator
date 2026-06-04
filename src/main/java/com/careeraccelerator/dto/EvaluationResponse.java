package com.careeraccelerator.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationResponse {

    private Integer score;

    private String feedback;
}