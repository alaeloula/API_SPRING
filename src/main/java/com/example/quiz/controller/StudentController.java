package com.example.quiz.controller;



import com.example.quiz.dto.StudentDTO;
import com.example.quiz.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/Student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "{studentId}")
    public ResponseEntity<StudentDTO> findById(@PathVariable int studentId) {
        return new ResponseEntity<>(studentService.findById(studentId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<StudentDTO>> findAll(Pageable pegeable){
        return new ResponseEntity<>(studentService.findAll(pegeable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.save(studentDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<StudentDTO> delete(@PathVariable int studentId){
        return new ResponseEntity<>(studentService.deleteById(studentId), HttpStatus.OK);
    }

}
