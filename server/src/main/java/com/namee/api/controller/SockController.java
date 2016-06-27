package com.namee.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static com.namee.core.spring.configuration.WebSocketConfiguration.MESSAGE_PREFIX;

/**
 * Created by namee on 2016. 6. 21..
 */
@Controller
@Slf4j
public class SockController {
    @Autowired
    public SimpMessagingTemplate websocket;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting() {
        log.debug("websocket : {}", "call");
        websocket.convertAndSend(MESSAGE_PREFIX + "/lovedise", "lovedise!!");
        return "hi~";
    }
}
