-- Student Result Management System - Database Schema
-- Run this file in MySQL before starting the application

-- Create database
CREATE DATABASE IF NOT EXISTS student_result_db;
USE student_result_db;

-- Students table
CREATE TABLE IF NOT EXISTS students (
    student_id  INT          AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    course      VARCHAR(50)  NOT NULL,
    semester    INT          NOT NULL CHECK (semester BETWEEN 1 AND 8),
    phone       VARCHAR(15),
    created_at  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

-- Results table
CREATE TABLE IF NOT EXISTS results (
    result_id       INT            AUTO_INCREMENT PRIMARY KEY,
    student_id      INT            NOT NULL,
    subject         VARCHAR(100)   NOT NULL,
    marks_obtained  INT            NOT NULL,
    total_marks     INT            NOT NULL DEFAULT 100,
    percentage      DECIMAL(5, 2)  NOT NULL,
    grade           VARCHAR(5)     NOT NULL,
    exam_year       VARCHAR(10)    NOT NULL,
    semester        INT            NOT NULL,
    created_at      TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE
);

-- Sample data
INSERT INTO students (name, email, course, semester, phone) VALUES
('Prince Ranjan',   'prince@example.com',   'MCA', 4, '9876543210'),
('Anurag Kumar',    'anurag@example.com',   'MCA', 4, '9876543211'),
('Pratik Poddar',   'pratik@example.com',   'BCA', 6, '9876543212'),
('Rahul Sharma',    'rahul@example.com',    'MCA', 2, '9876543213'),
('Priya Singh',     'priya@example.com',    'BCA', 4, '9876543214');

INSERT INTO results (student_id, subject, marks_obtained, total_marks, percentage, grade, exam_year, semester) VALUES
(1, 'Data Structures',    85, 100, 85.00, 'A',  '2025', 3),
(1, 'Java Programming',   90, 100, 90.00, 'A+', '2025', 3),
(1, 'Database Management',78, 100, 78.00, 'B+', '2025', 3),
(2, 'Data Structures',    72, 100, 72.00, 'B+', '2025', 3),
(2, 'Java Programming',   68, 100, 68.00, 'B',  '2025', 3),
(3, 'Web Technology',     88, 100, 88.00, 'A',  '2025', 5),
(3, 'Operating System',   75, 100, 75.00, 'B+', '2025', 5);
