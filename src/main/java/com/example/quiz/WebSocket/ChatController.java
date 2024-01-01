package com.example.quiz.WebSocket;


import com.example.quiz.dto.MessageDTOreq;
import com.example.quiz.dto.MessageDTOres;
import com.example.quiz.service.MessageService;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat.sendMessage/{roomId}")
    @SendTo("/room/{roomId}")
    public MessageDTOres send(MessageDTOreq message, @DestinationVariable int roomId){
        MessageDTOres msg= messageService.save(message);
        return msg;
    }

}
