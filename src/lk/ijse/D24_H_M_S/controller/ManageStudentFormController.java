package lk.ijse.D24_H_M_S.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.D24_H_M_S.bo.BOFactory;
import lk.ijse.D24_H_M_S.bo.custom.StudentBO;
import lk.ijse.D24_H_M_S.dto.StudentDTO;
import lk.ijse.D24_H_M_S.view.tdm.StudentTDM;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManageStudentFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public TableView tblStudent;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmAddress;
    public TableColumn clmContact;
    public TableColumn clmDOB;
    public TableColumn clmGender;
    public JFXTextField txtDOB;
    public JFXComboBox cmbGender;
    public Label lblGender;
    public AnchorPane studentPane;

    // Create a StudentBO instance using the BOFactory
    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.Types.STUDENT);

    // Initialize the controller
    public void initialize(){
        // Set cell values for the table, load student data, and populate gender combo box
        setCellValue();
        getAllStudent();

        ObservableList<String> gender = FXCollections.observableArrayList();
        gender.addAll("Male","Female");
        cmbGender.setItems(gender);
    }

    // Method to clear the name text field
    public void slipToName(ActionEvent actionEvent) {
        txtName.clear();
    }

    // Method to set focus to the address text field
    public void slipToAddress(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    // Method to set focus to the contact number text field
    public void slipToContact(ActionEvent actionEvent) {
        txtContact.requestFocus();
    }

    // Method to set focus to the date of birth text field
    public void SlipToDOB(ActionEvent actionEvent) {
        txtDOB.requestFocus();
    }

    // Method to handle the "Unpaid Students" button action
    public void unPaidStudentOnAction(ActionEvent actionEvent) throws IOException {
        // Load a different UI using the setUi method
        setUi("/lk/ijse/D24_H_M_S/view/PaymentDueStudentForm.fxml");
    }

    // Method to handle the update button action
    public void updateOnAction(ActionEvent actionEvent) {
        // Retrieve the student information and update it
        StudentDTO studentDTO = studentBO.searchStudent(txtId.getText());
        studentDTO.setName(txtName.getText());
        studentDTO.setAddress(txtAddress.getText());
        studentDTO.setContactNo(txtContact.getText());
        studentDTO.setGender((String) cmbGender.getValue());

        LocalDate dob = LocalDate.parse(txtDOB.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        studentDTO.setDate(dob);

        boolean update = studentBO.updateStudent(studentDTO);

        if (update){
            clearText();
            getAllStudent();
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully!").show();
        }else {
            clearText();
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
        }
    }

    // Method to handle the search button action
    public void searchOnAction(ActionEvent actionEvent) {
        // Retrieve and display student information by ID
        StudentDTO studentDTO = studentBO.searchStudent(txtId.getText());

        if (studentDTO != null){
            txtName.setText(studentDTO.getName());
            txtAddress.setText(studentDTO.getAddress());
            txtContact.setText(studentDTO.getContactNo());
            lblGender.setText(studentDTO.getGender());
            txtDOB.setText(String.valueOf(studentDTO.getDate()));
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid ID!").show();
        }
    }

    // Method to handle the delete button action
    public void deleteOnAction(ActionEvent actionEvent) {
        // Delete a student by ID
        StudentDTO studentDTO = studentBO.searchStudent(txtId.getText());
        boolean delete = studentBO.deleteStudent(studentDTO);

        if (delete){
            clearText();
            getAllStudent();
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted Successfully!").show();
        }else {
            clearText();
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
        }
    }

    // Method to set focus to the gender combo box
    public void slipToGender(ActionEvent actionEvent) {
        cmbGender.requestFocus();
    }

    // Method to retrieve and display all students
    private void getAllStudent(){
        try {
            ObservableList<StudentTDM> allStudent = studentBO.getAllStudent();
            tblStudent.setItems(allStudent);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    // Method to set cell values for the student table
    private void setCellValue(){
        clmId.setCellValueFactory(new PropertyValueFactory("sId"));
        clmName.setCellValueFactory(new PropertyValueFactory("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory("contactNo"));
        clmDOB.setCellValueFactory(new PropertyValueFactory("date"));
        clmGender.setCellValueFactory(new PropertyValueFactory("gender"));
    }

    // Method to clear all text fields and labels
    private void clearText(){
        txtName.clear();
        txtContact.clear();
        txtDOB.clear();
        txtAddress.clear();
        txtId.clear();
        lblGender.setText("");
    }

    // Method to load a different UI into the studentPane
    public void setUi(String ui) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource(ui));
        studentPane.getChildren().clear();
        studentPane.getChildren().add(load);
    }
}
