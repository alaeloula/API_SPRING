package com.example.quiz.service;


import com.example.quiz.dto.AssignementTDOReq;
import com.example.quiz.dto.AssignementTDORes;
import com.example.quiz.exception.ResourceNotFoundException;
import com.example.quiz.interfaces.IAssignement;
import com.example.quiz.model.Assignment;
import com.example.quiz.model.Student;
import com.example.quiz.model.Test;
import com.example.quiz.repository.AssignmentRepository;
import com.example.quiz.repository.StudentRepository;
import com.example.quiz.repository.TestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService implements IAssignement {

    private final AssignmentRepository assignmentRepository;
    private final StudentRepository studentRepository;
    private final TestRepository testRepository;
    private final ModelMapper modelMapper;

    public AssignmentService(AssignmentRepository assignmentRepository, StudentRepository studentRepository, TestRepository testRepository, ModelMapper modelMapper) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
        this.testRepository = testRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<AssignementTDORes> findAll() {
        List<Assignment> assignments = assignmentRepository.findAll();
        return assignments.stream()
                .map(assi -> modelMapper.map(assi, AssignementTDORes.class))
                .collect(Collectors.toList());
    }

    @Override
    public AssignementTDORes findById(int id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(assignment, AssignementTDORes.class);
    }

    @Override
    public AssignementTDOReq save(AssignementTDOReq assignementTDOReq) {
        Assignment assignment= modelMapper.map(assignementTDOReq, Assignment.class);
        Test test = testRepository.findById(assignementTDOReq.getTest_id())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + assignementTDOReq.getTest_id()));
        Student student = studentRepository.findById(assignementTDOReq.getStudent_id())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + assignementTDOReq.getStudent_id()));
        assignment.setStudent(student);
        assignment.setTest(test);
        assignmentRepository.save(assignment);
        return modelMapper.map(assignment, AssignementTDOReq.class);
    }

    @Override
    public AssignementTDORes deleteById(int id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        assignmentRepository.deleteById(id);
        return modelMapper.map(assignment, AssignementTDORes.class);
    }

    @Override
    public AssignementTDOReq update(AssignementTDOReq assignementTDOReq) {
        return null;
    }
}
