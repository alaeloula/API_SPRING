package com.example.quiz.dto;

import com.example.quiz.model.Person;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class StudentDTO extends Person {
    private int code;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "dateInscription is required")
    @NonNull private LocalDate dateInscription;
}
