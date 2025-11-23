-- Flyway V1: create core tables

CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  name VARCHAR(255),
  created_at TIMESTAMP DEFAULT now(),
  deleted_at TIMESTAMP
);

CREATE TABLE cars (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
  brand VARCHAR(100),
  model VARCHAR(100),
  year INT,
  mileage INT DEFAULT 0,
  vin VARCHAR(50),
  registration_expiry DATE,
  purchase_price NUMERIC(12,2)
);

CREATE UNIQUE INDEX ux_cars_user_vin ON cars(user_id, vin);

CREATE TABLE service_events (
  id BIGSERIAL PRIMARY KEY,
  car_id BIGINT REFERENCES cars(id) ON DELETE CASCADE,
  date DATE,
  mileage INT,
  description TEXT,
  type VARCHAR(100),
  cost NUMERIC(12,2)
);

CREATE TABLE reminder (
  id BIGSERIAL PRIMARY KEY,
  car_id BIGINT REFERENCES cars(id) ON DELETE CASCADE,
  type VARCHAR(100),
  due_date DATE,
  sent BOOLEAN DEFAULT FALSE
);
