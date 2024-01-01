package com.example.quiz.dto;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class SallonDTOres {
    private int id;
    private LocalDate dateCreation;
    private String title;
    private String description;
    private StudentDTO creator;

}
