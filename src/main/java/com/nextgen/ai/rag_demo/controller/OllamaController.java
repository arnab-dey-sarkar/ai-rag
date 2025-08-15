package com.nextgen.ai.rag_demo.controller;

import com.nextgen.ai.rag_demo.service.AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OllamaController {
    private final AIService aiService;

    @PostMapping("/ask")
    public ResponseEntity<String> retrieveAnswer(
            @RequestBody String question) {
        try {
            String result = aiService.answer(question);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Exception occurred {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
