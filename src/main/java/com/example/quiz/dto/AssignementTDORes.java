package com.example.quiz.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class AssignementTDORes {
    private long id;
    @NonNull
    private LocalDate dateStart;
    @NonNull private LocalDate dateEnd;
    @NonNull private double score;
    @NonNull private TestDTO test;
    @NonNull private StudentDTO student;
}
