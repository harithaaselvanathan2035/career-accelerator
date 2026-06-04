package com.careeraccelerator.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "interview_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sessionId;

    @Column(length = 10000)
    private String answer;

    private Integer score;
}