package com.example.quiz.interfaces;


import com.example.quiz.dto.ValidationDTOReq;
import com.example.quiz.dto.ValidationDTORes;

import java.util.List;

public interface IValidation {
    List<ValidationDTORes> findAll();
    ValidationDTORes findById(int id);
    ValidationDTOReq save(ValidationDTOReq validationDTOReq);
    ValidationDTOReq deleteById(int id);
    ValidationDTOReq update(ValidationDTOReq validationDTOReq);
    List<ValidationDTORes> findValidationByQuestion(int questionId);
}
