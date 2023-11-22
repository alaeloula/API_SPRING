package com.example.quiz.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectDTOReq {
    private int id;
    @NotNull(message = "title is required")
    private String title;
    //@NotNull(message = "parent_id is required")
    private Integer parent_id;
}
