package com.example.quiz.interfaces;

import com.example.quiz.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudent {
    Page<StudentDTO> findAll(Pageable pageable);
    StudentDTO findById(int id);
    StudentDTO save(StudentDTO studentDTO);
    StudentDTO deleteById(int id);
    StudentDTO update(StudentDTO studentDTO);
}
