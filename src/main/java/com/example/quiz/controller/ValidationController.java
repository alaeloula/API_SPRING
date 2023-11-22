package com.example.quiz.controller;

import com.example.quiz.dto.ValidationDTOReq;
import com.example.quiz.dto.ValidationDTORes;
import com.example.quiz.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Validation")
public class ValidationController {

    @Autowired
    private ValidationService validationService;


    private ValidationController(ValidationService validationService){
        this.validationService=validationService;
    }

    @GetMapping("/Question/{QuestionId}")
    public ResponseEntity<List<ValidationDTORes>> findValidationByQuestion(@PathVariable int QuestionId){
        return new ResponseEntity<>(validationService.findValidationByQuestion(QuestionId), HttpStatus.OK);
    }

    @GetMapping(path = "{validationId}")
    public ResponseEntity<ValidationDTORes> findById(@PathVariable int validationId) {
        return new ResponseEntity<>(validationService.findById(validationId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ValidationDTORes>> findAll(){
        return new ResponseEntity<>(validationService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ValidationDTOReq> save(@Valid @RequestBody ValidationDTOReq validationDTOReq){
        return new ResponseEntity<>(validationService.save(validationDTOReq), HttpStatus.OK);
    }

    @DeleteMapping(path = "{validationId}")
    public ResponseEntity<ValidationDTOReq> delete(@PathVariable int validationId){
        return new ResponseEntity<>(validationService.deleteById(validationId), HttpStatus.OK);
    }

}
