# RAG Demo Application

This is a Spring Boot application demonstrating Retrieval-Augmented Generation (RAG) using Spring AI.

## Features
- Vector store configuration with SimpleVectorStore
- Document processing using TikaDocumentReader
- Text splitting using TokenTextSplitter
- Integration with embedding models
- Ollama API integration for LLM interactions

## Project Structure
```
src/
├── main/
│   ├── java/com/nextgen/ai/rag_demo/
│   │   ├── config/                 # Configuration classes
│   │   ├── controller/             # REST controllers
│   │   ├── service/                # Business logic
│   │   └── RagDemoApplication.java # Main application class
│   └── resources/                  # Application resources
└── test/                           # Test classes   
```

## Technology Stack
- Java 21
- Spring Boot 3.x
- Gradle build system
- Spring AI
- Tika for document processing
- Ollama API integration

## Getting Started
1. Make sure you have Java 21 and Gradle installed
2. Run the application using `./gradlew bootRun`
3. Access the application at `http://localhost:8080`       

## Usage
The application provides the following endpoint:
- `/ask` - Process questions using RAG with Ollama integration
