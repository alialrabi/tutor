# Tutor Platform - Backend API

A comprehensive online tutoring platform backend built with **Spring Boot 3.2**, providing RESTful APIs for connecting students with tutors, managing sessions, scheduling, and video conferencing integration.

---

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Configuration](#configuration)
  - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
  - [Authentication](#authentication)
  - [Users](#users)
  - [Tutors](#tutors)
  - [Sessions](#sessions)
  - [Time Slots](#time-slots)
  - [Categories](#categories)
  - [Languages](#languages)
  - [Comments](#comments)
  - [Daily (Video Rooms)](#daily-video-rooms)
- [Database Schema](#database-schema)
- [Security](#security)
- [Project Structure](#project-structure)
- [Email Notifications](#email-notifications)

---

## Overview

The Tutor Platform backend provides:

- **User Management** – Registration, login, and profile management for students and tutors.
- **Authentication** – JWT-based authentication with support for local login and Google OAuth2.
- **Tutor Profiles** – Tutors can set up profiles with bio, hourly rate, experience, categories, and video introductions.
- **Session Booking** – Students can book tutoring sessions with available tutors.
- **Time Slot Management** – Tutors define their weekly availability via time slots.
- **Video Conferencing** – Integration with [Daily.co](https://daily.co) for real-time video sessions.
- **Comments & Ratings** – Students can leave reviews and comments on tutors.
- **Email Notifications** – Automated email notifications with retry scheduling for unsent messages.
- **Category & Language Management** – Organize tutors by subjects/categories and languages.

---

## Tech Stack

| Technology             | Version   | Purpose                          |
|------------------------|-----------|----------------------------------|
| Java                   | 17        | Programming Language             |
| Spring Boot            | 3.2.0     | Application Framework            |
| Spring Security        | 6.x       | Authentication & Authorization   |
| Spring Data JPA        | 3.x       | Database ORM                     |
| PostgreSQL             | Latest    | Relational Database              |
| JWT (jjwt)             | 0.11.5    | Token-based Authentication       |
| Google API Client      | 2.2.0     | Google OAuth2 Integration        |
| MapStruct              | 1.5.5     | Object Mapping                   |
| Lombok                 | 1.18.30   | Boilerplate Code Reduction       |
| Jakarta Mail           | 2.0.1     | Email Sending                    |
| Daily.co API           | —         | Video Conferencing               |
| Maven                  | —         | Build & Dependency Management    |

---

## Architecture

The project follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────────────────────────┐
│                  Controller Layer                │  ← REST API endpoints
├─────────────────────────────────────────────────┤
│                  Use Case Layer                  │  ← Business orchestration
├─────────────────────────────────────────────────┤
│                  Service Layer                   │  ← Business logic
├─────────────────────────────────────────────────┤
│               Persistence Layer                  │  ← JPA Repositories & Entities
├─────────────────────────────────────────────────┤
│                  PostgreSQL                      │  ← Database
└─────────────────────────────────────────────────┘
```

**Key Design Patterns:**

- **Use Case Pattern** – Business logic is organized into use cases (e.g., `SessionUseCase`, `TutorUseCase`).
- **DTO Pattern** – Data Transfer Objects for API request/response separation.
- **MapStruct Mappers** – Automatic mapping between entities and DTOs.
- **Generic Response Wrapper** – All API responses wrapped in `GenericResponseEntity<T>` for consistent format.
- **Global Exception Handling** – Centralized error handling with `@RestControllerAdvice`.

---

## Getting Started

### Prerequisites

- **Java 17** or higher
- **Maven 3.8+**
- **PostgreSQL** (running on `localhost:5432`)
- **Daily.co API Key** (for video conferencing features)

### Configuration

The application uses two configuration files:

#### `application.yml` (Primary)

```yaml
server:
  port: 8088

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        default_schema: tutor
        dialect: org.hibernate.dialect.PostgreSQLDialect

app:
  jwt:
    secret: <your-jwt-secret>
    expiration: 86400000   # 24 hours in ms
  cors:
    allowed-origins: http://localhost:4200

daily:
  api:
    key: <your-daily-api-key>
    base-url: https://api.daily.co/v1

google:
  client-id: <your-google-client-id>
```

#### Environment Variables (Recommended for Production)

| Variable                  | Description                          |
|---------------------------|--------------------------------------|
| `SPRING_DATASOURCE_URL`   | PostgreSQL connection URL            |
| `SPRING_DATASOURCE_USERNAME` | Database username                 |
| `SPRING_DATASOURCE_PASSWORD` | Database password                 |
| `APP_JWT_SECRET`          | JWT signing secret (256-bit hex)     |
| `DAILY_API_KEY`           | Daily.co API key                     |
| `GOOGLE_CLIENT_ID`        | Google OAuth2 client ID              |

### Database Setup

1. Install and start PostgreSQL.
2. Create a schema named `tutor` in the `postgres` database:

```sql
CREATE SCHEMA IF NOT EXISTS tutor;
```

3. The application uses `ddl-auto: update`, so tables will be created automatically on first run.

### Running the Application

```bash
# Clone the repository
git clone <repository-url>
cd tutor-platform/backend

# Build the project
mvn clean package -DskipTests

# Run the application
mvn spring-boot:run

# Or run the JAR directly
java -jar target/tutor-platform-1.0.0.jar
```

The server starts on **http://localhost:8088**.

---

## API Endpoints

All endpoints return a standardized response format:

```json
{
  "responseStatus": "0",
  "requestUUID": "unique-request-id",
  "data": { ... },
  "traceError": null
}
```

- `responseStatus: "0"` = Success
- `responseStatus: "-1"` = Error (with `traceError` message)

### Authentication

| Method | Endpoint              | Description                  | Auth Required |
|--------|-----------------------|------------------------------|---------------|
| POST   | `/api/auth/register`  | Register a new user          | No            |
| POST   | `/api/auth/login`     | Login with email & password  | No            |
| POST   | `/api/auth/google`    | Login with Google OAuth2     | No            |
| POST   | `/api/auth/upload-photo` | Upload profile photo      | No            |
| GET    | `/api/auth/me`        | Get current user profile     | Yes           |

#### Register Request

```json
{
  "email": "user@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "+1234567890",
  "userType": "STUDENT"
}
```

> `userType` can be: `STUDENT` or `TUTOR`

#### Login Request

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

#### Login Response

```json
{
  "responseStatus": "0",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "tokenType": "Bearer"
  }
}
```

#### Google Login Request

```json
{
  "idToken": "google-id-token-string"
}
```

### Users

| Method | Endpoint        | Description          | Auth Required |
|--------|-----------------|----------------------|---------------|
| POST   | `/api/users`    | Search/list users    | Yes           |

#### Search Request

```json
{
  "page": 0,
  "size": 10,
  "filters": [],
  "sorts": []
}
```

### Tutors

| Method | Endpoint              | Description                | Auth Required |
|--------|-----------------------|----------------------------|---------------|
| POST   | `/api/tutors/search`  | Search/list tutors         | Yes           |
| GET    | `/api/tutors/details?id={id}` | Get tutor details  | Yes           |
| POST   | `/api/tutors`         | Register as tutor (multipart) | Yes        |

### Sessions

| Method | Endpoint                        | Description                     | Auth Required |
|--------|---------------------------------|---------------------------------|---------------|
| POST   | `/api/sessions/search`          | Search sessions                 | Yes           |
| GET    | `/api/sessions/{id}`            | Get session by ID               | Yes           |
| POST   | `/api/sessions/create`          | Book a new session              | Yes           |
| DELETE | `/api/sessions/{id}`            | Cancel/delete a session         | Yes           |
| GET    | `/api/sessions/list/user?id={id}` | Get sessions by user         | Yes           |
| GET    | `/api/sessions/list/tutor?id={id}` | Get sessions by tutor       | Yes           |

#### Create Session Request

```json
{
  "tutorId": 1,
  "date": "2026-06-10",
  "startTime": "10:00",
  "endTime": "11:00"
}
```

### Time Slots

| Method | Endpoint                              | Description                        | Auth Required |
|--------|---------------------------------------|------------------------------------|---------------|
| POST   | `/api/time-slots/list`                | List all time slots                | Yes           |
| GET    | `/api/time-slots/tutor/{id}/timeslots`| Get tutor's time slots with dates  | Yes           |
| GET    | `/api/time-slots/tutor/timeslots`     | Get current tutor's time slots     | Yes           |
| GET    | `/api/time-slots/{id}`                | Get time slot by ID                | Yes           |
| POST   | `/api/time-slots/create`              | Create time slots (batch)          | Yes           |
| PUT    | `/api/time-slots/{id}`                | Update a time slot                 | Yes           |
| DELETE | `/api/time-slots/{id}`                | Delete a time slot                 | Yes           |

#### Create Time Slot Request (Array)

```json
[
  {
    "dayOfWeek": 1,
    "startTime": "09:00",
    "endTime": "10:00"
  },
  {
    "dayOfWeek": 1,
    "startTime": "10:00",
    "endTime": "11:00"
  }
]
```

> `dayOfWeek`: 1 = Monday, 2 = Tuesday, ..., 7 = Sunday

### Categories

| Method | Endpoint                | Description                      | Auth Required |
|--------|-------------------------|----------------------------------|---------------|
| POST   | `/api/categories/list`  | List all categories              | Yes           |
| POST   | `/api/categories/tutors`| List tutors by category          | Yes           |

### Languages

| Method | Endpoint             | Description            | Auth Required |
|--------|----------------------|------------------------|---------------|
| POST   | `/api/languages/list`| List all languages     | Yes           |
| GET    | `/api/languages/{id}`| Get language by ID     | Yes           |
| POST   | `/api/languages`     | Create a language      | Yes           |
| PUT    | `/api/languages/{id}`| Update a language      | Yes           |
| DELETE | `/api/languages/{id}`| Delete a language      | Yes           |

### Comments

| Method | Endpoint                      | Description              | Auth Required |
|--------|-------------------------------|--------------------------|---------------|
| POST   | `/api/comments/list`          | List all comments        | Yes           |
| GET    | `/api/comments/tutor/{tutorId}` | Get comments for tutor | Yes           |
| GET    | `/api/comments/{id}`          | Get comment by ID        | Yes           |
| POST   | `/api/comments`               | Create a comment         | Yes           |
| PUT    | `/api/comments/{id}`          | Update a comment         | Yes           |
| DELETE | `/api/comments/{id}`          | Delete a comment         | Yes           |

### Daily (Video Rooms)

| Method | Endpoint         | Description               | Auth Required |
|--------|------------------|---------------------------|---------------|
| POST   | `/api/daily/room` | Create/join a video room | Yes           |

---

## Database Schema

The application uses the `tutor` schema in PostgreSQL with the following entities:

### Entity Relationship Diagram

```
┌──────────────────┐       ┌──────────────────┐
│   UserProfile    │       │      Role        │
├──────────────────┤       ├──────────────────┤
│ id               │◄──┐   │ id               │
│ email            │   │   │ name             │
│ password         │   │   └──────────────────┘
│ firstName        │   │            ▲
│ lastName         │   │            │ (user_roles)
│ phoneNumber      │   ├────────────┘
│ enabled          │   │
│ userType         │   │   ┌──────────────────┐
│ provider         │   │   │      Tutor       │
│ image            │   │   ├──────────────────┤
│ jwtToken         │   ├──►│ id               │
└──────────────────┘   │   │ userProfile (FK) │
                       │   │ title            │
                       │   │ bio              │
                       │   │ experienceYears  │
                       │   │ hourlyRate       │
                       │   │ rating           │
                       │   │ category (FK)    │
                       │   └──────────────────┘
                       │            │
                       │            ▼
                       │   ┌──────────────────┐
                       │   │    TimeSlot      │
                       │   ├──────────────────┤
                       │   │ id               │
                       │   │ tutorId (FK)     │
                       │   │ startTime        │
                       │   │ endTime          │
                       │   │ dayOfWeek        │
                       │   └──────────────────┘
                       │
                       │   ┌──────────────────┐
                       │   │     Session      │
                       │   ├──────────────────┤
                       │   │ id               │
                       ├──►│ userProfileId(FK)│
                       │   │ tutorId (FK)     │
                       │   │ roomId           │
                       │   │ date             │
                       │   │ startTime        │
                       │   │ endTime          │
                       │   └──────────────────┘
                       │
                       │   ┌──────────────────┐
                       │   │     Comment      │
                       │   ├──────────────────┤
                       └──►│ id               │
                           │ content          │
                           │ tutor (FK)       │
                           │ userProfile (FK) │
                           └──────────────────┘

┌──────────────────┐
│    Category      │
├──────────────────┤
│ id               │
│ name             │
│ tutors (1:N)     │
└──────────────────┘
```

### Audit Fields (All Entities)

| Column       | Type           | Description                    |
|--------------|----------------|--------------------------------|
| `created_at` | `TIMESTAMP`    | Record creation timestamp      |
| `updated_at` | `TIMESTAMP`    | Last update timestamp          |
| `created_by` | `BIGINT`       | User ID who created the record |
| `updated_by` | `BIGINT`       | User ID who last updated       |
| `status`     | `INTEGER`      | Entity status flag             |
| `is_deleted` | `BOOLEAN`      | Soft delete flag               |

---

## Security

### Authentication Flow

1. **Local Login**: User registers with email/password → password hashed with BCrypt → JWT token issued on login.
2. **Google OAuth2**: User sends Google ID token → server verifies with Google API → JWT token issued.

### JWT Configuration

- **Algorithm**: HMAC-SHA256
- **Expiration**: 24 hours (86,400,000 ms)
- **Token Type**: Bearer

### Request Authentication

All requests (except `/api/auth/**` and `/api/public/**`) require a valid JWT token in the `Authorization` header:

```
Authorization: Bearer <jwt-token>
```

### Roles

| Role    | Description           |
|---------|-----------------------|
| `USER`  | Standard student user |
| `TUTOR` | Tutor account         |
| `ADMIN` | Platform administrator|

### CORS

Configured to allow requests from: `http://localhost:4200` (Angular frontend).

---

## Project Structure

```
src/main/java/com/tutor/
├── TutorPlatformApplication.java    # Spring Boot entry point
├── business/
│   ├── dto/                         # Business DTOs
│   ├── mapper/                      # MapStruct mappers
│   ├── service/                     # Service layer (business logic)
│   │   ├── AuthService.java
│   │   ├── GoogleAuthService.java
│   │   ├── TutorService.java
│   │   ├── SessionService.java
│   │   ├── TimeSlotService.java
│   │   ├── CategoryService.java
│   │   ├── CommentService.java
│   │   ├── LanguageService.java
│   │   ├── UserService.java
│   │   └── MsgNotificationService.java
│   └── usecase/                     # Use case orchestration
│       ├── TutorUseCase.java
│       ├── SessionUseCase.java
│       ├── TimeSlotUseCase.java
│       ├── CategoryUseCase.java
│       ├── CommentUseCase.java
│       ├── LanguageUseCase.java
│       └── UserUseCase.java
├── common/
│   ├── dto/                         # Shared DTOs
│   │   ├── GenericResponseEntity.java
│   │   ├── SearchRequest.java
│   │   ├── ResponseDataModel.java
│   │   └── FilterColumn.java
│   ├── MessageService.java          # Email notification service
│   └── CommonCriteria.java          # Dynamic query criteria
├── config/
│   ├── DailyConfig.java             # Daily.co REST client config
│   └── RequestFilter.java           # Request UUID filter
├── controller/
│   ├── AuthController.java
│   ├── TutorController.java
│   ├── SessionController.java
│   ├── TimeSlotController.java
│   ├── CategoryController.java
│   ├── CommentController.java
│   ├── LanguageController.java
│   ├── UserController.java
│   ├── DailyController.java
│   ├── request/                     # Request DTOs
│   └── response/                    # Response DTOs
├── dto/
│   └── DailyRoomResponse.java       # Daily.co API response
├── enums/
│   ├── Provider.java                # LOCAL, GOOGLE
│   ├── Roles.java                   # USER, TUTOR, ADMIN
│   ├── SessionStatusEnum.java       # Session status
│   └── UserType.java                # STUDENT, TUTOR
├── exception/
│   ├── BusinessException.java
│   ├── DataIntegrityException.java
│   ├── EntityNotFoundException.java
│   └── GlobalExceptionHandler.java  # Centralized error handling
├── persistance/
│   ├── entity/                      # JPA Entities
│   │   ├── UserProfile.java
│   │   ├── Tutor.java
│   │   ├── Session.java
│   │   ├── TimeSlot.java
│   │   ├── Category.java
│   │   ├── Comment.java
│   │   ├── Role.java
│   │   ├── LuLanguage.java
│   │   ├── MsgNotification.java
│   │   ├── BaseEntity.java
│   │   └── AuditableEntity.java
│   └── repository/                  # Spring Data JPA Repositories
│       ├── UserProfileRepository.java
│       ├── TutorRepository.java
│       ├── SessionRepository.java
│       ├── TimeSlotRepository.java
│       ├── CategoryRepository.java
│       ├── CommentRepository.java
│       ├── LanguageRepository.java
│       ├── RoleRepository.java
│       └── MsgNotificationRepository.java
├── schedule/
│   └── MsgNotificationSchedule.java # Retry unsent emails every 5 min
└── security/
    ├── SecurityConfig.java          # Spring Security configuration
    ├── JwtAuthenticationFilter.java # JWT token filter
    ├── JwtService.java              # JWT creation & validation
    ├── AppUserDetails.java          # Custom UserDetails
    ├── UserDetailsServiceImpl.java  # User loading from DB
    └── SecurityUtil.java            # Security utilities
```

---

## Email Notifications

The platform includes an email notification system:

- **SMTP Provider**: Gmail (`smtp.gmail.com:587`)
- **Retry Mechanism**: A scheduled task (`MsgNotificationSchedule`) retries unsent notifications every **5 minutes**.
- **Notifications Stored**: All notifications are persisted in the `MsgNotification` entity for audit and retry.

---

## Build & Deployment

### Build

```bash
mvn clean package -DskipTests
```

### Run Tests

```bash
mvn test
```

### Docker (Optional)

```dockerfile
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/tutor-platform-1.0.0.jar app.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "app.jar"]
```

```bash
docker build -t tutor-platform-backend .
docker run -p 8088:8088 tutor-platform-backend
```

---

## Frontend Integration

This backend is designed to work with an **Angular** frontend running on `http://localhost:4200`. The CORS configuration is pre-set for this origin.

### Auth Header Example (Frontend)

```typescript
const headers = new HttpHeaders({
  'Authorization': `Bearer ${token}`,
  'Content-Type': 'application/json'
});
```

---

## License

This project is developed for educational purposes.

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Open a Pull Request

