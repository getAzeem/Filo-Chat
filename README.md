# Filo - Chatbot API (Spring Boot)

## Overview
This project is a chatbot API built using **Spring Boot** that integrates with the **Groq AI API** to generate AI-powered responses. It showcases your skills in **Spring Boot, REST APIs, Dependency Injection, Configuration Management, Exception Handling, and External API Integration**.

## Technologies Used
- **Spring Boot** - Backend framework
- **Spring MVC** - REST API handling
- **Spring Configuration** - Managing properties and beans
- **Jackson** - JSON processing
- **Apache HttpClient** - Making external API calls
- **RESTful APIs** - Handling client requests
- **CORS Configuration** - Allowing cross-origin requests

## Project Structure
```
filo
│── src/main/java/com/example/filo
│   ├── api
│   │   ├── dto
│   │   │   ├── GroqRequest.java
│   │   │   ├── GroqResponse.java
│   │   ├── GroqApiClient.java
│   ├── config
│   │   ├── GroqConfig.java
│   │   ├── WebConfig.java
│   ├── controller
│   │   ├── ChatbotController.java
│   ├── exception
│   │   ├── ApiException.java
│   ├── service
│   │   ├── ChatbotService.java
│   │   ├── GroqService.java
│   ├── FiloApplication.java
```

## Class Descriptions

### 1. **API DTO (Data Transfer Objects)**
- **GroqRequest.java** - Represents the request payload with a `message` field.
- **GroqResponse.java** - Represents the response payload with a `reply` field.

### 2. **API Client**
- **GroqApiClient.java** - Handles communication with the Groq API using `RestTemplate`. It sends the user input and receives AI-generated responses.

### 3. **Configuration Classes**
- **GroqConfig.java** - Loads Groq API credentials from application properties.
- **WebConfig.java** - Configures CORS to allow frontend access from `http://localhost:3939`.

### 4. **Controller**
- **ChatbotController.java** - The main REST controller that exposes the `/api/chat` endpoint. It processes user input, calls the Groq API, and returns AI responses.

### 5. **Service Layer**
- **ChatbotService.java** - Uses `GroqApiClient` to handle chatbot interactions and return responses.
- **GroqService.java** - Alternative service that manually sends requests using `Apache HttpClient`.

### 6. **Exception Handling**
- **ApiException.java** - Custom exception class for handling API-related errors.

### 7. **Main Application**
- **FiloApplication.java** - The entry point of the Spring Boot application. It initializes the `RestTemplate` bean.

## Flow of API Calls
1. **Client sends a request** to `/api/chat` with a message payload.
2. **ChatbotController** receives the request and validates it.
3. **ChatbotService** processes the request and calls **GroqApiClient**.
4. **GroqApiClient** sends the request to the **Groq AI API**.
5. **Groq AI API** generates a response and sends it back to **GroqApiClient**.
6. **GroqApiClient** processes the response and returns it to **ChatbotService**.
7. **ChatbotService** forwards the response to **ChatbotController**.
8. **ChatbotController** returns the AI-generated response to the client.

## How It Works
1. **User sends a chat message** to `/api/chat`.
2. The **ChatbotController** processes the request and calls the **Groq API**.
3. The response is returned as JSON, containing the AI-generated reply.

## Running the Project
1. Clone the repository.
2. Configure API credentials in `application.properties`:
   ```properties
   groq.api.url=https://api.groq.com/openai/v1/chat/completions
   groq.api.key=YOUR_API_KEY
   ```
3. Run the Spring Boot application:
   ```sh
   mvn spring-boot:run
   ```
4. Send a POST request to `http://localhost:8080/api/chat` with a JSON payload:
   ```json
   {
       "message": "Hello, AI!"
   }
   ```

## Future Improvements
- Add **WebSocket** for real-time chat.
- Implement **database storage** for conversation history.
- Enhance **error handling** and **logging**.

---
🚀 Built with passion and Spring Boot expertise!

