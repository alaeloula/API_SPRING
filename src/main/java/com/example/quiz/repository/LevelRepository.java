package com.example.quiz.repository;

import com.example.quiz.model.Level;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends CrudRepository<Level,Integer> {
}
