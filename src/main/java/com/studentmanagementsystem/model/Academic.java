package com.studentmanagementsystem.model;

public class Academic {
    private Long id;
    private String academicYear;
    private String programme;
    private String certificateType;
    private String otherDocs;

    // Constructors
    public Academic() {}

    public Academic(Long id, String academicYear, String programme, String certificateType, String otherDocs) {
        this.id = id;
        this.academicYear = academicYear;
        this.programme = programme;
        this.certificateType = certificateType;
        this.otherDocs = otherDocs;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public String getProgramme() { return programme; }
    public void setProgramme(String programme) { this.programme = programme; }

    public String getCertificateType() { return certificateType; }
    public void setCertificateType(String certificateType) { this.certificateType = certificateType; }

    public String getOtherDocs() { return otherDocs; }
    public void setOtherDocs(String otherDocs) { this.otherDocs = otherDocs; }
}