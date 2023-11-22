package com.example.quiz.dto;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AnswerDTORes {
    private int id;
    @NonNull private ValidationDTORes validation;
    @NonNull private AssignementTDORes assignment;
}
