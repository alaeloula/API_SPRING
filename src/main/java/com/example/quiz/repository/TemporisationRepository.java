package com.example.quiz.repository;

import com.example.quiz.dto.TestDTO;
import com.example.quiz.model.Question;
import com.example.quiz.model.Temporisation;

import com.example.quiz.model.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporisationRepository extends JpaRepository<Temporisation,Integer> {
    List<Temporisation> findByTestId(Long test_id);
    List<Temporisation> findByTest(Test test);
}