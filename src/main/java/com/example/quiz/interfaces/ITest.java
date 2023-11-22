package com.example.quiz.interfaces;


import com.example.quiz.dto.TestDTO;
import com.example.quiz.dto.TestDTOReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITest {
    Page<TestDTO> findAll(Pageable pageable);
    TestDTO findById(int id);
    TestDTOReq save(TestDTOReq testDTOReq);
    TestDTO deleteById(int id);
    TestDTO update(TestDTO testDTO);
}
