package com.example.quiz.service;

import com.example.quiz.dto.TrainerDTO;
import com.example.quiz.interfaces.ITrainer;
import com.example.quiz.model.Trainer;
import com.example.quiz.repository.TrainerRepository;
import com.example.quiz.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrainerService implements ITrainer {
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }


    @Override
    public Page<TrainerDTO> findAll(Pageable pageable) {
        Page<Trainer> Trainers = trainerRepository.findAll(pageable);
        return Trainers.map(Trainer -> modelMapper.map(Trainer, TrainerDTO.class));
    }

    @Override
    public TrainerDTO findById(int id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(trainer, TrainerDTO.class);
    }

    @Override
    public TrainerDTO save(TrainerDTO TrainerDTO) {
        Trainer Trainer= modelMapper.map(TrainerDTO, Trainer.class);
        return modelMapper.map(Trainer, TrainerDTO.class);
    }

    @Override
    public TrainerDTO deleteById(int id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        trainerRepository.deleteById(id);
        return modelMapper.map(trainer, TrainerDTO.class);
    }

    @Override
    public TrainerDTO update(TrainerDTO trainerDTO) {
        Trainer trainer = trainerRepository.findById(trainerDTO.getMatricule())
                .orElseThrow(() -> new ResourceNotFoundException("id : " + trainerDTO.getMatricule()));
        trainer.setFirstName(trainerDTO.getFirstName());
        trainer.setLastName(trainerDTO.getLastName());
        trainer.setAdresse(trainerDTO.getAdresse());
        trainer.setSpecialite(trainerDTO.getSpecialite());
        trainerRepository.save(trainer);
        return modelMapper.map(trainer, TrainerDTO.class);
    }
}
