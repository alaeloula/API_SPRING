package com.example.quiz.repository;

import com.example.quiz.dto.QuestionDTORes;
import com.example.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findBySubjectId(int subjectId);
    List<Question> findByLevelId(int id);


}
