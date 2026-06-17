package com.studentresult.service;

import com.studentresult.dao.StudentDAO;
import com.studentresult.dao.ResultDAO;
import com.studentresult.model.Student;
import com.studentresult.model.Result;

import java.util.List;

/**
 * StudentService - Business logic layer
 * Sits between UI and DAO — handles validation and processing
 */
public class StudentService {

    private final StudentDAO studentDAO = new StudentDAO();
    private final ResultDAO  resultDAO  = new ResultDAO();

    // ── Student Operations ─────────────────────────────

    public boolean addStudent(String name, String email, String course, int semester, String phone) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Error: Student name cannot be empty.");
            return false;
        }
        if (!email.contains("@")) {
            System.out.println("Error: Invalid email address.");
            return false;
        }
        if (semester < 1 || semester > 8) {
            System.out.println("Error: Semester must be between 1 and 8.");
            return false;
        }

        Student student = new Student(name.trim(), email.trim(), course.trim(), semester, phone.trim());
        boolean success = studentDAO.addStudent(student);

        if (success) System.out.println("Student added successfully: " + name);
        else         System.out.println("Failed to add student.");

        return success;
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student getStudentById(int id) {
        Student s = studentDAO.getStudentById(id);
        if (s == null) System.out.println("No student found with ID: " + id);
        return s;
    }

    public List<Student> searchStudents(String name) {
        return studentDAO.searchByName(name);
    }

    public boolean updateStudent(int id, String name, String email, String course, int semester, String phone) {
        Student existing = studentDAO.getStudentById(id);
        if (existing == null) {
            System.out.println("Student not found.");
            return false;
        }

        existing.setName(name.trim());
        existing.setEmail(email.trim());
        existing.setCourse(course.trim());
        existing.setSemester(semester);
        existing.setPhone(phone.trim());

        boolean success = studentDAO.updateStudent(existing);
        if (success) System.out.println("Student updated successfully.");
        return success;
    }

    public boolean deleteStudent(int id) {
        boolean success = studentDAO.deleteStudent(id);
        if (success) System.out.println("Student deleted successfully.");
        else         System.out.println("Failed to delete student.");
        return success;
    }

    public int getTotalStudents() {
        return studentDAO.getTotalStudents();
    }

    // ── Result Operations ──────────────────────────────

    public boolean addResult(int studentId, String subject, int marksObtained, int totalMarks, String examYear, int semester) {
        if (studentDAO.getStudentById(studentId) == null) {
            System.out.println("Error: Student ID " + studentId + " not found.");
            return false;
        }
        if (marksObtained < 0 || marksObtained > totalMarks) {
            System.out.println("Error: Invalid marks. Marks obtained cannot exceed total marks.");
            return false;
        }
        if (totalMarks <= 0) {
            System.out.println("Error: Total marks must be greater than 0.");
            return false;
        }

        Result result = new Result(studentId, subject.trim(), marksObtained, totalMarks, examYear, semester);
        boolean success = resultDAO.addResult(result);

        if (success) {
            System.out.printf("Result added — Subject: %s | Marks: %d/%d | Grade: %s%n",
                    subject, marksObtained, totalMarks, result.getGrade());
        }
        return success;
    }

    public List<Result> getStudentResults(int studentId) {
        return resultDAO.getResultsByStudentId(studentId);
    }

    public List<Result> getAllResults() {
        return resultDAO.getAllResults();
    }

    public void printStudentReport(int studentId) {
        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        List<Result> results = resultDAO.getResultsByStudentId(studentId);
        double avg = resultDAO.getAveragePercentage(studentId);

        System.out.println("\n========================================");
        System.out.println("         STUDENT RESULT REPORT          ");
        System.out.println("========================================");
        System.out.printf("Name    : %s%n", student.getName());
        System.out.printf("Course  : %s | Semester: %d%n", student.getCourse(), student.getSemester());
        System.out.printf("Email   : %s%n", student.getEmail());
        System.out.println("----------------------------------------");
        System.out.printf("%-20s %-10s %-10s %-8s%n", "Subject", "Obtained", "Total", "Grade");
        System.out.println("----------------------------------------");

        for (Result r : results) {
            System.out.printf("%-20s %-10d %-10d %-8s%n",
                    r.getSubject(), r.getMarksObtained(), r.getTotalMarks(), r.getGrade());
        }

        System.out.println("----------------------------------------");
        System.out.printf("Average Percentage : %.2f%%%n", avg);
        System.out.println("========================================\n");
    }

    public List<Result> getTopPerformers(int limit) {
        return resultDAO.getTopPerformers(limit);
    }
}
