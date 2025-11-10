package com.studentmanagementsystem.model;

import java.time.LocalDateTime;

public class Request {
    private Long id;
    private Long studentId;
    private Long academicId;
    private LocalDateTime requestDate;
    private String requestNature;
    private String sendingAddress;
    private String receivingAddress;
    private String requestStatus;

    // Constructors
    public Request() {}

    public Request(Long id, Long studentId, Long academicId, LocalDateTime requestDate, String requestNature, String sendingAddress, String receivingAddress, String requestStatus) {
        this.id = id;
        this.studentId = studentId;
        this.academicId = academicId;
        this.requestDate = requestDate;
        this.requestNature = requestNature;
        this.sendingAddress = sendingAddress;
        this.receivingAddress = receivingAddress;
        this.requestStatus = requestStatus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getAcademicId() { return academicId; }
    public void setAcademicId(Long academicId) { this.academicId = academicId; }

    public LocalDateTime getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate; }

    public String getRequestNature() { return requestNature; }
    public void setRequestNature(String requestNature) { this.requestNature = requestNature; }

    public String getSendingAddress() { return sendingAddress; }
    public void setSendingAddress(String sendingAddress) { this.sendingAddress = sendingAddress; }

    public String getReceivingAddress() { return receivingAddress; }
    public void setReceivingAddress(String receivingAddress) { this.receivingAddress = receivingAddress; }

    public String getRequestStatus() { return requestStatus; }
    public void setRequestStatus(String requestStatus) { this.requestStatus = requestStatus; }
}