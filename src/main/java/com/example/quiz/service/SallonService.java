package com.example.quiz.service;

import com.example.quiz.dto.SallonDTOreq;
import com.example.quiz.dto.SallonDTOres;
import com.example.quiz.exception.ResourceNotFoundException;
import com.example.quiz.interfaces.ISallon;
import com.example.quiz.model.Sallon;
import com.example.quiz.model.Student;
import com.example.quiz.repository.SallonRepository;
import com.example.quiz.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class SallonService implements ISallon {
    private final SallonRepository roomRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    private SallonService(StudentRepository studentRepository, SallonRepository roomRepository, ModelMapper modelMapper){
        this.studentRepository=studentRepository;
        this.roomRepository = roomRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public List<SallonDTOres> findAll() {
        List<Sallon> salles = roomRepository.findAll();
        return salles.stream()
                .map(r -> modelMapper.map(r, SallonDTOres.class))
                .collect(Collectors.toList());
    }

    public SallonDTOres findById(int id) {
        Sallon room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(room, SallonDTOres.class);
    }

    @Override
    public SallonDTOres save(SallonDTOreq roomDTOreq) {
        Sallon room= modelMapper.map(roomDTOreq, Sallon.class);
        Student student = studentRepository.findById(roomDTOreq.getStudent_id())
                .orElseThrow(() -> new ResourceNotFoundException("id student: " + roomDTOreq.getStudent_id()));
        room.setCreator(student);
        roomRepository.save(room);
        return modelMapper.map(room, SallonDTOres.class);
    }


    public SallonDTOres deleteById(int id){
        Sallon room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        roomRepository.deleteById(id);
        return modelMapper.map(room, SallonDTOres.class);
    }
}
