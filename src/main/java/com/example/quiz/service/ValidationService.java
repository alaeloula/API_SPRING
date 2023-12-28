package com.example.quiz.service;

import com.example.quiz.dto.ValidationDTOReq;
import com.example.quiz.dto.ValidationDTORes;
import com.example.quiz.exception.ResourceNotFoundException;
import com.example.quiz.interfaces.IValidation;
import com.example.quiz.model.Question;
import com.example.quiz.model.Response;
import com.example.quiz.model.Validation;
import com.example.quiz.repository.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidationService implements IValidation {
    @Autowired
    private ValidationRepository validationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ResponseRepository responseRepository;

    private ValidationService(ValidationRepository validationRepository, TrainerRepository trainerRepository, StudentRepository studentRepository){
        this.validationRepository=validationRepository;
    }

    @Override
    public List<ValidationDTORes> findAll() {
        List<Validation> validationDTOResList=validationRepository.findAll();
        return validationDTOResList.stream()
                .map(valid -> modelMapper.map(valid, ValidationDTORes.class))
                .collect(Collectors.toList());
    }

    @Override
    public ValidationDTORes findById(int id) {
        Validation validation = validationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(validation, ValidationDTORes.class);
    }

    @Override
    public ValidationDTOReq save(ValidationDTOReq validationDTOReq) {
        Validation validation= modelMapper.map(validationDTOReq, Validation.class);
        Question question = questionRepository.findById(validationDTOReq.getQuestion_id())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + validationDTOReq.getQuestion_id()));
        Response response = responseRepository.findById(validationDTOReq.getResponse_id())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + validationDTOReq.getResponse_id()));
        validation.setQuestion(question);
        validation.setResponse(response);
        validationRepository.save(validation);
        return modelMapper.map(validation, ValidationDTOReq.class);
    }

    @Override
    public ValidationDTOReq deleteById(int id) {
        Validation validation = validationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        validationRepository.deleteById(id);
        return modelMapper.map(validation, ValidationDTOReq.class);
    }

    @Override
    public ValidationDTOReq update(ValidationDTOReq validationDTOReq) {
        return null;
    }

    @Override
    public List<ValidationDTORes> findValidationByQuestion(int questionId){
        List<Validation> validationDTOResList=validationRepository.findByQuestionId(questionId);
        return validationDTOResList.stream()
                .map(valid -> modelMapper.map(valid, ValidationDTORes.class))
                .collect(Collectors.toList());
    }


    public List<ValidationDTORes> findByQuestion(int questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("id question : " + questionId));
        List<Validation> tmps=validationRepository.findByQuestion(question);
        return tmps.stream()
                .map(temp -> modelMapper.map(temp, ValidationDTORes.class))
                .collect(Collectors.toList());
    }


}
