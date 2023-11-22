package com.example.quiz.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ValidationDTORes {
    private int id;
    @NonNull private double point;
    @NonNull private ResponseDTO response;
    @NonNull private QuestionDTORes question;
}
