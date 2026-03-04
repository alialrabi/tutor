-- Run this once to set up sequences and seed data

-- Create schema
CREATE SCHEMA IF NOT EXISTS tutor;

-- Create sequences
CREATE SEQUENCE IF NOT EXISTS tutor.permissions_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS tutor.roles_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS tutor.users_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS tutor.tutor_seq START 1 INCREMENT 1;

-- Tables already defined in the schema DDL provided.
-- Run the DDL from the user's definition first, then seed data below:

-- Seed permissions
INSERT INTO tutor.permissions (name, description) VALUES
  ('READ_USERS',       'Can read user profiles'),
  ('WRITE_USERS',      'Can create/update user profiles'),
  ('READ_TUTORS',      'Can read tutor profiles'),
  ('WRITE_TUTORS',     'Can create/update tutor profiles'),
  ('MANAGE_ROLES',     'Can assign/remove roles'),
  ('MANAGE_PERMISSIONS','Can manage permissions')
ON CONFLICT (name) DO NOTHING;

-- Seed roles
INSERT INTO tutor.roles (name, description) VALUES
  ('ADMIN',   'System administrator'),
  ('TUTOR',   'Registered tutor'),
  ('STUDENT', 'Registered student')
ON CONFLICT (name) DO NOTHING;

-- Assign permissions to ADMIN role
INSERT INTO tutor.role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM tutor.roles r CROSS JOIN tutor.permissions p
WHERE r.name = 'ADMIN'
ON CONFLICT DO NOTHING;

-- Assign READ_TUTORS to STUDENT
INSERT INTO tutor.role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM tutor.roles r JOIN tutor.permissions p ON p.name IN ('READ_TUTORS', 'READ_USERS')
WHERE r.name = 'STUDENT'
ON CONFLICT DO NOTHING;

-- Assign tutor permissions to TUTOR role
INSERT INTO tutor.role_permissions (role_id, permission_id)
SELECT r.id, p.id
FROM tutor.roles r JOIN tutor.permissions p ON p.name IN ('READ_TUTORS', 'WRITE_TUTORS', 'READ_USERS')
WHERE r.name = 'TUTOR'
ON CONFLICT DO NOTHING;
