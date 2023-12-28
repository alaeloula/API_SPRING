package com.example.quiz.interfaces;


import com.example.quiz.dto.TemporisationDTOReq;
import com.example.quiz.dto.TemporisationDTORes;
import com.example.quiz.dto.TestDTO;
import com.example.quiz.model.Question;


import java.util.List;

public interface ITemporisation {
    List<TemporisationDTORes> findAll();
    TemporisationDTORes findById(int id);
    TemporisationDTOReq save(TemporisationDTOReq temporisationDTOReq);
    TemporisationDTOReq deleteById(int id);
    TemporisationDTOReq update(TemporisationDTOReq temporisationDTOReq);
//    List<Question>getQuestionsForQuiz(int id);
}
