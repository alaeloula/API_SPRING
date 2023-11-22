package com.example.quiz.dto;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class TestDTO {
    private int id;
    @NonNull private int successScore;
    @NonNull private boolean canViewAnswers;
    @NonNull private boolean canSeeResult;
    @NonNull private int numberOfChances;
    @NonNull private String remarks;
    @NonNull private String instructions;
    @NonNull private TrainerDTO trainer;
}
