package com.example.quiz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@Entity
@Table
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Temporisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @NonNull
    private Test test;
    @ManyToOne
    @NonNull
    private Question question;
    @NonNull
    private Duration temporisation;
}
