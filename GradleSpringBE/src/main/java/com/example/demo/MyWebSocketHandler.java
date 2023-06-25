package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
//import kafkaService.KafkaMessageProducer;

public class MyWebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyWebSocketHandler.class);
//    private KafkaMessageProducer kafkaMessageProducer;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("WebSocket connection established. Session ID: {}", session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String receivedMessage = message.getPayload();
        logger.info("Received message: {}", receivedMessage);

        // Handle the received message

        // Example: Send a response back to the client
        String responseMessage = "Hello from the server!";
        session.sendMessage(new TextMessage(responseMessage));
        logger.info("Sent message: {}", responseMessage);
        
//        kafkaMessageProducer.sendMessage("my-topic", "Hello everyone!I am Caspar");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("WebSocket connection closed. Session ID: {}. Close status: {}", session.getId(), status);
    }
}
