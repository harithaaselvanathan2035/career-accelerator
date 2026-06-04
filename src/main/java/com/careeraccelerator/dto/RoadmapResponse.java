package com.careeraccelerator.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadmapResponse {

    private String title;

    private List<String> roadmap;
}