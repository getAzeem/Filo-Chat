package com.example.filo.service;

import com.example.filo.api.GroqApiClient;
import com.example.filo.api.dto.GroqRequest;
import com.example.filo.api.dto.GroqResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {
    private final GroqApiClient groqApiClient;

    public ChatbotService(GroqApiClient groqApiClient) {
        this.groqApiClient = groqApiClient;
    }

    public GroqResponse chatWithBot(String message) {
        GroqRequest request = new GroqRequest(message);
        return groqApiClient.sendMessage(request);
    }


}
