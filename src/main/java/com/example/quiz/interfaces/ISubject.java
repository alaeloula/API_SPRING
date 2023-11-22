package com.example.quiz.interfaces;


import com.example.quiz.dto.SubjectDTOReq;
import com.example.quiz.dto.SubjectDTOResp;

import java.util.List;

public interface ISubject {
    List<SubjectDTOResp> findAll();
    SubjectDTOResp findById(int id);
    SubjectDTOReq save(SubjectDTOReq subjectDTOReq);
    SubjectDTOResp deleteById(int id);
    SubjectDTOReq update(SubjectDTOReq subjectDTOReq);
}
