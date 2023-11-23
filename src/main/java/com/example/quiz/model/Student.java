package com.example.quiz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor
public class Student extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    @NonNull private LocalDate dateInscription;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Assignment> assignments;

    public Student(String firstName, String lastName, LocalDate birthday,String adresse,LocalDate dateInscription){
        super(firstName,lastName,birthday,adresse);
        this.dateInscription=dateInscription;
    }
}
