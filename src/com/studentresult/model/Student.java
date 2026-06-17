package com.studentresult.model;

/**
 * Student - Model class representing a student entity
 * Encapsulates all student-related data fields
 */
public class Student {

    private int    studentId;
    private String name;
    private String email;
    private String course;
    private int    semester;
    private String phone;

    // Default constructor
    public Student() {}

    // Parameterized constructor
    public Student(int studentId, String name, String email, String course, int semester, String phone) {
        this.studentId = studentId;
        this.name      = name;
        this.email     = email;
        this.course    = course;
        this.semester  = semester;
        this.phone     = phone;
    }

    // Constructor without ID (for insert operations)
    public Student(String name, String email, String course, int semester, String phone) {
        this.name      = name;
        this.email     = email;
        this.course    = course;
        this.semester  = semester;
        this.phone     = phone;
    }

    // Getters and Setters
    public int    getStudentId() { return studentId; }
    public void   setStudentId(int studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void   setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void   setEmail(String email) { this.email = email; }

    public String getCourse() { return course; }
    public void   setCourse(String course) { this.course = course; }

    public int    getSemester() { return semester; }
    public void   setSemester(int semester) { this.semester = semester; }

    public String getPhone() { return phone; }
    public void   setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return String.format("Student[ID=%d, Name=%s, Course=%s, Semester=%d]",
                studentId, name, course, semester);
    }
}
