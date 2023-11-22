package com.example.quiz.service;


import com.example.quiz.dto.AnswerDTOReq;
import com.example.quiz.dto.AnswerDTORes;
import com.example.quiz.exception.ResourceNotFoundException;
import com.example.quiz.interfaces.IAnswer;
import com.example.quiz.model.Answer;
import com.example.quiz.model.Assignment;
import com.example.quiz.model.Validation;
import com.example.quiz.repository.AnswerRepository;
import com.example.quiz.repository.AssignmentRepository;
import com.example.quiz.repository.ValidationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService implements IAnswer {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private ValidationRepository validationRepository;
    @Autowired
    private ModelMapper modelMapper;

    public AnswerService(AnswerRepository answerRepository, AssignmentRepository assignmentRepository, ValidationRepository validationRepository) {
        this.answerRepository=answerRepository;
        this.assignmentRepository=assignmentRepository;
        this.validationRepository=validationRepository;
    }

    @Override
    public Page<AnswerDTORes> findAll(Pageable pageable) {
        Page<Answer> answers = answerRepository.findAll(pageable);
        return answers
                .map(ans -> modelMapper.map(ans, AnswerDTORes.class));
    }



    @Override
    public AnswerDTORes findById(int id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(answer, AnswerDTORes.class);
    }

    @Override
    public AnswerDTOReq save(AnswerDTOReq answerDTOReq) {
        Answer answer= modelMapper.map(answerDTOReq, Answer.class);
        Assignment assignment = assignmentRepository.findById(answerDTOReq.getAssignment_id())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + answerDTOReq.getAssignment_id()));
        answer.setAssignment(assignment);
        answerRepository.save(answer);
        return modelMapper.map(answer, AnswerDTOReq.class);
    }

    @Override
    public AnswerDTORes deleteById(int id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        answerRepository.deleteById(id);
        return modelMapper.map(answer, AnswerDTORes.class);
    }

    @Override
    public AnswerDTOReq update(AnswerDTOReq answerDTOReq) {
        return null;
    }
}
