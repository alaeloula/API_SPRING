package com.example.quiz.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectDTOResp {
    private int id;
    private String title;
    private SubjectDTOReq parent;
    //private List<SubjectDTOReq> children;
}
