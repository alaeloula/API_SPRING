package com.example.quiz.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Level {
    //private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String description;
    @NonNull private int pointMin;
    @NonNull private int pointMax;

    @OneToMany(mappedBy = "level",fetch = FetchType.EAGER)
    private List<Question> questions;
}
