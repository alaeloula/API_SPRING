package com.example.quiz.service;

import com.example.quiz.dto.MessageDTOreq;
import com.example.quiz.dto.MessageDTOres;
import com.example.quiz.exception.ResourceNotFoundException;
import com.example.quiz.interfaces.IMessage;
import com.example.quiz.model.*;
import com.example.quiz.repository.MessageRepository;
import com.example.quiz.repository.ParticipateRepository;
import com.example.quiz.repository.SallonRepository;
import com.example.quiz.repository.StudentRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService implements IMessage {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private SallonRepository roomRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ParticipateRepository participateRepository;

    @Autowired
    private ModelMapper modelMapper;

    public MessageService(MessageRepository messageRepository, SallonRepository roomRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.roomRepository = roomRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<MessageDTOres> findAll() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(msg -> modelMapper.map(msg, MessageDTOres.class))
                .collect(Collectors.toList());
    }

    @Override
    public MessageDTOres findById(Integer id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        return modelMapper.map(message, MessageDTOres.class);
    }


    @Override
    public MessageDTOres save(MessageDTOreq messageDTOreq) {
        Message message= modelMapper.map(messageDTOreq, Message.class);
        Sallon Room = roomRepository.findById(Math.toIntExact(messageDTOreq.getParticipateID().getRoom().getId()))
                .orElseThrow(() -> new ResourceNotFoundException("id room: " + messageDTOreq.getParticipateID().getRoom().getId()));
        Student student = studentRepository.findById(messageDTOreq.getParticipateID().getStudent().getCode())
                .orElseThrow(() -> new ResourceNotFoundException("code student : " + messageDTOreq.getParticipateID().getStudent().getCode()));
        ParticipateID pId= new ParticipateID();
        pId.setRoom(Room);
        pId.setStudent(student);
        Participate participate = participateRepository.findById(pId)
                .orElseThrow(() -> new ResourceNotFoundException("id participate : " + messageDTOreq.getParticipateID().getStudent().getCode()));
        message.setParticipate(participate);
        messageRepository.save(message);
        return modelMapper.map(message, MessageDTOres.class);
    }

    @Override
    public MessageDTOres deleteById(Integer id){
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id : " + id));
        messageRepository.deleteById(id);
        return modelMapper.map(message, MessageDTOres.class);
    }

    @Override
    public MessageDTOres update(MessageDTOreq messageDTOreq) {
        return null;
    }

    @Override
    public List<MessageDTOres> findMessagesByRoomId(Integer RoomId) {
        List<Message> messages = messageRepository.findMessagesByRoomId(RoomId);
        return messages.stream()
                .map(msg -> modelMapper.map(msg, MessageDTOres.class))
                .collect(Collectors.toList());
    }
}
