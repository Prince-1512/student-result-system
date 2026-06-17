package com.studentresult.util;

/**
 * Validator - Input validation methods
 * Used in service layer to validate user input before DB operations
 */
public class Validator {

    // Check if a string is null or blank
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    // Basic email format check
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) return false;
        return email.contains("@") && email.contains(".");
    }

    // Semester must be between 1 and 8
    public static boolean isValidSemester(int semester) {
        return semester >= 1 && semester <= 8;
    }

    // Marks cannot be negative or exceed total
    public static boolean isValidMarks(int obtained, int total) {
        return total > 0 && obtained >= 0 && obtained <= total;
    }

    // Phone number should be 10 digits
    public static boolean isValidPhone(String phone) {
        if (isEmpty(phone)) return false;
        return phone.matches("\\d{10}");
    }

    // Student ID must be positive
    public static boolean isValidId(int id) {
        return id > 0;
    }
}
