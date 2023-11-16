package com.example.quiz.controller;

import com.example.quiz.model.Response;
import com.example.quiz.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/Response")
public class ResponseController {
    @Autowired
    private ResponseService responseService;


    @GetMapping
    public Iterable<Response> getAll(){
        return responseService.getAll();
    }

    @PostMapping
    public Response save(@RequestBody Response response){
        return responseService.save(response);
    }

    @GetMapping(path = {"{responseId}"})
    public Response findById(@PathVariable("responseId") Integer id) throws Exception {
        return responseService.findById(id);
    }

    @DeleteMapping(path = {"{responseId}"})
    public void deleteById(@PathVariable("responseId") Integer id) throws Exception{
        responseService.deleteById(id);
    }
}
