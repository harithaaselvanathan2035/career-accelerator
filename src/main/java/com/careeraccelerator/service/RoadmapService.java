package com.careeraccelerator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoadmapService {

    public List<String> generateRoadmap(String domain, String level) {

        List<String> roadmap = new ArrayList<>();

        switch (domain.toLowerCase()) {

            case "data science":

                if (level.equalsIgnoreCase("Beginner")) {

                    roadmap.add("Introduction to Data Science");
                    roadmap.add("Python Fundamentals");
                    roadmap.add("NumPy Basics");
                    roadmap.add("Pandas for Data Analysis");
                    roadmap.add("Data Cleaning");
                    roadmap.add("Statistics Fundamentals");
                    roadmap.add("Data Visualization");
                    roadmap.add("Mini Project");

                } else if (level.equalsIgnoreCase("Intermediate")) {

                    roadmap.add("Advanced Pandas");
                    roadmap.add("Feature Engineering");
                    roadmap.add("Machine Learning Basics");
                    roadmap.add("Scikit-Learn");
                    roadmap.add("Model Evaluation");
                    roadmap.add("SQL for Analytics");
                    roadmap.add("Power BI / Tableau");
                    roadmap.add("End-to-End Project");

                } else {

                    roadmap.add("Deep Learning");
                    roadmap.add("TensorFlow");
                    roadmap.add("PyTorch");
                    roadmap.add("Natural Language Processing");
                    roadmap.add("Computer Vision");
                    roadmap.add("MLOps");
                    roadmap.add("Cloud Deployment");
                    roadmap.add("Interview Preparation");
                }

                break;

            case "web development":

                if (level.equalsIgnoreCase("Beginner")) {

                    roadmap.add("HTML");
                    roadmap.add("CSS");
                    roadmap.add("JavaScript");
                    roadmap.add("Responsive Design");
                    roadmap.add("Git & GitHub");
                    roadmap.add("Mini Website Project");

                } else if (level.equalsIgnoreCase("Intermediate")) {

                    roadmap.add("React");
                    roadmap.add("REST APIs");
                    roadmap.add("Node.js");
                    roadmap.add("Express.js");
                    roadmap.add("MongoDB");
                    roadmap.add("Portfolio Project");

                } else {

                    roadmap.add("System Design");
                    roadmap.add("Microservices");
                    roadmap.add("Docker");
                    roadmap.add("AWS");
                    roadmap.add("CI/CD");
                    roadmap.add("Production Deployment");
                }

                break;

            default:

                if (level.equalsIgnoreCase("Beginner")) {

                    roadmap.add("Introduction to " + domain);
                    roadmap.add("Fundamentals");
                    roadmap.add("Core Concepts");
                    roadmap.add("Tools & Setup");
                    roadmap.add("Mini Projects");

                } else if (level.equalsIgnoreCase("Intermediate")) {

                    roadmap.add("Advanced Concepts");
                    roadmap.add("Frameworks");
                    roadmap.add("Projects");
                    roadmap.add("Portfolio Building");
                    roadmap.add("Best Practices");

                } else {

                    roadmap.add("System Design");
                    roadmap.add("Industry Standards");
                    roadmap.add("Real-world Applications");
                    roadmap.add("Optimization");
                    roadmap.add("Interview Preparation");
                }
        }

        return roadmap;
    }
}