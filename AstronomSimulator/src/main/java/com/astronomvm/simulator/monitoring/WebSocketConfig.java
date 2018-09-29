package com.astronomvm.simulator.monitoring;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Prefix for messages FROM server TO client
        config.enableSimpleBroker("/topic");
        // Prefix for messages FROM client TO server
        config.setApplicationDestinationPrefixes("/app");
        // /app wird beim client - sendName verwendet: stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name
        // }));
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/hello").withSockJS();
    }
}