# Tasks: Garage Buddy Baseline Application

**Input**: Design documents from `/specs/001-number/`  
**Prerequisites**: `spec.md`, `plan.md`, `research.md`

---

## Phase 1: Project Setup (Shared Infrastructure)

Goal: Have a runnable Spring Boot backend with a clean structure ready for feature work.

- [ ] **T001** Create Spring Boot backend project (`backend/`) using Maven/Gradle with correct groupId/artifactId.
- [ ] **T002** Create base package structure:
  - `config/`
  - `domain/`
  - `application/`
  - `api/`
  - `infrastructure/`
- [ ] **T003** Add configuration files:
  - `application.yml` (dev + test profiles)
  - `logback-spring.xml`
- [ ] **T004** Configure build plugins & quality tools:
  - Java version  
  - Spotless / Checkstyle  
  - Basic static analysis
- [ ] **T005** Add unit test dependencies and verify a sample test passes.
- [ ] **T006** Create initial documentation: `README.md`, `quickstart.md`.

---

## Phase 2: Foundational Backend Infrastructure (Blocking)


Goal: Infrastructure required before any user story.

- [ ] **T010** Configure DB connectivity in `application.yml` (dev/test DB).
- [ ] **T011** Add Flyway/Liquibase with baseline migrations for:
  - `users`
  - `cars`
  - `service_events`
- [ ] **T012** Define JPA entities:
  - `User`
  - `Car`
  - `ServiceEvent`
- [ ] **T013** Create repositories using Spring Data JPA.
- [ ] **T014** Implement global exception handling (`@ControllerAdvice`) with standardized JSON responses.
- [ ] **T015** Implement structured logging (see T093 for security checklist):
  - no logging of passwords, tokens, or sensitive data
  - include correlation IDs where useful
- [ ] **T016 [P]** Implement authentication using Spring Security:
  - email/password login  
  - BCrypt hashing  
  - registration & login endpoints  
  - JWT or session-based auth (matching plan.md)
- [ ] **T017 [P]** Implement authorization:
  - enforce resource ownership consistently (`ownerId`)
  - users only access their own cars, service events, reminders
- [ ] **T018 [P]** Implement data retention configuration:
  - log retention period  
  - user data deletion hooks  
  - export behavior  
  **Acceptance Criteria**:
    - no PII stored longer than retention rule  
    - deletion/anonymization rules documented  
    - retention documented in `docs/data-retention.md`
- [ ] **T019** Add integration tests:
  - DB connectivity  
  - migrations apply cleanly  
  - basic registration + login flow works

---

## Phase 3: User Story 1 — Register & Manage Cars (P1)

Goal: User can register, edit, remove, and list cars.

- [ ] **T020 [P] [US1]** Extend `Car` entity (brand, model, year, mileage, VIN, regExpiry, purchasePrice).
- [ ] **T021 [US1]** Add strong validation (VIN uniqueness per user, numeric constraints).
- [ ] **T022 [US1]** Create Car DTOs (request/response) + mappers.
- [ ] **T023 [US1]** Implement `CarService`:
  - create/update/delete/list
- [ ] **T024 [US1]** REST endpoints:
  - POST /cars  
  - GET /cars  
  - PUT/PATCH /cars/{id}  
  - DELETE /cars/{id}
- [ ] **T025 [US1]** Enforce resource ownership (always via `ownerId = authenticatedUserId`).
- [ ] **T026 [US1]** Add projection for car list in dashboard backend.
- [ ] **T027 [US1]** Unit tests for `CarService`.
- [ ] **T028 [US1]** Integration tests for car endpoints.

---

## Phase 4: User Story 2 — Service Events (P2)

Goal: Add and view service events per car.

- [ ] **T030 [P] [US2]** Extend `ServiceEvent` entity with fields (date, mileage, description, type, cost).
- [ ] **T031 [US2]** Add validation (mileage progression, valid dates).
- [ ] **T032 [US2]** Implement `ServiceEventService`:
  - add/list/edit/delete events
- [ ] **T033 [US2]** REST endpoints:
  - POST /cars/{id}/service-events  
  - GET /cars/{id}/service-events
- [ ] **T034 [US2]** Add service history summary hook for dashboard.
- [ ] **T035 [US2]** Enforce resource ownership via car's `ownerId`.
- [ ] **T036 [US2]** Unit tests.
- [ ] **T037 [US2]** Integration tests.

---

## Phase 5: User Story 3 — Calculations & Insights (P3)

Goal: System generates cost metrics and deadlines.

- [ ] **T040 [P] [US3]** Define cost calculation domain logic:
  - total cost of ownership  
  - cost per km  
  - yearly summary
