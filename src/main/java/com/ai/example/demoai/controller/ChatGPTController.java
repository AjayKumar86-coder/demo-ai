package com.ai.example.demoai.controller;

import com.ai.example.demoai.service.ChatGPTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    public ChatGPTController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    // Inner class for request payload
    public static class ChatRequest {
        private String prompt;

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> chat(@RequestBody ChatRequest chatRequest) {
        if (chatRequest == null || chatRequest.getPrompt() == null || chatRequest.getPrompt().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Prompt cannot be empty."));
        }

        try {
            String response = chatGPTService.getChatGPTResponse(chatRequest.getPrompt());
            if (response.startsWith("Error:")) {
                 // Let's assume service returns "Error:..." for service-level errors
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", response));
            }
            return ResponseEntity.ok(Map.of("response", response));
        } catch (Exception e) {
            // Log the exception server-side
            System.err.println("Unexpected error in ChatGPTController: " + e.getMessage());
            // Return a generic error to the client
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An unexpected error occurred."));
        }
    }
}
