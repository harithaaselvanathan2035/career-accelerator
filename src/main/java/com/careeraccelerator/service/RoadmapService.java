package com.careeraccelerator.service;

import com.careeraccelerator.dto.RoadmapRequest;
import com.careeraccelerator.dto.RoadmapResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoadmapService {

    public RoadmapResponse generateRoadmap(
            RoadmapRequest request) {

        List<String> roadmap =
                new ArrayList<>();

        String role =
                request.getRole();

        switch (role) {

            case "Java Full Stack Developer":

                roadmap.add("Month 1 - Java Fundamentals");
                roadmap.add("Month 2 - OOP + Collections");
                roadmap.add("Month 3 - JDBC + MySQL");
                roadmap.add("Month 4 - Spring Boot");
                roadmap.add("Month 5 - React JS");
                roadmap.add("Month 6 - Projects + Interviews");

                break;

            case "Python Developer":

                roadmap.add("Month 1 - Python Basics");
                roadmap.add("Month 2 - OOP + Modules");
                roadmap.add("Month 3 - Django");
                roadmap.add("Month 4 - REST APIs");
                roadmap.add("Month 5 - Projects");
                roadmap.add("Month 6 - Interview Prep");

                break;

            case "Data Analyst":

                roadmap.add("Month 1 - Excel");
                roadmap.add("Month 2 - SQL");
                roadmap.add("Month 3 - Python");
                roadmap.add("Month 4 - Power BI");
                roadmap.add("Month 5 - Data Visualization");
                roadmap.add("Month 6 - Portfolio Projects");

                break;

            default:

                roadmap.add("Learning Path Coming Soon");
        }

        return RoadmapResponse
                .builder()
                .title(role)
                .roadmap(roadmap)
                .build();
    }
}