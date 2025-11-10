package com.studentmanagementsystem.service;

import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.model.Academic;
import com.studentmanagementsystem.model.Request;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

public class ApiService {
    private final String BASE_URL = "http://localhost:8080";
    private final RestTemplate restTemplate;
    
    public ApiService() {
        this.restTemplate = new RestTemplate();
        // Spring Boot's RestTemplate with Jackson should handle Java 8 dates automatically
        // in modern versions without needing explicit JavaTimeModule configuration
    }
    
    // Student methods
    public List<Student> getAllStudents() {
        try {
            ResponseEntity<List<Student>> response = restTemplate.exchange(
                BASE_URL + "/api/student",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error fetching students: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
    
    public Student createStudent(Student student) {
        try {
            return restTemplate.postForObject(BASE_URL + "/api/student", student, Student.class);
        } catch (Exception e) {
            System.err.println("Error creating student: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public Student updateStudent(Long id, Student student) {
        try {
            restTemplate.put(BASE_URL + "/api/student/" + id, student);
            return student;
        } catch (Exception e) {
            System.err.println("Error updating student: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean deleteStudent(Long id) {
        try {
            restTemplate.delete(BASE_URL + "/api/student/" + id);
            return true;
        } catch (Exception e) {
            System.err.println("Error deleting student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Academic methods
    public List<Academic> getAllAcademicRecords() {
        try {
            ResponseEntity<List<Academic>> response = restTemplate.exchange(
                BASE_URL + "/api/academic",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Academic>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error fetching academic records: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
    
    public Academic createAcademicRecord(Academic academic) {
        try {
            return restTemplate.postForObject(BASE_URL + "/api/academic", academic, Academic.class);
        } catch (Exception e) {
            System.err.println("Error creating academic record: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public Academic updateAcademicRecord(Long id, Academic academic) {
        try {
            restTemplate.put(BASE_URL + "/api/academic/" + id, academic);
            return academic;
        } catch (Exception e) {
            System.err.println("Error updating academic record: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean deleteAcademicRecord(Long id) {
        try {
            restTemplate.delete(BASE_URL + "/api/academic/" + id);
            return true;
        } catch (Exception e) {
            System.err.println("Error deleting academic record: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Request methods
    public List<Request> getAllRequests() {
        try {
            ResponseEntity<List<Request>> response = restTemplate.exchange(
                BASE_URL + "/api/request",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Request>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error fetching requests: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }
    
    public Request createRequest(Request request) {
        try {
            return restTemplate.postForObject(BASE_URL + "/api/request", request, Request.class);
        } catch (Exception e) {
            System.err.println("Error creating request: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public Request updateRequest(Long id, Request request) {
        try {
            restTemplate.put(BASE_URL + "/api/request/" + id, request);
            return request;
        } catch (Exception e) {
            System.err.println("Error updating request: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean deleteRequest(Long id) {
        try {
            restTemplate.delete(BASE_URL + "/api/request/" + id);
            return true;
        } catch (Exception e) {
            System.err.println("Error deleting request: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}