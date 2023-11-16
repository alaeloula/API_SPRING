package com.example.quiz.controller;

import com.example.quiz.model.Subject;
import com.example.quiz.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/Subject")
@Api(tags = "Subject Management")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value = "Get all subjects")
    @GetMapping
    public Iterable<Subject> getAll(){
        return subjectService.getAll();
    }

    @PostMapping
    public Subject save(@RequestBody Subject subject){
        return subjectService.save(subject);
    }

    @GetMapping(path = {"{subjectId}"})
    public Subject findById(@PathVariable("subjectId") Integer id){
        try {
            return subjectService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(path = {"{subjectId}"})
    public void deleteById(@PathVariable("subjectId") Integer id){
        try {
            subjectService.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
