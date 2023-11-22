package com.example.quiz.interfaces;


import com.example.quiz.dto.TrainerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITrainer {
    Page<TrainerDTO> findAll(Pageable pageable);
    TrainerDTO findById(int id);
    TrainerDTO save(TrainerDTO trainerDTO);
    TrainerDTO deleteById(int id);
    TrainerDTO update(TrainerDTO trainerDTO);
}
