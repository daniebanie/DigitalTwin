package com.nhlstenden.groep3.digital_twin.Repository;

import com.nhlstenden.groep3.digital_twin.Model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRepository {

    private final SimpMessagingTemplate template;

    @Autowired
    public ClientRepository(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/messages/broadcast")
    public void receiveMessage(@Payload Message message){
        switch (message.getTitle()){
            case "test":
                System.out.println(message.getContent());
                sendMessage(new Message("ServerMessage", "This is a message"));
        }
    }

    public void sendMessage(Message message){
        template.convertAndSend("/topic/messages", message);
    }
}
