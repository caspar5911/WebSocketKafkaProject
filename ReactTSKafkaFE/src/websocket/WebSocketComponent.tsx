import React, { useEffect, useState } from 'react';
import { Kafka, logLevel } from 'kafkajs';

const WebSocketComponent: React.FC = () => {
  const [socket, setSocket] = useState<WebSocket | null>(null);
  const [message, setMessage] = useState<string>('');
  const [messages, setMessages] = useState<string[]>([]);

  useEffect(() => {
    // Create WebSocket connection
    const ws = new WebSocket('ws://localhost:8080/websocket');
    
    // Set up event handlers
    ws.onopen = () => {
      console.log('WebSocket connection opened');
    };

    ws.onmessage = (event) => {
      const receivedMessage = event.data;
      console.log('Message received:', receivedMessage);
      // Handle the received Kafka message
      setMessages((prevMessages) => [...prevMessages, receivedMessage]);
    };

    ws.onclose = () => {
      console.log('WebSocket connection closed');
    };
    ws.onerror = (error) => {
      console.error('WebSocket error:', error);
    };

    // Save the WebSocket instance in state
    setSocket(ws);

    // Clean up the WebSocket connection when the component unmounts
    return () => {
      if (ws.readyState === 1) {
        ws.close();
      }
    };
  }, []);

  const sendMessage = async () => {
      if (socket && socket.readyState === WebSocket.OPEN) {
      socket.send(message);
      console.log('Message sent:', message);
    }
  };

  return (
    <div>
      <input
        type="text"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
      />
      <button onClick={sendMessage}>Send Message</button>
    </div>
  );
};

export default WebSocketComponent;
