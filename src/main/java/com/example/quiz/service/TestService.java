package com.example.quiz.service;


import com.example.quiz.dto.TestDTO;
import com.example.quiz.dto.TestDTOReq;
import com.example.quiz.exception.ResourceNotFoundException;
import com.example.quiz.interfaces.ITest;

import com.example.quiz.model.Test;
import com.example.quiz.model.Trainer;
import com.example.quiz.repository.TestRepository;
import com.example.quiz.repository.TrainerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TestService implements ITest {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private ModelMapper modelMapper;

    private TestService(TestRepository testRepository, TrainerRepository trainerRepository){
        this.testRepository=testRepository;
        this.trainerRepository=trainerRepository;

    }

    
    @Override
    public Page<TestDTO> findAll(Pageable pageable) {
        Page<Test> tests = testRepository.findAll(pageable);
        return tests
                .map(test -> modelMapper.map(test, TestDTO.class));
    }

    @Override
    public TestDTO findById(int id) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(test, TestDTO.class);
    }

    @Override
    public TestDTOReq save(TestDTOReq testDTOReq) {
        Test test = modelMapper.map(testDTOReq, Test.class);
        Trainer trainer = trainerRepository.findById(testDTOReq.getTrainer_matricule())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + testDTOReq.getTrainer_matricule()));
        test.setTrainer(trainer);
        testRepository.save(test);
        return modelMapper.map(test, TestDTOReq.class);
    }

    @Override
    public TestDTO deleteById(int id) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        testRepository.deleteById(id);
        return modelMapper.map(test, TestDTO.class);
    }

    @Override
    public TestDTO update(TestDTO testDTO) {
        Test test = testRepository.findById(testDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + testDTO.getId()));
        test.setSuccessScore(testDTO.getSuccessScore());
        test.setCanViewAnswers(testDTO.isCanViewAnswers());
        test.setCanSeeResult(testDTO.isCanSeeResult());
        test.setNumberOfChances(testDTO.getNumberOfChances());
        test.setRemarks(testDTO.getRemarks());
        test.setInstructions(testDTO.getInstructions());
        testRepository.save(test);
        return modelMapper.map(test, TestDTO.class);
    }
}
