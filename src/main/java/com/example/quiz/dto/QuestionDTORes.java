package com.example.quiz.dto;


import com.example.quiz.model.QuestionType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
public class QuestionDTORes {
    private int id;
    @NonNull
    private int numberOfResponses;
    @NonNull private int numberOfCorrectResponses;
    @NonNull private String questionText;
    @NonNull private QuestionType type;
    @NonNull private SubjectDTOReq subject;
    @NonNull private LevelDTO level;
}
