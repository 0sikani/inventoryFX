package com.studentmanagementsystem.controller;

import com.studentmanagementsystem.model.Student;
import com.studentmanagementsystem.model.Academic;
import com.studentmanagementsystem.model.Request;
import com.studentmanagementsystem.service.ApiService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.layout.HBox;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController implements Initializable {
    
    @FXML private AnchorPane main_form;
    @FXML private Button closeBtn;
    @FXML private TabPane mainTabPane;
    
    // Student components
    @FXML private TextField studentSearchField;
    @FXML private Button searchStudentBtn;
    @FXML private Button clearStudentSearchBtn;
    @FXML private TextField studentName;
    @FXML private TextField studentEmail;
    @FXML private TextField studentPhone;
    @FXML private DatePicker studentBirthDate;
    @FXML private Button addStudentBtn;
    @FXML private Button updateStudentBtn;
    @FXML private Button clearStudentBtn;
    @FXML private TableView<Student> studentsTable;
    @FXML private TableColumn<Student, Long> colStudentId;
    @FXML private TableColumn<Student, String> colStudentName;
    @FXML private TableColumn<Student, String> colStudentEmail;
    @FXML private TableColumn<Student, String> colStudentPhone;
    @FXML private TableColumn<Student, String> colStudentBirthDate;
    @FXML private TableColumn<Student, String> colStudentActions;
    
    // Academic components
    @FXML private TextField academicYear;
    @FXML private TextField programme;
    @FXML private TextField certificateType;
    @FXML private TextField otherDocs;
    @FXML private Button addAcademicBtn;
    @FXML private Button updateAcademicBtn;
    @FXML private Button clearAcademicBtn;
    @FXML private TableView<Academic> academicTable;
    @FXML private TableColumn<Academic, Long> colAcademicId;
    @FXML private TableColumn<Academic, String> colAcademicYear;
    @FXML private TableColumn<Academic, String> colProgramme;
    @FXML private TableColumn<Academic, String> colCertificateType;
    @FXML private TableColumn<Academic, String> colOtherDocs;
    @FXML private TableColumn<Academic, String> colAcademicActions;
    
    // Request components
    @FXML private TextField studentIdField;
    @FXML private TextField academicIdField;
    @FXML private TextField requestNature;
    @FXML private TextField sendingAddress;
    @FXML private TextField receivingAddress;
    @FXML private ChoiceBox<String> requestStatus;
    @FXML private Button addRequestBtn;
    @FXML private Button updateRequestBtn;
    @FXML private Button clearRequestBtn;
    @FXML private TableView<Request> requestsTable;
    @FXML private TableColumn<Request, Long> colRequestId;
    @FXML private TableColumn<Request, Long> colStudentIdReq;
    @FXML private TableColumn<Request, Long> colAcademicIdReq;
    @FXML private TableColumn<Request, String> colRequestNature;
    @FXML private TableColumn<Request, String> colSendingAddress;
    @FXML private TableColumn<Request, String> colReceivingAddress;
    @FXML private TableColumn<Request, String> colRequestStatus;
    @FXML private TableColumn<Request, String> colRequestDate;
    @FXML private TableColumn<Request, String> colRequestActions;
    
    private ObservableList<Student> studentList;
    private ObservableList<Academic> academicList;
    private ObservableList<Request> requestList;
    
    private Student selectedStudent;
    private Academic selectedAcademic;
    private Request selectedRequest;
    private ApiService apiService;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apiService = new ApiService();
        initializeTables();
        initializeChoiceBoxes();
        setupTabListeners();
        setupTableListeners();
        loadStudents(); // Load initial data for first tab
    }
    
    private void initializeTables() {
        studentList = FXCollections.observableArrayList();
        academicList = FXCollections.observableArrayList();
        requestList = FXCollections.observableArrayList();
        
        studentsTable.setItems(studentList);
        academicTable.setItems(academicList);
        requestsTable.setItems(requestList);
        
        // Configure Student table columns with proper data binding
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colStudentPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colStudentBirthDate.setCellValueFactory(cellData -> {
            LocalDate birthDate = cellData.getValue().getBirthDate();
            return new SimpleStringProperty(birthDate != null ? birthDate.format(dateFormatter) : "");
        });
        
        // Configure Academic table columns
        colAcademicId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAcademicYear.setCellValueFactory(new PropertyValueFactory<>("academicYear"));
        colProgramme.setCellValueFactory(new PropertyValueFactory<>("programme"));
        colCertificateType.setCellValueFactory(new PropertyValueFactory<>("certificateType"));
        colOtherDocs.setCellValueFactory(new PropertyValueFactory<>("otherDocs"));
        
        // Configure Request table columns
        colRequestId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStudentIdReq.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colAcademicIdReq.setCellValueFactory(new PropertyValueFactory<>("academicId"));
        colRequestNature.setCellValueFactory(new PropertyValueFactory<>("requestNature"));
        colSendingAddress.setCellValueFactory(new PropertyValueFactory<>("sendingAddress"));
        colReceivingAddress.setCellValueFactory(new PropertyValueFactory<>("receivingAddress"));
        colRequestStatus.setCellValueFactory(new PropertyValueFactory<>("requestStatus"));
        colRequestDate.setCellValueFactory(cellData -> {
            LocalDateTime requestDate = cellData.getValue().getRequestDate();
            return new SimpleStringProperty(requestDate != null ? requestDate.format(dateTimeFormatter) : "");
        });
        
        // Add action buttons to all tables
        setupActionButtons();
    }
    
    private void setupActionButtons() {
        // Student table actions
        colStudentActions.setCellFactory(param -> new TableCell<Student, String>() {
            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");
            
            {
                editBtn.setOnAction(event -> {
                    Student student = getTableView().getItems().get(getIndex());
                    editStudent(student);
                });
                
                deleteBtn.setOnAction(event -> {
                    Student student = getTableView().getItems().get(getIndex());
                    deleteStudent(student.getId());
                });
                
                editBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 10px; -fx-padding: 5px;");
                deleteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 10px; -fx-padding: 5px;");
            }
            
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(5, editBtn, deleteBtn);
                    buttons.setStyle("-fx-alignment: center;");
                    setGraphic(buttons);
                }
            }
        });
        
        // Academic table actions
        colAcademicActions.setCellFactory(param -> new TableCell<Academic, String>() {
            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");
            
            {
                editBtn.setOnAction(event -> {
                    Academic academic = getTableView().getItems().get(getIndex());
                    editAcademic(academic);
                });
                
                deleteBtn.setOnAction(event -> {
                    Academic academic = getTableView().getItems().get(getIndex());
                    deleteAcademic(academic.getId());
                });
                
                editBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 10px; -fx-padding: 5px;");
                deleteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 10px; -fx-padding: 5px;");
            }
            
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(5, editBtn, deleteBtn);
                    buttons.setStyle("-fx-alignment: center;");
                    setGraphic(buttons);
                }
            }
        });
        
        // Request table actions
        colRequestActions.setCellFactory(param -> new TableCell<Request, String>() {
            private final Button editBtn = new Button("Edit");
            private final Button deleteBtn = new Button("Delete");
            
            {
                editBtn.setOnAction(event -> {
                    Request request = getTableView().getItems().get(getIndex());
                    editRequest(request);
                });
                
                deleteBtn.setOnAction(event -> {
                    Request request = getTableView().getItems().get(getIndex());
                    deleteRequest(request.getId());
                });
                
                editBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 10px; -fx-padding: 5px;");
                deleteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 10px; -fx-padding: 5px;");
            }
            
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(5, editBtn, deleteBtn);
                    buttons.setStyle("-fx-alignment: center;");
                    setGraphic(buttons);
                }
            }
        });
    }
    
    private void initializeChoiceBoxes() {
        requestStatus.setItems(FXCollections.observableArrayList(
            "PENDING", "APPROVED", "REJECTED", "PROCESSING", "COMPLETED"
        ));
        requestStatus.setValue("PENDING");
    }
    
    private void setupTabListeners() {
        mainTabPane.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldTab, newTab) -> {
                if (newTab != null) {
                    switch (newTab.getText()) {
                        case "Students":
                            loadStudents();
                            break;
                        case "Academic Records":
                            loadAcademicRecords();
                            break;
                        case "Requests":
                            loadRequests();
                            break;
                    }
                }
            });
    }
    
    private void setupTableListeners() {
        studentsTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                selectedStudent = newValue;
            });
            
        academicTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                selectedAcademic = newValue;
            });
            
        requestsTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                selectedRequest = newValue;
            });
    }
    
    // [Keep all your existing methods for addStudent, updateStudent, searchStudents, etc.]
    // Student Methods
    @FXML
    private void addStudent() {
        try {
            if (studentName.getText().isEmpty() || studentEmail.getText().isEmpty()) {
                showAlert("Error", "Please fill in required fields (Name and Email)", Alert.AlertType.ERROR);
                return;
            }
            
            Student student = new Student();
            student.setStudentName(studentName.getText());
            student.setEmail(studentEmail.getText());
            student.setPhoneNumber(studentPhone.getText());
            student.setBirthDate(studentBirthDate.getValue());
            
            Student createdStudent = apiService.createStudent(student);
            if (createdStudent != null) {
                showAlert("Success", "Student added successfully!", Alert.AlertType.INFORMATION);
                clearStudentForm();
                loadStudents();
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to add student: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void updateStudent() {
        if (selectedStudent == null) {
            showAlert("Warning", "Please select a student to update", Alert.AlertType.WARNING);
            return;
        }
        
        try {
            selectedStudent.setStudentName(studentName.getText());
            selectedStudent.setEmail(studentEmail.getText());
            selectedStudent.setPhoneNumber(studentPhone.getText());
            selectedStudent.setBirthDate(studentBirthDate.getValue());
            
            Student updatedStudent = apiService.updateStudent(selectedStudent.getId(), selectedStudent);
            if (updatedStudent != null) {
                showAlert("Success", "Student updated successfully!", Alert.AlertType.INFORMATION);
                clearStudentForm();
                loadStudents();
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to update student: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void searchStudents() {
        String searchTerm = studentSearchField.getText().trim();
        if (searchTerm.isEmpty()) {
            studentsTable.setItems(studentList);
            return;
        }
        
        ObservableList<Student> filteredList = FXCollections.observableArrayList();
        for (Student student : studentList) {
            if (student.getStudentName().toLowerCase().contains(searchTerm.toLowerCase()) || 
                student.getEmail().toLowerCase().contains(searchTerm.toLowerCase()) ||
                (student.getPhoneNumber() != null && student.getPhoneNumber().toLowerCase().contains(searchTerm.toLowerCase()))) {
                filteredList.add(student);
            }
        }
        studentsTable.setItems(filteredList);
    }
    
    @FXML
    private void clearStudentSearch() {
        studentSearchField.clear();
        studentsTable.setItems(studentList);
    }
    
    @FXML
    private void clearStudentForm() {
        studentName.clear();
        studentEmail.clear();
        studentPhone.clear();
        studentBirthDate.setValue(null);
        selectedStudent = null;
        updateStudentBtn.setDisable(true);
    }
    
    private void editStudent(Student student) {
        selectedStudent = student;
        studentName.setText(student.getStudentName());
        studentEmail.setText(student.getEmail());
        studentPhone.setText(student.getPhoneNumber());
        studentBirthDate.setValue(student.getBirthDate());
        updateStudentBtn.setDisable(false);
    }
    
    private void deleteStudent(Long studentId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Delete Student");
        alert.setContentText("Are you sure you want to delete this student?");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            boolean success = apiService.deleteStudent(studentId);
            if (success) {
                showAlert("Success", "Student deleted successfully!", Alert.AlertType.INFORMATION);
                loadStudents();
            } else {
                showAlert("Error", "Failed to delete student", Alert.AlertType.ERROR);
            }
        }
    }
    
    // Academic Methods
    @FXML
    private void addAcademicRecord() {
        try {
            if (academicYear.getText().isEmpty() || programme.getText().isEmpty()) {
                showAlert("Error", "Please fill in required fields (Year and Programme)", Alert.AlertType.ERROR);
                return;
            }
            
            Academic academic = new Academic();
            academic.setAcademicYear(academicYear.getText());
            academic.setProgramme(programme.getText());
            academic.setCertificateType(certificateType.getText());
            academic.setOtherDocs(otherDocs.getText());
            
            Academic createdAcademic = apiService.createAcademicRecord(academic);
            if (createdAcademic != null) {
                showAlert("Success", "Academic record added successfully!", Alert.AlertType.INFORMATION);
                clearAcademicForm();
                loadAcademicRecords();
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to add academic record: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void updateAcademicRecord() {
        if (selectedAcademic == null) {
            showAlert("Warning", "Please select an academic record to update", Alert.AlertType.WARNING);
            return;
        }
        
        try {
            selectedAcademic.setAcademicYear(academicYear.getText());
            selectedAcademic.setProgramme(programme.getText());
            selectedAcademic.setCertificateType(certificateType.getText());
            selectedAcademic.setOtherDocs(otherDocs.getText());
            
            Academic updatedAcademic = apiService.updateAcademicRecord(selectedAcademic.getId(), selectedAcademic);
            if (updatedAcademic != null) {
                showAlert("Success", "Academic record updated successfully!", Alert.AlertType.INFORMATION);
                clearAcademicForm();
                loadAcademicRecords();
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to update academic record: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void clearAcademicForm() {
        academicYear.clear();
        programme.clear();
        certificateType.clear();
        otherDocs.clear();
        selectedAcademic = null;
        updateAcademicBtn.setDisable(true);
    }
    
    private void editAcademic(Academic academic) {
        selectedAcademic = academic;
        academicYear.setText(academic.getAcademicYear());
        programme.setText(academic.getProgramme());
        certificateType.setText(academic.getCertificateType());
        otherDocs.setText(academic.getOtherDocs());
        updateAcademicBtn.setDisable(false);
    }
    
    private void deleteAcademic(Long academicId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Delete Academic Record");
        alert.setContentText("Are you sure you want to delete this academic record?");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            boolean success = apiService.deleteAcademicRecord(academicId);
            if (success) {
                showAlert("Success", "Academic record deleted successfully!", Alert.AlertType.INFORMATION);
                loadAcademicRecords();
            } else {
                showAlert("Error", "Failed to delete academic record", Alert.AlertType.ERROR);
            }
        }
    }
    
    // Request Methods
    @FXML
    private void addRequest() {
        try {
            if (studentIdField.getText().isEmpty() || requestNature.getText().isEmpty()) {
                showAlert("Error", "Please fill in required fields (Student ID and Request Nature)", Alert.AlertType.ERROR);
                return;
            }
            
            Request request = new Request();
            request.setStudentId(Long.parseLong(studentIdField.getText()));
            request.setAcademicId(academicIdField.getText().isEmpty() ? null : Long.parseLong(academicIdField.getText()));
            request.setRequestNature(requestNature.getText());
            request.setSendingAddress(sendingAddress.getText());
            request.setReceivingAddress(receivingAddress.getText());
            request.setRequestStatus(requestStatus.getValue());
            request.setRequestDate(LocalDateTime.now());
            
            Request createdRequest = apiService.createRequest(request);
            if (createdRequest != null) {
                showAlert("Success", "Request submitted successfully!", Alert.AlertType.INFORMATION);
                clearRequestForm();
                loadRequests();
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numeric IDs", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Failed to submit request: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void updateRequest() {
        if (selectedRequest == null) {
            showAlert("Warning", "Please select a request to update", Alert.AlertType.WARNING);
            return;
        }
        
        try {
            selectedRequest.setStudentId(Long.parseLong(studentIdField.getText()));
            selectedRequest.setAcademicId(academicIdField.getText().isEmpty() ? null : Long.parseLong(academicIdField.getText()));
            selectedRequest.setRequestNature(requestNature.getText());
            selectedRequest.setSendingAddress(sendingAddress.getText());
            selectedRequest.setReceivingAddress(receivingAddress.getText());
            selectedRequest.setRequestStatus(requestStatus.getValue());
            
            Request updatedRequest = apiService.updateRequest(selectedRequest.getId(), selectedRequest);
            if (updatedRequest != null) {
                showAlert("Success", "Request updated successfully!", Alert.AlertType.INFORMATION);
                clearRequestForm();
                loadRequests();
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Please enter valid numeric IDs", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error", "Failed to update request: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void clearRequestForm() {
        studentIdField.clear();
        academicIdField.clear();
        requestNature.clear();
        sendingAddress.clear();
        receivingAddress.clear();
        requestStatus.setValue("PENDING");
        selectedRequest = null;
        updateRequestBtn.setDisable(true);
    }
    
    private void editRequest(Request request) {
        selectedRequest = request;
        studentIdField.setText(String.valueOf(request.getStudentId()));
        academicIdField.setText(request.getAcademicId() != null ? String.valueOf(request.getAcademicId()) : "");
        requestNature.setText(request.getRequestNature());
        sendingAddress.setText(request.getSendingAddress());
        receivingAddress.setText(request.getReceivingAddress());
        requestStatus.setValue(request.getRequestStatus());
        updateRequestBtn.setDisable(false);
    }
    
    private void deleteRequest(Long requestId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Delete Request");
        alert.setContentText("Are you sure you want to delete this request?");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
            boolean success = apiService.deleteRequest(requestId);
            if (success) {
                showAlert("Success", "Request deleted successfully!", Alert.AlertType.INFORMATION);
                loadRequests();
            } else {
                showAlert("Error", "Failed to delete request", Alert.AlertType.ERROR);
            }
        }
    }
    
    // Data Loading Methods
    private void loadStudents() {
        try {
            studentList.setAll(apiService.getAllStudents());
        } catch (Exception e) {
            showAlert("Error", "Failed to load students: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void loadAcademicRecords() {
        try {
            academicList.setAll(apiService.getAllAcademicRecords());
        } catch (Exception e) {
            showAlert("Error", "Failed to load academic records: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void loadRequests() {
        try {
            requestList.setAll(apiService.getAllRequests());
        } catch (Exception e) {
            showAlert("Error", "Failed to load requests: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    // Utility Methods
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void close() {
        System.exit(0);
    }
}