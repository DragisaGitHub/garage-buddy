-- Flyway baseline migration: initial schema
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  name VARCHAR(100),
  created_at TIMESTAMP DEFAULT NOW(),
  deleted_at TIMESTAMP
);

CREATE TABLE cars (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL REFERENCES users(id),
  brand VARCHAR(100) NOT NULL,
  model VARCHAR(100) NOT NULL,
  year INTEGER NOT NULL,
  mileage INTEGER NOT NULL,
  vin VARCHAR(50) NOT NULL,
  registration_expiry DATE NOT NULL,
  purchase_price NUMERIC(12,2) NOT NULL,
  UNIQUE (user_id, vin)
);

CREATE TABLE service_events (
  id SERIAL PRIMARY KEY,
  car_id INTEGER NOT NULL REFERENCES cars(id),
  date DATE NOT NULL,
  mileage INTEGER NOT NULL,
  description VARCHAR(255),
  type VARCHAR(50) NOT NULL,
  cost NUMERIC(10,2) NOT NULL
);

CREATE TABLE reminder (
  id SERIAL PRIMARY KEY,
  car_id INTEGER NOT NULL REFERENCES cars(id),
  type VARCHAR(30) NOT NULL,
  due_date DATE NOT NULL,
  sent BOOLEAN DEFAULT FALSE
);

CREATE TABLE notification_preference (
  id SERIAL PRIMARY KEY,
  user_id INTEGER NOT NULL REFERENCES users(id),
  email_reminders BOOLEAN DEFAULT TRUE,
  dashboard_reminders BOOLEAN DEFAULT TRUE
);

CREATE INDEX idx_car_user ON cars(user_id);
CREATE INDEX idx_serviceevent_car ON service_events(car_id);