- [ ] **T041 [US3]** Implement `CostCalculationService`.
- [ ] **T042 [US3]** Implement deadline calculation (service intervals, registration dates).
- [ ] **T043 [US3]** Optimize repositories or queries where needed.
- [ ] **T044 [US3]** Expose calculated metrics via `/dashboard`.
- [ ] **T045 [US3]** Unit tests for all calculation logic.
- [ ] **T046 [US3]** Integration test for “car + events → dashboard metrics”.

---

## Phase 6: User Story 4 — Dashboard Health Indicators (P4)

- [ ] **T050 [P] [US4]** Define health indicator rules (green/yellow/red).
- [ ] **T051 [US4]** Implement `HealthIndicatorService`.
- [ ] **T052 [US4]** Add indicators to dashboard endpoint.
- [ ] **T053 [US4]** Performance evaluation (indexes, minimizing round-trips).
- [ ] **T054 [US4]** Unit tests.
- [ ] **T055 [US4]** Integration tests.

---

## Phase 7: User Story 5 — Recommendations (P5)

- [ ] **T060 [P] [US5]** Define recommendation rules.
- [ ] **T061 [US5]** Implement `RecommendationService`.
- [ ] **T062 [US5]** Add endpoint: `/cars/{id}/recommendations`.
- [ ] **T063 [US5]** Ensure deterministic behavior.  
  **Acceptance Criteria**:
    - same input always → same output  
    - rules documented and testable
- [ ] **T064 [US5]** Unit tests.
- [ ] **T065 [US5]** Integration tests for various car scenarios.

---

## Phase 8: User Story 6 — Export Maintenance History to PDF (P6)

- [ ] **T070 [P] [US6]** Select PDF library (iText, PDFBox).
- [ ] **T071 [US6]** Create PDF generator component (layout, multi-page support).
- [ ] **T072 [US6]** Add endpoint: GET `/cars/{id}/export` → PDF.
- [ ] **T073 [US6]** Enforce resource ownership (only car `ownerId` can export).
- [ ] **T074 [US6]** Unit tests (where possible).
- [ ] **T075 [US6]** Integration tests (PDF generated + structure).

---

## Phase 9: User Story 7 — Reminders & Notifications (P7)

Goal: Notifications for upcoming deadlines based on user preferences.

- [ ] **T080 [P] [US7]** Define reminder + notification preference model.
- [ ] **T081 [US7]** Add Reminder + NotificationPreference schema.
- [ ] **T082 [US7]** Implement scheduling logic.
- [ ] **T083 [US7]** Implement notification adapter (email or stub).
- [ ] **T084 [US7]** Implement scheduled background job for reminders/notifications.  
  **Acceptance Criteria**:
    - retries defined (see notification policy in spec.md)
    - failures logged without sensitive data
    - job idempotent
    - notification frequency and error handling match requirements
- [ ] **T085 [US7]** Add API endpoints to manage notification preferences.
- [ ] **T086 [US7]** Unit tests.
- [ ] **T087 [US7]** End-to-end test: deadline → reminder → notification.
  **Acceptance Criteria**:
    - test covers notification frequency, error handling, and user preferences

---

## Phase N: Cross-Cutting, Security, Data Retention

- [ ] **T090 [P]** Update all documentation (ensure all constitution-mandated docs are current):
  - `docs/`
  - `quickstart.md`
  - `research.md`
  - `data-retention.md`
  - `security.md`
  - `plan.md`
  - `spec.md`
- [ ] **T091** Code cleanup & refactoring.
  **Acceptance Criteria**:
    - no dead code, duplication, or unused dependencies
    - clear separation of layers as per constitution
    - code style matches formatter/static analysis rules
- [ ] **T092** Performance tests:
  - dashboard
  - recommendations
  - reminder job
  **Acceptance Criteria**:
    - endpoints meet p95 latency/throughput targets as defined in spec.md/plan.md (e.g., dashboard p95 < 500ms)
- [ ] **T093 [P] Security Hardening — Constitution Checklist**
  - authentication rules verified  
  - authorization rules verified  
  - full input validation  
  - zero sensitive data in logs  
  - brute-force protection (if defined in plan.md)  
  - update `security.md`
- [ ] **T094 [P] Data Retention & Privacy — Constitution Checklist**
  - log retention correctly applied  
  - user deletion behavior implemented  
  - export functionality compliant  
  - PII stored only within allowed retention window  
  - document in `docs/data-retention.md`
- [ ] **T095** Final end-to-end tests covering US1–US7.

---

## Dependencies & Execution Order

- **Phase 1 → Phase 2 → Phases 3–9 → Phase N**
- Authentication, authorization, and base domain layer (Phase 2) are mandatory before any user story.