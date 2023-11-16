package com.example.quiz.controller;

import com.example.quiz.model.Question;
import com.example.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;



    @GetMapping
    public Iterable<Question> getAll(){
        return questionService.getAll();
    }

    @PostMapping
    public Question save(@RequestBody Question Question){
        return questionService.save(Question);
    }

    @GetMapping("{questionId}")
    public Question findById(@PathVariable("questionId") Integer id){
        try {
            return questionService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("{questionId}")
    public void deleteById(@PathVariable("questionId") Integer id){
        try {
            questionService.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("bySubject/{subjectId}")
    public List<Question> findBySubjectId(@PathVariable("subjectId") Integer id){;
        try {
            return questionService.findBySubjectId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping( "byLevel/{levelId}")
    public List<Question> findByLevelId(@PathVariable("levelId") Integer id){;
        try {
            return questionService.findByLevelId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
