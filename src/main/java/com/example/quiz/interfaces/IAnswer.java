package com.example.quiz.interfaces;


import com.example.quiz.dto.AnswerDTOReq;
import com.example.quiz.dto.AnswerDTORes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAnswer {
    Page<AnswerDTORes> findAll(Pageable pageable);
    AnswerDTORes findById(int id);
    AnswerDTOReq save(AnswerDTOReq answerDTOReq);
    AnswerDTORes deleteById(int id);
    AnswerDTOReq update(AnswerDTOReq answerDTOReq);
}
