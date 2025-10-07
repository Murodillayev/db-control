package uz.pdp.dbcontrol;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping
    public String indexPage() {
        return "index";
    }

    @MessageMapping("/send-message")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        System.out.println(message.getContent());
        return message;
    }

    @MessageMapping("/typing")
    @SendTo("/topic/typing")
    public String typing(@Payload String username) {



        return username;
    }

}
