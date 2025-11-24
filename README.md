# Garage Buddy

Vehicle maintenance tracking application with backend (Spring Boot) and frontend (Vue 3).

## Prerequisites

- Docker & Docker Compose
- (Optional) JDK 21 and Maven for local backend development
- (Optional) Node.js 20+ for local frontend development

## Quick Start (Docker)

1. **Copy environment file**:
   ```bash
   cp .env.example .env
   # Edit .env and fill in real values (DB password, JWT secret, mail credentials)
   ```

2. **Build and run**:
   ```bash
   docker compose up --build
   ```

3. **Access**:
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - Health: http://localhost:8080/actuator/health

## Project Structure

```
garage-buddy/
├── backend/           # Spring Boot 3.5 (Java 21)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/garagebuddy/
│   │   │   │   ├── api/           # REST controllers
│   │   │   │   ├── application/   # Service layer
│   │   │   │   ├── domain/        # Entities & repositories
│   │   │   │   ├── security/      # JWT & auth
│   │   │   │   └── config/        # Spring configuration
│   │   │   └── resources/
│   │   │       ├── application.yml
│   │   │       ├── logback-spring.xml
│   │   │       └── db/migration/  # Flyway migrations
│   │   └── test/
│   ├── pom.xml
│   └── Dockerfile
├── frontend/          # Vue 3 + Vite + PrimeVue
│   ├── src/
│   │   ├── api/       # Axios API client
│   │   ├── pages/     # Login, Register, Dashboard, Cars, etc.
│   │   ├── store/     # Pinia state management
│   │   └── router/    # Vue Router
│   ├── package.json
│   └── Dockerfile
├── infra/
│   ├── initdb/        # DB init scripts
│   └── .env           # DB credentials (gitignored)
├── docker-compose.yml
└── .env.example       # Template for environment variables
```

## Development

### Backend (Local)

```bash
cd backend
cp .env.example .env
# Edit .env with local DB credentials
mvn spring-boot:run
```

### Frontend (Local)

```bash
cd frontend
cp .env.example .env
# Edit .env with VITE_API_URL=http://localhost:8080/api
npm install
npm run dev
```

### Run Tests

```bash
cd backend
mvn test
```

## Features

- ✅ User registration & JWT authentication
- ✅ Car profile management
- ✅ Service history tracking
- ✅ Maintenance reminders
- ✅ Intelligent recommendations (age/mileage/cost-based)
- ✅ Email notifications (optional, configurable)
- ✅ Dashboard analytics
- ✅ Flyway database migrations
- ✅ OpenAPI/Swagger documentation
- ✅ Constitution-compliant logging (90-day retention, no sensitive data)

## Configuration

### Environment Variables

See `.env.example` for all available configuration options:

- **Database**: `POSTGRES_USER`, `POSTGRES_PASSWORD`, `POSTGRES_DB`
- **JWT**: `GARAGEBUDDY_JWT_SECRET` (must be 256+ bits)
- **Mail**: `SPRING_MAIL_HOST`, `SPRING_MAIL_PORT`, `SPRING_MAIL_USERNAME`, `SPRING_MAIL_PASSWORD`
- **Reminders**: `GARAGEBUDDY_REMINDER_CRON` (default: daily at 7am)
- **Email Notifications**: `GARAGEBUDDY_EMAIL_ENABLED` (true/false)

### Frontend Environment

- **VITE_API_URL**: Backend API base URL
  - Local dev: `http://localhost:8080/api`
  - Docker Compose: `http://backend:8080/api`

## API Documentation

Once the backend is running, visit:

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Security

- All endpoints except `/api/auth/**` require JWT authentication
- Passwords hashed with BCrypt
- `@PreAuthorize` annotations enforce authorization
- User data isolation (users can only access their own cars/events)
- Sensitive data logging disabled (constitution compliant)

## CI/CD

GitHub Actions workflow (`.github/workflows/ci.yml`) runs on push:

- Backend: Maven build + tests
- Frontend: npm build

## License

Proprietary
