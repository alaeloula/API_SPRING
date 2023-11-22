package com.example.quiz.repository;

import com.example.quiz.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment,Integer> {
}
