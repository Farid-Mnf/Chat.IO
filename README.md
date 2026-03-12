# Chat.IO

A real-time one-to-one private messaging web application built with Spring Boot and WebSocket.

[![Demo Video](https://img.youtube.com/vi/gH92s4Llhvo/maxresdefault.jpg)](https://youtu.be/gH92s4Llhvo)

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Framework | Spring Boot, Spring MVC |
| Real-time | WebSocket, SockJS, STOMP |
| Persistence | Spring Data JPA, H2 (in-memory) |
| Templating | Thymeleaf |
| Frontend | HTML, CSS, JavaScript, Bootstrap |
| Icons | Font Awesome |

## Features

- **User registration & login** with form validation
- **Real-time messaging** -- bidirectional communication via WebSocket
- **Contact search** -- async searchable list to find and message any user
- **Contacts list** -- view all connected users
- **Profile pictures** -- upload and update profile images

## Architecture

```
Client A                    Server                     Client B
   |                          |                           |
   |--- STOMP CONNECT ------->|                           |
   |--- SUBSCRIBE /user/queue |                           |
   |                          |                           |
   |--- SEND /app/chat ------>|                           |
   |                          |--- MESSAGE /user/queue -->|
   |                          |       (to recipient)      |
   |                          |                           |
```

Messages are routed through Spring's `SimpMessagingTemplate` using user-specific queues, ensuring private delivery between two users.

## How to Run

**Prerequisites:** Java 8+, Maven

```bash
# Clone the repository
git clone https://github.com/Farid-Mnf/Chat.IO.git
cd Chat.IO

# Run the application
./mvnw spring-boot:run
```

The app starts at `http://localhost:8080`. Open two browser windows to test messaging between users.

> The app uses an H2 in-memory database -- no external database setup required.

## Screenshots

### Landing Page
![Landing Page](https://github.com/Farid-Mnf/Chat.IO/assets/36532455/b7140bd6-1c97-402a-8819-846b4c634899)

### Registration
![Register](https://github.com/Farid-Mnf/Chat.IO/assets/36532455/68bb46cb-9329-4939-be46-e48fe4652df8)

### Login
![Login](https://github.com/Farid-Mnf/Chat.IO/assets/36532455/abdb8e24-b361-444a-af2a-176c3d5db7ca)

### Home & Contact Search
![Home](https://github.com/Farid-Mnf/Chat.IO/assets/36532455/0da56598-ebad-4a9a-95de-c1c215a44446)

### Search Results
![Search Results](https://github.com/Farid-Mnf/Chat.IO/assets/36532455/e1be995a-7344-4e84-a5f9-a8dbe640e965)

### Contacts List
![Contacts](https://github.com/Farid-Mnf/Chat.IO/assets/36532455/1588f689-26d2-40ff-a6a4-b0bb1e934805)

### Chat Interface
![Chat](https://github.com/Farid-Mnf/Chat.IO/assets/36532455/2282566c-5815-4789-87ef-b7710ace36ee)
