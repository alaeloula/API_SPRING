package com.example.quiz.repository;

import com.example.quiz.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test,Integer> {
}
