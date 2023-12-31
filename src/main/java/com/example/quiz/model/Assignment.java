package com.example.quiz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
@RequiredArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull private LocalDate dateStart;
    @NonNull private LocalDate dateEnd;
    @Transient
    @NonNull private LocalDate dure;
    @NonNull private double score;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Answer> answers;
}
