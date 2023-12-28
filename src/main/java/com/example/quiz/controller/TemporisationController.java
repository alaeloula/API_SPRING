package com.example.quiz.controller;

import com.example.quiz.dto.TemporisationDTOReq;
import com.example.quiz.dto.TemporisationDTORes;

import com.example.quiz.dto.TestDTO;
import com.example.quiz.model.Question;
import com.example.quiz.model.Test;
import com.example.quiz.service.TemporisationService;
import com.example.quiz.service.TestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Temporisation")
public class TemporisationController {
    @Autowired
    private TemporisationService temporisationService;
    @Autowired
    private  TestService quizService;



    private TemporisationController(TemporisationService temporisationService){
        this.temporisationService=temporisationService;
    }

    @GetMapping(path = "{temporisationId}")
    public ResponseEntity<TemporisationDTORes> findById(@PathVariable int temporisationId) {
        return new ResponseEntity<>(temporisationService.findById(temporisationId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TemporisationDTORes>> findAll(){
        return new ResponseEntity<>(temporisationService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TemporisationDTOReq> save(@RequestBody @Valid TemporisationDTOReq temporisationDTOReq){
        return new ResponseEntity<>(temporisationService.save(temporisationDTOReq), HttpStatus.OK);
    }

    @DeleteMapping(path = "{temporisationId}")
    public ResponseEntity<TemporisationDTOReq> delete(@PathVariable int temporisationId){
        return new ResponseEntity<>(temporisationService.deleteById(temporisationId), HttpStatus.OK);
    }

    @GetMapping(path = "byTest/{testId}")
    public ResponseEntity<List<TemporisationDTORes>> findByTest(@PathVariable int testId){
        return new ResponseEntity<>(temporisationService.findByTest(testId), HttpStatus.OK);
    }


//    @GetMapping("/quizzes/{quizId}/questions")
//    public ResponseEntity<List<Question>> getQuestionsForQuiz(@PathVariable int quizId) {
//        TestDTO test = quizService.findById(quizId); // Obtention du test associé au quiz
//
//        if (test == null) {
//            return ResponseEntity.notFound().build(); // Gérer le cas où le test n'est pas trouvé
//        }
//
//        List<Question> questions = temporisationService.getQuestionsForQuiz(test); // Obtention des questions
//
//        return ResponseEntity.ok().body(questions); // Retourner les questions associées au quiz
//    }
}
