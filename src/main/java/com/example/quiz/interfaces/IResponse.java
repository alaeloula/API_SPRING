package com.example.quiz.interfaces;


import com.example.quiz.dto.ResponseDTO;

import java.util.List;

public interface IResponse {
    List<ResponseDTO> findAll();
    ResponseDTO findById(int id);
    ResponseDTO save(ResponseDTO responseDTO);
    ResponseDTO deleteById(int id);
    ResponseDTO update(ResponseDTO responseDTO);
}
