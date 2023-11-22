package com.example.quiz.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Question implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private int numberOfResponses;
    @NonNull private int numberOfCorrectResponses;
    @NonNull private String questionText;
    @NonNull private String image;

    @ManyToOne
  //@JsonIgnore
    @NonNull private Subject subject;
 // @JsonIgnore
    @ManyToOne
    @NonNull private Level level;

}
