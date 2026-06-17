package com.studentresult.ui;

import com.studentresult.model.Result;
import com.studentresult.model.Student;
import com.studentresult.service.StudentService;

import java.util.List;
import java.util.Scanner;

/**
 * MainMenu - Console-based UI for Student Result Management System
 * Provides interactive menu for all CRUD operations
 */
public class MainMenu {

    private static final StudentService service = new StudentService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  Student Result Management System     ");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1  -> studentMenu();
                case 2  -> resultMenu();
                case 3  -> reportsMenu();
                case 0  -> { running = false; System.out.println("Goodbye!"); }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // ── MAIN MENU ──────────────────────────────────────
    private static void printMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Student Management");
        System.out.println("2. Result Management");
        System.out.println("3. Reports");
        System.out.println("0. Exit");
    }

    // ── STUDENT MENU ───────────────────────────────────
    private static void studentMenu() {
        System.out.println("\n--- STUDENT MANAGEMENT ---");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("0. Back");

        int choice = readInt("Enter choice: ");
        switch (choice) {
            case 1  -> addStudent();
            case 2  -> viewAllStudents();
            case 3  -> searchStudent();
            case 4  -> updateStudent();
            case 5  -> deleteStudent();
            case 0  -> {}
            default -> System.out.println("Invalid choice.");
        }
    }

    // ── RESULT MENU ────────────────────────────────────
    private static void resultMenu() {
        System.out.println("\n--- RESULT MANAGEMENT ---");
        System.out.println("1. Add Result");
        System.out.println("2. View Student Results");
        System.out.println("3. View All Results");
        System.out.println("0. Back");

        int choice = readInt("Enter choice: ");
        switch (choice) {
            case 1  -> addResult();
            case 2  -> viewStudentResults();
            case 3  -> viewAllResults();
            case 0  -> {}
            default -> System.out.println("Invalid choice.");
        }
    }

    // ── REPORTS MENU ───────────────────────────────────
    private static void reportsMenu() {
        System.out.println("\n--- REPORTS ---");
        System.out.println("1. Student Result Report");
        System.out.println("2. Top Performers");
        System.out.println("3. Total Students Count");
        System.out.println("0. Back");

        int choice = readInt("Enter choice: ");
        switch (choice) {
            case 1  -> { int id = readInt("Enter Student ID: "); service.printStudentReport(id); }
            case 2  -> topPerformers();
            case 3  -> System.out.println("Total Students: " + service.getTotalStudents());
            case 0  -> {}
            default -> System.out.println("Invalid choice.");
        }
    }

    // ── STUDENT OPERATIONS ─────────────────────────────
    private static void addStudent() {
        System.out.println("\n-- Add New Student --");
        String name     = readString("Enter Name     : ");
        String email    = readString("Enter Email    : ");
        String course   = readString("Enter Course   : ");
        int    semester = readInt("Enter Semester : ");
        String phone    = readString("Enter Phone    : ");
        service.addStudent(name, email, course, semester, phone);
    }

    private static void viewAllStudents() {
        List<Student> students = service.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.printf("%n%-5s %-20s %-25s %-15s %-8s%n", "ID", "Name", "Email", "Course", "Sem");
        System.out.println("-".repeat(80));
        for (Student s : students) {
            System.out.printf("%-5d %-20s %-25s %-15s %-8d%n",
                    s.getStudentId(), s.getName(), s.getEmail(), s.getCourse(), s.getSemester());
        }
    }

    private static void searchStudent() {
        String name = readString("Enter name to search: ");
        List<Student> results = service.searchStudents(name);
        if (results.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : results) System.out.println(s);
    }

    private static void updateStudent() {
        int id = readInt("Enter Student ID to update: ");
        Student s = service.getStudentById(id);
        if (s == null) return;
        System.out.println("Current: " + s);
        String name     = readString("New Name     : ");
        String email    = readString("New Email    : ");
        String course   = readString("New Course   : ");
        int    semester = readInt("New Semester : ");
        String phone    = readString("New Phone    : ");
        service.updateStudent(id, name, email, course, semester, phone);
    }

    private static void deleteStudent() {
        int id = readInt("Enter Student ID to delete: ");
        System.out.print("Are you sure? (yes/no): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("yes")) service.deleteStudent(id);
        else System.out.println("Cancelled.");
    }

    // ── RESULT OPERATIONS ──────────────────────────────
    private static void addResult() {
        System.out.println("\n-- Add Result --");
        int    studentId     = readInt("Enter Student ID     : ");
        String subject       = readString("Enter Subject        : ");
        int    marksObtained = readInt("Enter Marks Obtained : ");
        int    totalMarks    = readInt("Enter Total Marks    : ");
        String examYear      = readString("Enter Exam Year      : ");
        int    semester      = readInt("Enter Semester       : ");
        service.addResult(studentId, subject, marksObtained, totalMarks, examYear, semester);
    }

    private static void viewStudentResults() {
        int id = readInt("Enter Student ID: ");
        List<Result> results = service.getStudentResults(id);
        if (results.isEmpty()) { System.out.println("No results found."); return; }
        System.out.printf("%n%-20s %-10s %-10s %-8s%n", "Subject", "Obtained", "Total", "Grade");
        System.out.println("-".repeat(50));
        for (Result r : results) {
            System.out.printf("%-20s %-10d %-10d %-8s%n",
                    r.getSubject(), r.getMarksObtained(), r.getTotalMarks(), r.getGrade());
        }
    }

    private static void viewAllResults() {
        List<Result> results = service.getAllResults();
        if (results.isEmpty()) { System.out.println("No results found."); return; }
        System.out.printf("%n%-20s %-20s %-10s %-8s%n", "Student", "Subject", "Marks", "Grade");
        System.out.println("-".repeat(65));
        for (Result r : results) {
            System.out.printf("%-20s %-20s %-10s %-8s%n",
                    r.getStudentName(), r.getSubject(),
                    r.getMarksObtained() + "/" + r.getTotalMarks(), r.getGrade());
        }
    }

    private static void topPerformers() {
        int limit = readInt("How many top performers to show? ");
        List<Result> top = service.getTopPerformers(limit);
        System.out.println("\n--- TOP PERFORMERS ---");
        for (int i = 0; i < top.size(); i++) {
            Result r = top.get(i);
            System.out.printf("%d. %-20s | %s | %.2f%% | %s%n",
                    i + 1, r.getStudentName(), r.getSubject(), r.getPercentage(), r.getGrade());
        }
    }

    // ── HELPER METHODS ─────────────────────────────────
    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
