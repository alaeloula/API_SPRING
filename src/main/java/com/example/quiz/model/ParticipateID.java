package com.example.quiz.model;


import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@NoArgsConstructor
public class ParticipateID implements Serializable {
    @ManyToOne
    private Student student;
    @ManyToOne
    private Sallon room;
}
