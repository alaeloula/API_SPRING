package com.example.quiz.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Embeddable
public class Validation {
    @ManyToOne
    @NonNull
    private Question question;
    @ManyToOne
    @NonNull private Response response;
    @NonNull private Double point;
}
