package com.example.quiz.interfaces;



import com.example.quiz.dto.MessageDTOreq;
import com.example.quiz.dto.MessageDTOres;

import java.util.List;

public interface IMessage {
    List<MessageDTOres> findAll();
    MessageDTOres findById(Integer id);
    MessageDTOres save(MessageDTOreq messageDTOreq);
    MessageDTOres deleteById(Integer id);
    MessageDTOres update(MessageDTOreq messageDTOreq);
    List<MessageDTOres> findMessagesByRoomId(Integer salleId);
}
