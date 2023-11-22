package com.example.quiz.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
public class ValidationDTOReq {
    private int id;

    @NotNull(message = "Point cannot be null")
    private Double point;

    @NotNull(message = "Response ID cannot be null")
    private Integer response_id;

    @NotNull(message = "Question ID cannot be null")
    private Integer question_id;
}
