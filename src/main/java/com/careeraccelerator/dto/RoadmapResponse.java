package com.careeraccelerator.dto;

import java.util.List;

public class RoadmapResponse {

    private List<String> roadmap;

    public RoadmapResponse(List<String> roadmap) {
        this.roadmap = roadmap;
    }

    public List<String> getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(List<String> roadmap) {
        this.roadmap = roadmap;
    }
}