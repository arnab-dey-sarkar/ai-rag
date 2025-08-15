package com.nextgen.ai.rag_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "rag.ai")
@Data
public class VectorStoreProperties {
    String vectorStorePath;
    List<Resource> documents;
}
