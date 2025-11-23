# Feature Specification: Garage Buddy Baseline Application

**Feature Branch**: `001-number`  
**Created**: 2025-11-23  
**Status**: Draft  
**Input**: User description: "Create a complete baseline specification for the entire Garage Buddy application. The system should allow users to: 1. Register one or more cars with details (brand, model, year, mileage, VIN, registration expiry, purchase price). 2. Add service events for each car (date, mileage, description, service type, cost). 3. Automatically calculate: - total cost of ownership, - cost per kilometer, - yearly maintenance summary, - upcoming service deadlines, - upcoming registration expiration. 4. Display a dashboard with all cars and their health indicators (green, yellow, red). 5. Provide recommendations such as: - whether the car is becoming too expensive to maintain, - whether selling is more cost-effective, - insights based on age and service history. 6. Allow exporting car maintenance history to PDF. 7. Provide reminders for upcoming servicing or registration deadlines."

---

## User Scenarios & Testing *(mandatory)*

### User Story 1 - Register and Manage Cars (Priority: P1)
A user registers one or more cars, entering details such as brand, model, year, mileage, VIN, registration expiry, and purchase price. The user can view, edit, or remove cars from their garage.

**Why this priority**: Core to the application's value; all other features depend on car registration and management.

**Independent Test**: User can add, edit, and remove cars, and see their details listed in the dashboard.

**Acceptance Scenarios:**
- **Given** a new user, **When** they add a car with all required details, **Then** the car appears in their garage list.
- **Given** a user with cars, **When** they edit or remove a car, **Then** the changes are reflected in the dashboard.

---

### User Story 2 - Add and Track Service Events (Priority: P2)
A user adds service events for each car, specifying date, mileage, description, service type, and cost. The system tracks all service history per car.

**Why this priority**: Enables maintenance tracking, which is essential for cost calculations and recommendations.

**Independent Test**: User can add service events to a car and view the full service history for each car.

**Acceptance Scenarios:**
- **Given** a car, **When** the user adds a service event, **Then** it appears in the car's service history.
- **Given** a car with service events, **When** the user views the car, **Then** all events are listed chronologically.

---

### User Story 3 - Automated Calculations & Insights (Priority: P3)
The system automatically calculates total cost of ownership, cost per kilometer, yearly maintenance summary, upcoming service deadlines, and registration expiration for each car.

**Why this priority**: Provides actionable insights and value beyond simple record-keeping.

**Independent Test**: Calculations update in real time as new data is entered; user can view summaries and upcoming deadlines.

**Acceptance Scenarios:**
- **Given** a car with service and purchase data, **When** the user views the dashboard, **Then** all calculated metrics are displayed.
- **Given** a car with an upcoming registration or service deadline, **When** the deadline approaches, **Then** the user is notified/reminded.

---

### User Story 4 - Dashboard & Health Indicators (Priority: P4)
The user sees a dashboard listing all cars, each with a health indicator (green, yellow, red) based on maintenance status, costs, and deadlines.

**Why this priority**: Enables quick assessment of car status and prioritization of actions.

**Independent Test**: User can see all cars and their health status at a glance; indicator changes as data changes.

**Acceptance Scenarios:**
- **Given** cars with varying maintenance and deadlines, **When** the user views the dashboard, **Then** each car shows the correct health indicator.

---

### User Story 5 - Recommendations & Insights (Priority: P5)
The system provides recommendations, such as whether a car is too expensive to maintain, if selling is more cost-effective, and insights based on age and service history.

**Why this priority**: Helps users make informed decisions about their vehicles.

**Independent Test**: User receives actionable recommendations based on their car's data.

**Acceptance Scenarios:**
- **Given** a car with high maintenance costs, **When** the user views recommendations, **Then** the system suggests considering selling or other actions.

---

### User Story 6 - Export Maintenance History (Priority: P6)
The user can export a car's maintenance history to PDF for record-keeping or resale purposes.

**Why this priority**: Supports documentation and increases resale value.

**Independent Test**: User can generate and download a PDF of a car's full maintenance history.

**Acceptance Scenarios:**
- **Given** a car with service history, **When** the user exports to PDF, **Then** a complete, formatted document is generated.

---

### User Story 7 - Reminders for Service & Registration (Priority: P7)
The system provides reminders for upcoming service or registration deadlines via dashboard notifications and/or email.

**Why this priority**: Prevents missed deadlines and supports proactive maintenance.

**Independent Test**: User receives timely reminders for all upcoming deadlines.

**Acceptance Scenarios:**
- **Given** a car with an upcoming deadline, **When** the deadline is near, **Then** the user is notified in advance.

---

### Edge Cases

- Duplicate VIN numbers for different cars.
- Invalid or missing car details (e.g., non-numeric mileage).
- Adding a service event with a date before purchase date.
- What if reminders are disabled?
- What happens when a car is sold?
- Extremely high mileage or cost values.
- Exporting a PDF with no service history.

---

## Functional Requirements (mandatory)

- **FR-001**: System MUST allow users to register, edit, and remove cars with all required details.
- **FR-002**: System MUST validate car details (e.g., VIN uniqueness, numeric fields).
- **FR-003**: Users MUST be able to add, edit, and remove service events for each car.
- **FR-004**: System MUST persist all car and service data securely.
- **FR-005**: System MUST automatically calculate cost metrics and deadlines.
- **FR-006**: System MUST display a dashboard with all cars and their health indicators.
- **FR-007**: System MUST provide recommendations based on data.
- **FR-008**: Users MUST be able to export maintenance history to PDF.
- **FR-009**: System MUST provide reminders for deadlines.
- **FR-010**: System MUST allow users to configure notification preferences.
- **FR-011**: Data privacy and security MUST be ensured.
- **FR-012**: User can own multiple cars.
- **FR-013**: Users can export data even after removing a car.
- **FR-014**: System MUST handle invalid data gracefully.
- **FR-015**: System MUST authenticate users via email/password (expandable to OAuth2 in future).
- **FR-016**: System MUST retain user data until explicit account deletion; deleted cars keep service history for export unless user requests hard delete; logs retained for 30 days max.

---

## Key Entities

- **User**: id, name, email, auth, preferences
- **Car**: id, userId, brand, model, year, mileage, VIN, expiry, price, status
- **ServiceEvent**: id, carId, date, mileage, description, type, cost
- **Dashboard**: cars, indicators, summaries
- **Recommendation**: carId, type, message
- **Reminder**: id, carId, date, type, notified

---

## Success Criteria

- **SC-001**: Register + first service event under 3 min.
- **SC-002**: 95% success rate adding service events.
- **SC-003**: 99% accurate health indicators.
- **SC-004**: 100% reminders sent 7 days before deadline.
- **SC-005**: 90% users rate recommendations useful.
- **SC-006**: PDF export under 30 seconds.
- **SC-007**: Support 10,000 users with no performance degradation.