package com.example.quiz.service;

import com.example.quiz.model.Question;
import com.example.quiz.repository.LevelRepository;
import com.example.quiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService{
    @Autowired
    private QuestionRepository questionRepository;
    private final LevelRepository levelRepository;

    public QuestionService(QuestionRepository questionRepository,
                           LevelRepository levelRepository) {
        this.questionRepository = questionRepository;
        this.levelRepository = levelRepository;
    }

    public Iterable<Question> getAll(){
        return questionRepository.findAll();
    }

    public Question findById(int id) throws Exception {
        Optional<Question> optional=questionRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new Exception("Question not found");
    }


    public void deleteById(int id){
        questionRepository.deleteById(id);
    }

    public Question save(Question question){
        return questionRepository.save(question);
    }

    public List<Question> findBySubjectId(int subjectId){
        return questionRepository.findBySubjectId(subjectId);
    }

    public List<Question> findByLevelId(int levelId){
        List<Question> tmp = levelRepository.findById(levelId).get().getQuestions();
        System.out.println("=>"+tmp.size());
        return tmp;
    }

}
