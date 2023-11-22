package com.example.quiz.repository;

import com.example.quiz.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValidationRepository extends JpaRepository<Validation,Integer> {
    List<Validation> findByQuestionId(int questionId);
}
