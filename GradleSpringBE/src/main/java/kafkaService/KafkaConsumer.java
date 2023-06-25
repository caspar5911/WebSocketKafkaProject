package kafkaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public KafkaConsumer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(String message) {
        // Forward the Kafka message to connected WebSocket clients
        messagingTemplate.convertAndSend("/topic/kafka-messages", message);
    }
}
