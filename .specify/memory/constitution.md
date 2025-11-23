# Garage Buddy Constitution

## Core Principles

### I. Simplicity-First Architecture
The system must remain simple, modular, and maintainable. Every added component must have a clear purpose and measurable value. Avoid unnecessary abstractions, premature optimization, and overengineering.

### II. Modular Service Design
Each major feature (cars, service events, recommendations, reminders, exports) must exist as an isolated service/module.  
Modules:
- Must be independently testable  
- Must have clear input/output boundaries  
- Must communicate through explicit contracts  
- Must not share state implicitly

### III. Test-First Development (Non-Negotiable)
Development follows strict TDD:
1. Write tests  
2. Validate tests with user/feature spec  
3. Confirm tests initially fail  
4. Implement functionality  
5. Refactor without breaking tests  

All features must include:
- Unit tests  
- Contract tests (where applicable)  
- Integration tests for multi-module flows  

### IV. CLI/API Interface Contract
Every service must expose functionality via:
- Python functions,
- and/or API (when backend is added),
- and/or CLI entrypoints (during early prototype phase).

All interactions must support:
- JSON input/output for machine use  
- Human-readable text for debugging  
- stdout for output, stderr for errors  

### V. Observability & Logging
All modules must include:
- Structured logging
- Context-rich debug logs for errors
- Minimal noise in production mode  
- No silent failures  

Error handling must be explicit and predictable.

### VI. Versioning & Backward Compatibility
Garage Buddy follows:
- **SemVer**: MAJOR.MINOR.PATCH  
- Breaking changes require:
  - Migration plan  
  - Version bump  
  - Update to contracts & tests  

Modules must avoid breaking interfaces without justification.

### VII. Performance & Reliability
The system must remain responsive and stable:
- CRUD operations < 150ms  
- Dashboard rendering < 300ms  
- Must support ≥ 10,000 users (future goal)  
- Must handle missing/incorrect data safely  

No blocking I/O inside core logic (use async when needed).

## Security & Data Requirements

### Authentication
Garage Buddy must support:
- Email/password authentication (initial)
- Expandable to OAuth2 in future

User data access must be restricted to the owning user (multi-tenant safety rule).

### Data Retention
Data retention rules:
- User data must remain until the user explicitly deletes the account
- Deleted cars must preserve their service history for export (unless user requests hard delete)
- Logs retained for 30 days max

### Privacy
- No sharing of user data  
- All sensitive fields must be validated and sanitized  
- VIN must be unique per user  
- Registration dates must be validated  

## Development Workflow & Quality Gates

### Code Workflow
Each feature branch must follow:
1. Feature spec
2. Research notes
3. Data model design
4. Contracts
5. Implementation
6. Tests
7. Documentation

### Pull Request Quality Gates
A PR cannot be merged unless:
- All tests pass  
- No new TODOs or placeholders  
- Constitution rules are satisfied  
- No warnings in linting  
- Coverage ≥ 80% for new code  

### Complexity Control
Any new abstraction (repository, service layer extension, helper module) must be justified in the Complexity Tracking table.

Breaking this rule requires:
- Written justification  
- Approval by maintainer (you)

## Governance

The Constitution supersedes all project practices.  
Any amendment requires:
- Update to this file  
- Version bump  
- Summary of the change  

Speckit must validate compliance before new features are planned.

**Version**: 1.0.0  
**Ratified**: 2025-11-23  
**Last Amended**: 2025-11-23