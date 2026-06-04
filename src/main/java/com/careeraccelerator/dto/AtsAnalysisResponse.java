package com.careeraccelerator.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtsAnalysisResponse {

    private int atsScore;

    private int skillsScore;

    private int keywordScore;

    private int sectionScore;

    private String summary;

    private List<String> missingKeywords;
}