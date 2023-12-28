package com.example.quiz.controller;

import com.example.quiz.dto.QuestionDTORes;
import com.example.quiz.dto.TestDTO;
import com.example.quiz.dto.TestDTOReq;
import com.example.quiz.service.TestService;
import com.example.quiz.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Test")
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private QuestionService QuestionService;

    private TestController(TestService testService){
        this.testService=testService;
    }

    @GetMapping(path = "{testId}")
    public ResponseEntity<TestDTO> findById(@PathVariable int testId) {
        return new ResponseEntity<>(testService.findById(testId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<TestDTO>> findAll(Pageable pageable){
        return new ResponseEntity<>(testService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TestDTOReq> save(@Valid @RequestBody TestDTOReq testDTOReq){
        return new ResponseEntity<>(testService.save(testDTOReq), HttpStatus.OK);
    }

    @DeleteMapping(path = "{testId}")
    public ResponseEntity<TestDTO> delete(@PathVariable int testId){
        return new ResponseEntity<>(testService.deleteById(testId), HttpStatus.OK);
    }



}
