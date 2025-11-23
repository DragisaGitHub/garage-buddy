# Research for Garage Buddy Baseline Application

This document consolidates research findings and final technology choices for the implementation plan of the **Garage Buddy Baseline Application**.

---

## 1. Language / Runtime

### Decision
Java 21 (LTS) with Spring Boot 3.x as the primary backend stack.

### Rationale
- Strong ecosystem for REST APIs, security, data access, and scheduling.
- Very good support for layered architecture (config / domain / application / api / infrastructure).
- Excellent tooling (IntelliJ IDEA), testing libraries (JUnit 5, AssertJ, Mockito), and observability support.
- Fits well with the Garage Buddy Constitution requirements (testability, modularity, observability).

### Alternatives Considered
- **Node.js + NestJS**  
  - Pros: Fast iteration, good for TypeScript users.  
  - Cons: Team’s stronger experience is in Java; would require more setup for strict typing and enterprise constraints.
- **.NET / C#**  
  - Pros: Great for enterprise environments.  
  - Cons: Less aligned with current toolchain and personal experience.

---

## 2. Primary Dependencies

### 2.1 Web / API Backend

**Decision**  
- Spring Boot 3.x (Spring Web / Spring MVC) for REST API.

**Rationale**
- Standard way to build REST services in Java.
- Integration with Spring Security, Spring Data JPA, and Actuator.
- Easy to test using `@SpringBootTest` and slice tests.

**Alternatives Considered**
- Spring WebFlux (reactive): powerful, but adds complexity without clear need for this MVP.
- JAX-RS implementations (e.g., Quarkus, Micronaut): interesting, but Spring Boot has better support in current environment.

---

### 2.2 Database / ORM

**Decision**  
- Spring Data JPA with Hibernate over a relational database.

**Rationale**
- Natural fit for entity-based domain model (User, Car, ServiceEvent, Reminder, etc.).
- Simple repository abstractions for common CRUD operations.
- Widely used and well supported.

**Alternatives Considered**
- MyBatis / jOOQ: more control over SQL, but more boilerplate for this project.
- Pure JDBC: lightweight but more error-prone and harder to maintain.

---

### 2.3 PDF Generation

**Decision**  
- Use a Java PDF library such as iText or Apache PDFBox (exact choice can be finalized during implementation).

**Rationale**
- Both are mature libraries used widely in Java projects.
- Support for programmatic construction of documents based on templates.

**Alternatives Considered**
- Generating HTML and rendering via an external service or headless browser (more infrastructure).
- Exporting CSV/Excel only (does not fulfill the explicit “PDF history” requirement).

---

### 2.4 Notification / Reminder Libraries

**Decision**  
- Use Spring’s built-in scheduling support (`@Scheduled` / `TaskScheduler`) for periodic reminder checks.
- Use JavaMail sender or a pluggable notification interface for email or other channels.

**Rationale**
- Built-in scheduling is sufficient for the MVP.
- Simple abstraction allows switching to external queues (RabbitMQ, Kafka, etc.) later if needed.

**Alternatives Considered**
- Full-blown job schedulers (Quartz, external cron jobs) – more powerful, but overkill for first version.
- Managed cloud-based notification systems (SNS, SendGrid, etc.) – can be integrated later.

---

## 3. Storage

### Decision
- Use a relational database such as PostgreSQL or MariaDB.
- Core tables: `users`, `cars`, `service_events`, `reminders`, `notification_preferences`, and any additional support tables (e.g., recommendations cache if needed).

### Rationale
- Strong consistency model and easy querying for aggregated views (dashboard, summaries).
- Good fit for historical maintenance data and relational links between user, car, and events.

### Alternatives Considered
- NoSQL (e.g., MongoDB): more flexible schema, but no clear need for it here.
- Embedded database only (H2): useful for tests, but not for production.

---

## 4. Testing Framework

### Decision
- JUnit 5 for unit and integration tests.
- AssertJ for fluent assertions.
- Mockito or Spring’s built-in test utilities for mocking.
- Testcontainers for integration tests with a real database.

### Rationale
- Standard stack in Java / Spring ecosystem.
- Easy to integrate into CI pipelines.
- Testcontainers allows realistic DB tests without manual setup.

### Alternatives Considered
- Spock (Groovy): powerful but adds an additional language.
- Plain JUnit without Testcontainers: simpler but less realistic DB tests.

---

## 5. Target Platform

### Decision
- Primary target: Linux-based container environment (e.g., Docker images).
- Application will run as a Spring Boot fat JAR inside a container.

### Rationale
- Works well with modern deployment workflows (Docker, Kubernetes, cloud).
- Easy to reproduce in local development and CI.

### Alternatives Considered
- Direct deployment to an application server (Tomcat/Jetty): possible but less flexible than containerized approach.
- Windows-only deployment: too restrictive.

---

## 6. Project Structure

### Decision: Single backend service with a clean layered architecture

Project layout:

backend/  
- src/main/java/.../config  
- src/main/java/.../domain  
- src/main/java/.../application  
- src/main/java/.../api  
- src/main/java/.../infrastructure  
- src/test/java/... (mirrors main packages for tests)

### Rationale
- Clear separation between domain logic, application services, API layer, and infrastructure concerns (persistence, external integrations).
- Aligns with constitution principles around modularity and testability.
- Keeps the project simple enough for a single repository.

### Alternatives Considered
- Splitting into multiple services (e.g., “maintenance”, “notifications”) – premature for MVP.
- Adding frontend code into the same repository – possible but intentionally kept separate at this stage.

---

## 7. Performance & Scalability

### Decision
- Optimize for **small to medium** usage initially:
  - Hundreds of cars per user and thousands of service events.
- Focus on:
  - Efficient queries for dashboard views.
  - Proper indexing (by user, car, and date).
  - Avoiding N+1 query patterns in reports and dashboards.

### Rationale
- MVP will not handle massive fleet sizes; realistic initial usage is moderate.
- We still want clean query patterns that can scale further if needed.

### Alternatives Considered
- Introducing caching layers or message queues from day one: intentionally deferred until real load is observed.

---

## 8. Security & Privacy

### Decision
- Use Spring Security with:
  - Email/password authentication.
  - Password hashing (BCrypt).
  - Per-user access control (users can only see and manipulate their own data).
- Follow Garage Buddy Constitution:
  - No logging of sensitive data (passwords, tokens, personal identifiers).
  - Log retention window defined (e.g., 90 days) and configurable.
  - Support for user data export and deletion in line with data retention rules.

### Rationale
- Security and privacy are core to the constitution and to user trust.
- Spring Security is a proven solution that integrates well with the rest of the stack.

### Alternatives Considered
- External identity provider (OAuth2 / OpenID Connect): could be added later if needed.
- Custom security implementation: not recommended due to complexity and risk.

---

This research document now fully supports the implementation plan and tasks. Any future architectural or tooling changes should be reflected here and aligned with the Garage Buddy Constitution.