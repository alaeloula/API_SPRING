package com.example.quiz.service;


import com.example.quiz.dto.ParticipateDTOreq;
import com.example.quiz.dto.ParticipateDTOres;
import com.example.quiz.dto.SallonDTOres;
import com.example.quiz.exception.ResourceNotFoundException;
import com.example.quiz.interfaces.IParticipate;
import com.example.quiz.model.Participate;
import com.example.quiz.model.ParticipateID;
import com.example.quiz.model.Sallon;
import com.example.quiz.model.Student;
import com.example.quiz.repository.ParticipateRepository;
import com.example.quiz.repository.SallonRepository;
import com.example.quiz.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ParticipateService implements IParticipate {

    private final ParticipateRepository participateRepository;
    private final SallonRepository roomRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    public ParticipateService(ParticipateRepository participateRepository, SallonRepository roomRepository, StudentRepository studenteRepository, ModelMapper modelMapper) {
        this.participateRepository = participateRepository;
        this.roomRepository = roomRepository;
        this.studentRepository = studenteRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public List<ParticipateDTOres> findAll() {
        List<Participate> participates = participateRepository.findAll();
        return participates.stream()
                .map(s -> modelMapper.map(s, ParticipateDTOres.class))
                .collect(Collectors.toList());
    }

    public ParticipateDTOres findById(ParticipateID id) {
        Participate participate = participateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(participate, ParticipateDTOres.class);
    }

    @Override
    public ParticipateDTOres save(ParticipateDTOreq participateDTOreq) {
        // Map DTO to entity
        Participate participate = modelMapper.map(participateDTOreq, Participate.class);

        // Retrieve Salle and Student entities
        Sallon room = roomRepository.findById(Math.toIntExact(participateDTOreq.getParticipateID().getRoom().getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + participateDTOreq.getParticipateID().getRoom().getId()));

        Student student = studentRepository.findById(participateDTOreq.getParticipateID().getStudent().getCode())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with code: " + participateDTOreq.getParticipateID().getStudent().getCode()));

        // Set ParticipateID
        ParticipateID participateID = new ParticipateID();
        participateID.setRoom(room);
        participateID.setStudent(student);
        participate.setParticipateID(participateID);

        // Save the Participate entity
        participateRepository.save(participate);

        // Map the saved entity back to DTO for response
        return modelMapper.map(participate, ParticipateDTOres.class);
    }


    public ParticipateDTOres deleteById(ParticipateID id){
        Participate participate = participateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        participateRepository.deleteById(id);
        return modelMapper.map(participate, ParticipateDTOres.class);
    }

    @Override
    public ParticipateDTOres update(ParticipateDTOreq participateDTOreq) {
        return null;
    }

    @Override
    public List<SallonDTOres> findParticipatesByStudent(Integer studentId, String status){
        List<Sallon> rooms=null;
        if(Objects.equals(status, "in")){
            rooms=participateRepository.findParticipatesByStudentId(studentId);
        }else{
            //not worked yet need some changes
            rooms=participateRepository.findParticipatesByStudentNotIn(studentId);
        }
        return rooms.stream()
                .map(s -> modelMapper.map(s, SallonDTOres.class))
                .collect(Collectors.toList());
    }
}
