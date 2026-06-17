package com.studentresult.model;

/**
 * Result - Model class representing a student's exam result
 * Contains subject-wise marks and calculated grade/percentage
 */
public class Result {

    private int    resultId;
    private int    studentId;
    private String studentName;  // for display purposes
    private String subject;
    private int    marksObtained;
    private int    totalMarks;
    private double percentage;
    private String grade;
    private String examYear;
    private int    semester;

    // Default constructor
    public Result() {}

    // Parameterized constructor
    public Result(int studentId, String subject, int marksObtained, int totalMarks, String examYear, int semester) {
        this.studentId    = studentId;
        this.subject      = subject;
        this.marksObtained = marksObtained;
        this.totalMarks   = totalMarks;
        this.examYear     = examYear;
        this.semester     = semester;
        this.percentage   = calculatePercentage();
        this.grade        = calculateGrade();
    }

    // Calculate percentage from marks
    private double calculatePercentage() {
        if (totalMarks == 0) return 0.0;
        return ((double) marksObtained / totalMarks) * 100;
    }

    // Assign grade based on percentage
    private String calculateGrade() {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B+";
        if (percentage >= 60) return "B";
        if (percentage >= 50) return "C";
        if (percentage >= 40) return "D";
        return "F";
    }

    // Getters and Setters
    public int    getResultId()      { return resultId; }
    public void   setResultId(int id){ this.resultId = id; }

    public int    getStudentId()     { return studentId; }
    public void   setStudentId(int id){ this.studentId = id; }

    public String getStudentName()   { return studentName; }
    public void   setStudentName(String name){ this.studentName = name; }

    public String getSubject()       { return subject; }
    public void   setSubject(String subject){ this.subject = subject; }

    public int    getMarksObtained() { return marksObtained; }
    public void   setMarksObtained(int marks){
        this.marksObtained = marks;
        this.percentage = calculatePercentage();
        this.grade = calculateGrade();
    }

    public int    getTotalMarks()    { return totalMarks; }
    public void   setTotalMarks(int total){
        this.totalMarks = total;
        this.percentage = calculatePercentage();
        this.grade = calculateGrade();
    }

    public double getPercentage()    { return percentage; }
    public String getGrade()         { return grade; }

    public String getExamYear()      { return examYear; }
    public void   setExamYear(String year){ this.examYear = year; }

    public int    getSemester()      { return semester; }
    public void   setSemester(int sem){ this.semester = sem; }

    @Override
    public String toString() {
        return String.format("Result[StudentID=%d, Subject=%s, Marks=%d/%d, Grade=%s]",
                studentId, subject, marksObtained, totalMarks, grade);
    }
}
