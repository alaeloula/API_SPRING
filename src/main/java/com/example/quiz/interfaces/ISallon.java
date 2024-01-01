package com.example.quiz.interfaces;

import com.example.quiz.dto.SallonDTOreq;
import com.example.quiz.dto.SallonDTOres;

import java.util.List;

public interface ISallon {
    List<SallonDTOres> findAll();
    SallonDTOres findById(int id);
    SallonDTOres save(SallonDTOreq RoomDTOreq);
    SallonDTOres deleteById(int id);
}
