package com.nextgen.ai.rag_demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {
    private final OllamaChatModel ollamaChatModel;
    private final SimpleVectorStore simpleVectorStore;
    @Value("classpath:/templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    public String answer(String question) {
        List<Document> documents=simpleVectorStore.similaritySearch(SearchRequest.builder().query(question).topK(4).build());
        if(!CollectionUtils.isEmpty(documents)) {
            List<String> contentList = documents.stream().map(Document::getText).toList();
            PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
            Prompt prompt = promptTemplate.create(Map.of("question", question, "documents", String.join("\n", contentList)));
            ChatResponse chatResponse = ollamaChatModel.call(prompt);
            return chatResponse.getResult().getOutput().getText();
        }
        else
        {
            return "No documents found";
        }
    }
}
