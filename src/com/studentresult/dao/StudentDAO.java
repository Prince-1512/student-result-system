package com.studentresult.dao;

import com.studentresult.db.DatabaseConnection;
import com.studentresult.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDAO - Data Access Object for Student entity
 * Implements CRUD operations using JDBC
 */
public class StudentDAO {

    // ── CREATE ────────────────────────────────────────
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, email, course, semester, phone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getSemester());
            stmt.setString(5, student.getPhone());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    // ── READ ALL ──────────────────────────────────────
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY student_id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getInt("semester"),
                    rs.getString("phone")
                );
                students.add(s);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }
        return students;
    }

    // ── READ BY ID ────────────────────────────────────
    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getInt("semester"),
                    rs.getString("phone")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error fetching student: " + e.getMessage());
        }
        return null;
    }

    // ── SEARCH BY NAME ────────────────────────────────
    public List<Student> searchByName(String name) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course"),
                    rs.getInt("semester"),
                    rs.getString("phone")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error searching student: " + e.getMessage());
        }
        return students;
    }

    // ── UPDATE ────────────────────────────────────────
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, email=?, course=?, semester=?, phone=? WHERE student_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getSemester());
            stmt.setString(5, student.getPhone());
            stmt.setInt(6, student.getStudentId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }

    // ── DELETE ────────────────────────────────────────
    public boolean deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    // ── COUNT ─────────────────────────────────────────
    public int getTotalStudents() {
        String sql = "SELECT COUNT(*) FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.err.println("Error counting students: " + e.getMessage());
        }
        return 0;
    }
}
