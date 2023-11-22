package com.example.quiz.service;


import com.example.quiz.dto.LevelDTO;
import com.example.quiz.interfaces.ILevel;
import com.example.quiz.model.Level;
import com.example.quiz.repository.LevelRepository;
import com.example.quiz.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevelService implements ILevel {
    
    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private ModelMapper modelMapper;
    private LevelService(LevelRepository levelRepository){
        this.levelRepository=levelRepository;
    }

    @Override
    public List<LevelDTO> findAll() {
        List<Level> levels = levelRepository.findAll();
        return levels.stream()
                .map(level -> modelMapper.map(level, LevelDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LevelDTO findById(int id) {
        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(level, LevelDTO.class);
    }


    @Override
    public LevelDTO save(LevelDTO levelDTO) {
        Level level= modelMapper.map(levelDTO, Level.class);
        levelRepository.save(level);
        return modelMapper.map(level, LevelDTO.class);
    }

    @Override
    public LevelDTO deleteById(int id){
        Level level = levelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        levelRepository.deleteById(id);
        return modelMapper.map(level, LevelDTO.class);
    }

    @Override
    public LevelDTO update(LevelDTO levelDTO) {
        Level level = levelRepository.findById(levelDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + levelDTO.getId()));
        level.setDescription(levelDTO.getDescription());
        level.setPointMin(levelDTO.getPointMin());
        level.setPointMax(levelDTO.getPointMax());
        levelRepository.save(level);
        return modelMapper.map(level, LevelDTO.class);
    }

}
