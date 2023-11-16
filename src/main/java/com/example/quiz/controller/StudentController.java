package com.example.quiz.controller;

import com.example.quiz.model.Student;
import com.example.quiz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Student")
public class StudentController {

    @Autowired
    private StudentService studentService;



    @GetMapping
    public Iterable<Student> findAll(){
        return studentService.findAll();
    }

    @PostMapping
    public Student add(@RequestBody Student student){
        return studentService.add(student);
    }

    @GetMapping(path = {"{studentId}"})
    public Student findById(@PathVariable("studentId") Integer code){
        try {
            return studentService.findById(code);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(path = {"{studentId}"})
    public void deleteById(@PathVariable("studentId") int id){
        studentService.deleteById(id);
    }

}