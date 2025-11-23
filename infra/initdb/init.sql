-- init.sql: run in the database specified by POSTGRES_DB (e.g. garage_buddy)
-- create a simple marker table if not exists to ensure the DB is initialized
CREATE TABLE IF NOT EXISTS schema_initialization_marker (
  id serial PRIMARY KEY,
  created_at timestamptz DEFAULT now()
);
