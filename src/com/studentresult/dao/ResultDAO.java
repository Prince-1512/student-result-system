package com.studentresult.dao;

import com.studentresult.db.DatabaseConnection;
import com.studentresult.model.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ResultDAO - Data Access Object for Result entity
 * Handles all result-related database operations
 */
public class ResultDAO {

    // ── ADD RESULT ────────────────────────────────────
    public boolean addResult(Result result) {
        String sql = "INSERT INTO results (student_id, subject, marks_obtained, total_marks, percentage, grade, exam_year, semester) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, result.getStudentId());
            stmt.setString(2, result.getSubject());
            stmt.setInt(3, result.getMarksObtained());
            stmt.setInt(4, result.getTotalMarks());
            stmt.setDouble(5, result.getPercentage());
            stmt.setString(6, result.getGrade());
            stmt.setString(7, result.getExamYear());
            stmt.setInt(8, result.getSemester());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding result: " + e.getMessage());
            return false;
        }
    }

    // ── GET RESULTS BY STUDENT ────────────────────────
    public List<Result> getResultsByStudentId(int studentId) {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT r.*, s.name as student_name FROM results r " +
                     "JOIN students s ON r.student_id = s.student_id " +
                     "WHERE r.student_id = ? ORDER BY r.semester, r.subject";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Result result = mapResultSet(rs);
                results.add(result);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching results: " + e.getMessage());
        }
        return results;
    }

    // ── GET ALL RESULTS ───────────────────────────────
    public List<Result> getAllResults() {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT r.*, s.name as student_name FROM results r " +
                     "JOIN students s ON r.student_id = s.student_id ORDER BY s.name";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                results.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all results: " + e.getMessage());
        }
        return results;
    }

    // ── GET AVERAGE PERCENTAGE ────────────────────────
    public double getAveragePercentage(int studentId) {
        String sql = "SELECT AVG(percentage) FROM results WHERE student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) return rs.getDouble(1);

        } catch (SQLException e) {
            System.err.println("Error calculating average: " + e.getMessage());
        }
        return 0.0;
    }

    // ── UPDATE RESULT ─────────────────────────────────
    public boolean updateResult(Result result) {
        String sql = "UPDATE results SET marks_obtained=?, total_marks=?, percentage=?, grade=? WHERE result_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, result.getMarksObtained());
            stmt.setInt(2, result.getTotalMarks());
            stmt.setDouble(3, result.getPercentage());
            stmt.setString(4, result.getGrade());
            stmt.setInt(5, result.getResultId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating result: " + e.getMessage());
            return false;
        }
    }

    // ── DELETE RESULT ─────────────────────────────────
    public boolean deleteResult(int resultId) {
        String sql = "DELETE FROM results WHERE result_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, resultId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting result: " + e.getMessage());
            return false;
        }
    }

    // ── TOP PERFORMERS ────────────────────────────────
    public List<Result> getTopPerformers(int limit) {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT r.*, s.name as student_name FROM results r " +
                     "JOIN students s ON r.student_id = s.student_id " +
                     "ORDER BY r.percentage DESC LIMIT ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) results.add(mapResultSet(rs));

        } catch (SQLException e) {
            System.err.println("Error fetching top performers: " + e.getMessage());
        }
        return results;
    }

    // ── HELPER: Map ResultSet to Result object ─────────
    private Result mapResultSet(ResultSet rs) throws SQLException {
        Result result = new Result();
        result.setResultId(rs.getInt("result_id"));
        result.setStudentId(rs.getInt("student_id"));
        result.setStudentName(rs.getString("student_name"));
        result.setSubject(rs.getString("subject"));
        result.setMarksObtained(rs.getInt("marks_obtained"));
        result.setTotalMarks(rs.getInt("total_marks"));
        result.setExamYear(rs.getString("exam_year"));
        result.setSemester(rs.getInt("semester"));
        return result;
    }
}
