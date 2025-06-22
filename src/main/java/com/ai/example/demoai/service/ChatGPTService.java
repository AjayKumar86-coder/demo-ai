package com.ai.example.demoai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTService {

    private final ChatClient chatClient;

    public ChatGPTService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getChatGPTResponse(String userPrompt) {
        try {
            Prompt prompt = new Prompt(userPrompt);
            ChatResponse response = chatClient.prompt(prompt).call().chatResponse();
            return response.getResult().getOutput().getContent();
        } catch (Exception e) {
            // Basic error logging, can be enhanced
            System.err.println("Error calling OpenAI API: " + e.getMessage());
            // In a real application, throw a custom exception or handle more gracefully
            return "Error: Could not get response from AI service.";
        }
    }
}
