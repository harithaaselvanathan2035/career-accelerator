package com.careeraccelerator.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "career_scores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int atsScore;

    private int interviewScore;

    private int roadmapProgress;

    private int jobMatchScore;

    private int overallScore;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}