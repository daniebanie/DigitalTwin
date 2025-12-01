package com.nhlstenden.groep3.digital_twin.Repository;

import com.nhlstenden.groep3.digital_twin.Model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRepository {
    @MessageMapping("/messages/broadcast")
    public void receiveMessage(@Payload Message message){
        switch (message.getTitle()){
            case "test":
                System.out.println(message.getContent());
        }
    }
}
