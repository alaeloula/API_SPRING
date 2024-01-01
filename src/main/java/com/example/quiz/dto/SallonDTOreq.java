package com.example.quiz.dto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class SallonDTOreq {

    @NotNull(message = "Date creation cannot be null")
    private LocalDate dateCreation;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @ManyToOne
    private Integer student_id;
}
