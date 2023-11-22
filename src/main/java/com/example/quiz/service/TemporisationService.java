package com.example.quiz.service;

import com.example.quiz.dto.TemporisationDTOReq;
import com.example.quiz.dto.TemporisationDTORes;
import com.example.quiz.exception.ResourceNotFoundException;
import com.example.quiz.interfaces.ITemporisation;

import com.example.quiz.model.Question;
import com.example.quiz.model.Temporisation;
import com.example.quiz.model.Test;
import com.example.quiz.repository.QuestionRepository;


import com.example.quiz.repository.TemporisationRepository;
import com.example.quiz.repository.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TemporisationService implements ITemporisation {

    @Autowired
    private TemporisationRepository temporisationRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestRepository testRepository;

    private TemporisationService(TemporisationRepository temporisationRepository, QuestionRepository questionRepository, TestRepository testRepository){
        this.temporisationRepository=temporisationRepository;
        this.questionRepository=questionRepository;
        this.testRepository=testRepository;
    }

    @Override
    public List<TemporisationDTORes> findAll() {
        List<Temporisation> tempDTOResList=temporisationRepository.findAll();
        return tempDTOResList.stream()
                .map(temp -> modelMapper.map(temp, TemporisationDTORes.class))
                .collect(Collectors.toList());
    }

    @Override
    public TemporisationDTORes findById(int id) {
        Temporisation temporisation = temporisationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(temporisation, TemporisationDTORes.class);
    }

    @Override
    public TemporisationDTOReq save(TemporisationDTOReq temporisationDTOReq) {
        Temporisation temporisation= modelMapper.map(temporisationDTOReq, Temporisation.class);
        Question question = questionRepository.findById(temporisationDTOReq.getQuestion_id())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + temporisationDTOReq.getQuestion_id()));
        Test test = testRepository.findById(temporisationDTOReq.getTest_id())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + temporisationDTOReq.getTest_id()));
        temporisation.setQuestion(question);
        temporisation.setTest(test);
        temporisationRepository.save(temporisation);
        return modelMapper.map(temporisation, TemporisationDTOReq.class);
    }

    @Override
    public TemporisationDTOReq deleteById(int id) {
        Temporisation temporisation = temporisationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        temporisationRepository.deleteById(id);
        return modelMapper.map(temporisation, TemporisationDTOReq.class);
    }

    @Override
    public TemporisationDTOReq update(TemporisationDTOReq temporisationDTOReq) {
        return null;
    }
}
