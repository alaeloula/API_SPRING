package com.example.quiz.controller;

import com.example.quiz.model.Level;
import com.example.quiz.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LevelController {

    @Autowired
    private LevelService levelService;

    @GetMapping("/levels")
    public Iterable<Level> getAll(){
        return levelService.getAll();
    }

    @PostMapping("/level")
    public Level save(@RequestBody Level level){
        return levelService.save(level);
    }

    @GetMapping("/level/{id}")
    public Level findById(@PathVariable("id") Integer id){
        try {
            return levelService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(path = {"/levelquestion/{id}"})
    public void deleteById(@PathVariable("id") Integer id){
        try {
            levelService.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
