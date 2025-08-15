package com.nextgen.ai.rag_demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
@Slf4j
public class VectorStoreConfig {
    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel, VectorStoreProperties vectorStoreProperties) throws IOException {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        File vectorStore = new File(vectorStoreProperties.getVectorStorePath());
        if (vectorStore.exists()) {
           vectorStore.createNewFile();
        }
        vectorStoreProperties.getDocuments().forEach(document -> {
            configure(simpleVectorStore, vectorStoreProperties, vectorStore);
        });
        simpleVectorStore.save(vectorStore);

        return simpleVectorStore;
    }

    public void configure(SimpleVectorStore simpleVectorStore, VectorStoreProperties vectorStoreProperties, File vectorStore) {
        log.info("Loading document into vector store");
        vectorStoreProperties.getDocuments().forEach(document -> {
            log.info("Loading document : {}", document.getFilename());
            TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(document);
            List<Document> documents = tikaDocumentReader.get();
            TextSplitter textSplitter = new TokenTextSplitter();
            List<Document> splitDocs = textSplitter.apply(documents);
            simpleVectorStore.add(splitDocs);
        });

    }
}
