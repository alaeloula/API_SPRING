package com.example.quiz.controller;

import com.example.quiz.dto.AnswerDTOReq;
import com.example.quiz.dto.AnswerDTORes;
import com.example.quiz.service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/Answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    private AnswerController(AnswerService answerService){
        this.answerService=answerService;
    }

    @GetMapping
    public ResponseEntity<Page<AnswerDTORes>> getAll(Pageable pageable){
        return new ResponseEntity<>(answerService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnswerDTOReq> save(@Valid @RequestBody AnswerDTOReq answerDTOReq){
        return new ResponseEntity<>(answerService.save(answerDTOReq), HttpStatus.OK);
    }

    @GetMapping(path = {"{answertId}"})
    public ResponseEntity<AnswerDTORes> findById(@PathVariable("answertId") Integer answertId){
        return new ResponseEntity<>(answerService.findById(answertId), HttpStatus.OK);
    }

    @DeleteMapping(path = {"{answertId}"})
    public ResponseEntity<AnswerDTORes> deleteById(@PathVariable("answertId") Integer answertId){
        return new ResponseEntity<>(answerService.deleteById(answertId), HttpStatus.OK);
    }
}
