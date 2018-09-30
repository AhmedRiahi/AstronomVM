package com.astronomvm.simulator.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/websocket")
public class WebSocketRest {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/ping")
    public void ping(){
        this.simpMessagingTemplate.convertAndSend("/topic/ping","ping");
    }
}
