package com.example.quiz.service;

import com.example.quiz.dto.QuestionDTOReq;
import com.example.quiz.dto.QuestionDTORes;
import com.example.quiz.exception.ResourceNotFoundException;
import com.example.quiz.interfaces.IQuestion;
import com.example.quiz.model.Level;
import com.example.quiz.model.Question;

import com.example.quiz.model.Subject;
import com.example.quiz.repository.LevelRepository;
import com.example.quiz.repository.QuestionRepository;
import com.example.quiz.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService implements IQuestion {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    public QuestionService(QuestionRepository questionRepository, LevelRepository levelRepository, SubjectRepository subjectRepository) {
        this.questionRepository = questionRepository;
        this.levelRepository = levelRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Page<QuestionDTORes> findAll(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAll(pageable);
        return questionPage.map(question -> modelMapper.map(question, QuestionDTORes.class));
    }

    @Override
    public QuestionDTORes findById(int id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(question, QuestionDTORes.class);
    }

    @Override
    public QuestionDTOReq save(QuestionDTOReq questionDTOReq) {
        Question question= modelMapper.map(questionDTOReq, Question.class);
        Subject sub = subjectRepository.findById(questionDTOReq.getSubject_id())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + questionDTOReq.getSubject_id()));
        Level lvl = levelRepository.findById(questionDTOReq.getLevel_id())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + questionDTOReq.getLevel_id()));
        question.setSubject(sub);
        question.setLevel(lvl);
        questionRepository.save(question);
        return modelMapper.map(question, QuestionDTOReq.class);
    }

    @Override
    public QuestionDTORes deleteById(int id){
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        questionRepository.deleteById(id);
        return modelMapper.map(question, QuestionDTORes.class);
    }

    @Override
    public QuestionDTOReq update(QuestionDTOReq questionDTOReq) {
        return null;
    }


    public List<QuestionDTORes> findBySubjectId(int subjectId){
        List<Question> questions = questionRepository.findBySubjectId(subjectId);
        return questions.stream()
                .map(question -> modelMapper.map(question, QuestionDTORes.class))
                .collect(Collectors.toList());
    }

    public List<QuestionDTORes> findByLevelId(int levelId){
        List<Question> questions = questionRepository.findByLevelId(levelId);
        return questions.stream()
                .map(question -> modelMapper.map(question, QuestionDTORes.class))
                .collect(Collectors.toList());
    }




}


