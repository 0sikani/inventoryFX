package com.studentmanagementsystem.model;

import java.time.LocalDate;

public class Student {
    private Long id;
    private Long academicId;
    private String studentName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
    private String profilePicturePath;

    // Constructors
    public Student() {}

    public Student(Long id, Long academicId, String studentName, LocalDate birthDate, String email, String phoneNumber, String profilePicturePath) {
        this.id = id;
        this.academicId = academicId;
        this.studentName = studentName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profilePicturePath = profilePicturePath;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAcademicId() { return academicId; }
    public void setAcademicId(Long academicId) { this.academicId = academicId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getProfilePicturePath() { return profilePicturePath; }
    public void setProfilePicturePath(String profilePicturePath) { this.profilePicturePath = profilePicturePath; }
}