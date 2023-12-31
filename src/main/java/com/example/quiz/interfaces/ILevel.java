package com.example.quiz.interfaces;

import com.example.quiz.dto.LevelDTO;

import java.util.List;

public interface ILevel {
    List<LevelDTO> findAll();
    LevelDTO findById(int id);
    LevelDTO save(LevelDTO levelDTO);
    LevelDTO deleteById(int id);
    LevelDTO update(LevelDTO levelDTO);
}
