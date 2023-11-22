package com.example.quiz.interfaces;

import com.example.quiz.dto.QuestionDTOReq;
import com.example.quiz.dto.QuestionDTORes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IQuestion {
    Page<QuestionDTORes> findAll(Pageable pageable);
    QuestionDTORes findById(int id);
    QuestionDTOReq save(QuestionDTOReq questionDTOReq);
    QuestionDTORes deleteById(int id);
    QuestionDTOReq update(QuestionDTOReq questionDTOReq);
}
