package com.example.quiz.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor
public class TemporisationDTOReq {
    private int id;

    @Min(value = 1, message = "Time must be greater than or equal to 1")
    private Integer time;

    @NotNull(message = "Test ID is required")
    private Integer test_id;

    @NotNull(message = "Question ID is required")
    private Integer question_id;
}
