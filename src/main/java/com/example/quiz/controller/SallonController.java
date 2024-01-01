package com.example.quiz.controller;

import com.example.quiz.dto.SallonDTOreq;
import com.example.quiz.dto.SallonDTOres;
import com.example.quiz.interfaces.ISallon;
import com.example.quiz.service.SallonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Salle")
public class SallonController {
    private final ISallon roomService;

    private SallonController(SallonService roomService){
        this.roomService = roomService;
    }
    @GetMapping
    public ResponseEntity<List<SallonDTOres>> getAll(){
        return new ResponseEntity<>(roomService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "{salleId}")
    public ResponseEntity<SallonDTOres> findById(@PathVariable int salleId) {
        return new ResponseEntity<>(roomService.findById(salleId), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<SallonDTOres> save(@Valid @RequestBody SallonDTOreq SalleDTO){
        return new ResponseEntity<>(roomService.save(SalleDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "{salleId}")
    public ResponseEntity<SallonDTOres> delete(@PathVariable int salleId){
        return new ResponseEntity<>(roomService.deleteById(salleId), HttpStatus.OK);
    }
}
