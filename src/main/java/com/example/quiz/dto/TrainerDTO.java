package com.example.quiz.dto;


import com.example.quiz.model.Person;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrainerDTO extends Person {
    private int matricule;
    @NotBlank(message = "specialite is required")
    @NonNull
    private String specialite;
}
