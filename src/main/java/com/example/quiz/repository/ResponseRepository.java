package com.example.quiz.repository;

import com.example.quiz.model.Response;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends CrudRepository<Response,Integer> {
}
