package com.studentresult.util;

/**
 * DisplayUtil - Helper methods for console output formatting
 * Keeps UI code clean by centralizing print logic
 */
public class DisplayUtil {

    // Print a horizontal divider line
    public static void printDivider() {
        System.out.println("-".repeat(60));
    }

    // Print a thick header divider
    public static void printHeader(String title) {
        System.out.println("=".repeat(60));
        int padding = (60 - title.length()) / 2;
        System.out.println(" ".repeat(Math.max(0, padding)) + title);
        System.out.println("=".repeat(60));
    }

    // Print a section title
    public static void printSection(String title) {
        System.out.println("\n--- " + title + " ---");
    }

    // Print success message
    public static void success(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    // Print error message
    public static void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    // Print info message
    public static void info(String message) {
        System.out.println("[INFO] " + message);
    }

    // Convert numeric grade to description
    public static String gradeDescription(String grade) {
        return switch (grade) {
            case "A+" -> "Outstanding";
            case "A"  -> "Excellent";
            case "B+" -> "Very Good";
            case "B"  -> "Good";
            case "C"  -> "Average";
            case "D"  -> "Below Average";
            case "F"  -> "Fail";
            default   -> "Unknown";
        };
    }

    // Format percentage to 2 decimal places
    public static String formatPercentage(double percentage) {
        return String.format("%.2f%%", percentage);
    }

    // Check if a student passed (40% minimum)
    public static String passOrFail(double percentage) {
        return percentage >= 40 ? "PASS" : "FAIL";
    }
}
