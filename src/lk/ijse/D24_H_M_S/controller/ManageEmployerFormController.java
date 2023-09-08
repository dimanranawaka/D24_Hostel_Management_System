package lk.ijse.D24_H_M_S.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.D24_H_M_S.bo.BOFactory;
import lk.ijse.D24_H_M_S.bo.custom.EmployeeBO;
import lk.ijse.D24_H_M_S.dto.EmployeeDTO;
import lk.ijse.D24_H_M_S.view.tdm.EmployeeTDM;

public class ManageEmployerFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public TableView tblEmployee;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmAddress;
    public TableColumn clmContact;
    public TableColumn clmRole;
    public JFXTextField txtRole;

    // Create an instance of the EmployeeBO
    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.Types.EMPLOYEE);

    public void initialize(){

        // Initialize the table and load data
        getAllEmployee();

        // Set cell value factories for the table columns
        setCellValueFactory();
    }

    public void slipToName(ActionEvent actionEvent) {

        // Set focus to the Name text field
        txtName.requestFocus();

    }

    public void slipToAddress(ActionEvent actionEvent) {

        // Set focus to the Address text field
        txtAddress.requestFocus();

    }

    public void slipToContact(ActionEvent actionEvent) {

        // Set focus to the Contact text field
        txtContact.requestFocus();

    }

    public void SlipToRole(ActionEvent actionEvent) {

        // Set focus to the Role text field
        txtRole.requestFocus();

    }

    public void addOnAction(ActionEvent actionEvent) {

        try {
            // Create and save a new Employee using the provided information
            boolean add = employeeBO.saveEmployee(new EmployeeDTO(txtId.getText(), txtName.getText(), txtAddress.getText(),
                    txtContact.getText(), txtRole.getText()));

            if (add){

                // Clear the text fields and show a success message
                clearText();
                new Alert(Alert.AlertType.CONFIRMATION,"Added Successfully!").show();

            }else {

                // Clear the text fields and show an error message
                clearText();
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();
            }
        }catch (Exception e){

            // Clear the text fields and log any exceptions
            clearText();
            System.out.println(e);

        }
    }

    public void updateOnAction(ActionEvent actionEvent) {

        // Retrieve the Employee based on the provided ID
        EmployeeDTO employeeDTO = employeeBO.searchEmployee(txtId.getText());

        // Update the Employee information
        employeeDTO.setName(txtName.getText());
        employeeDTO.setAddress(txtAddress.getText());
        employeeDTO.setContact(txtContact.getText());
        employeeDTO.setRole(txtRole.getText());

        // Update the Employee information and handle the result
        boolean update = employeeBO.updateEmployee(employeeDTO);

        if (update){

            // Clear the text fields and show a success message
            clearText();
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully!").show();

        }else {

            // Clear the text fields and show an error message
            clearText();
            new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();
        }

    }

    public void searchOnAction(ActionEvent actionEvent) {

        try {

            // Search for an Employee based on the provided ID
            EmployeeDTO employeeDTO = employeeBO.searchEmployee(txtId.getText());

            // Populate the text fields with the Employee information
            txtId.setText(employeeDTO.getEID());
            txtName.setText(employeeDTO.getName());
            txtAddress.setText(employeeDTO.getAddress());
            txtContact.setText(employeeDTO.getContact());
            txtRole.setText(employeeDTO.getRole());


        }catch (Exception e){

            // Show an error message for an invalid ID and log any exceptions
            new Alert(Alert.AlertType.ERROR,"Invalid ID!").show();
            System.out.println(e);

        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {

        // Search for an Employee based on the provided ID
        EmployeeDTO employeeDTO = employeeBO.searchEmployee(txtId.getText());

        // Delete the Employee and handle the result
        boolean delete = employeeBO.deleteEmployee(employeeDTO);

        if (delete){

            // Clear the text fields and show a success message
            clearText();
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted Successfully!").show();

        }else {

            // Clear the text fields and show an error message
            clearText();
            new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();

        }

    }

    private void clearText(){

        // Clear all text fields
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtRole.clear();

    }

    private void setCellValueFactory(){

        // Set cell value factories for the table columns
        clmId.setCellValueFactory(new PropertyValueFactory("eId"));
        clmName.setCellValueFactory(new PropertyValueFactory("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory("contact"));
        clmRole.setCellValueFactory(new PropertyValueFactory("role"));

    }

    private void getAllEmployee(){

        try {

            // Retrieve all employees and populate the table
            ObservableList<EmployeeTDM> allEmployee = employeeBO.getAllEmployee();
            tblEmployee.setItems(allEmployee);

        }catch (Exception e){

            // Log any exceptions
            System.out.println(e);

        }

    }
}
