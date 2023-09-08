package lk.ijse.D24_H_M_S.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import lk.ijse.D24_H_M_S.bo.BOFactory;
import lk.ijse.D24_H_M_S.bo.custom.UserBO;
import lk.ijse.D24_H_M_S.dto.UserDTO;
import lk.ijse.D24_H_M_S.view.tdm.UserTDM;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageUserFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField showPW;
    public TableView tblUser;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmEmail;
    public TableColumn clmRole;
    public JFXTextField showCMPW;
    public JFXComboBox cmbRole;
    public JFXTextField txtEmail;
    public JFXPasswordField txtCMPassword;
    public JFXPasswordField txtPassword;
    public JFXCheckBox cbxShowPW;

    private Matcher pwMatcher;
    private Matcher emailMatcher;

    private Matcher userNameMatcher;

    private Matcher userIdMatcher;

    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.Types.USER);

    // Initialize the form
    public void initialize(){

        getAllUser();
        setCellValue();
        showPW.setVisible(false);
        showCMPW.setVisible(false);

    }

    // Load user roles into the ComboBox
    private void loadRole(){

        ObservableList<String> role = FXCollections.observableArrayList();
        role.addAll("Admin","Reception");
        cmbRole.setItems(role);

    }

    // Handle actions when Enter is pressed while on the User ID field
    public void slipToName(ActionEvent actionEvent) {

        setPattern();
        if (!userIdMatcher.matches()){
            txtId.setFocusColor(Paint.valueOf("Red"));
        }else {
            txtId.setFocusColor(Paint.valueOf("Blue"));
            txtName.requestFocus();
        }

    }

    // Handle actions when Enter is pressed while on the Username field
    public void slipToEmail(ActionEvent actionEvent) {

        setPattern();

        if (!userNameMatcher.matches()){

            txtName.setFocusColor(Paint.valueOf("Red"));

        }else {
            txtName.setFocusColor(Paint.valueOf("Blue"));
            txtEmail.requestFocus();
        }

    }

    // Handle actions when the "Add" button is clicked
    public void addOnAction(ActionEvent actionEvent) {

        if (txtPassword.getText().equals(txtCMPassword.getText())){

            boolean add = userBO.addUser(new UserDTO(txtId.getId(), txtName.getText(),
                    txtEmail.getText(), txtPassword.getText(), (String) cmbRole.getValue()));

            if (add){

                clearText();
                getAllUser();
                new Alert(Alert.AlertType.CONFIRMATION,"User Added Successfully!").show();

            }else {

                clearText();
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();

            }
        }else {

            new Alert(Alert.AlertType.ERROR,"Password didn't match!").show();

        }

    }

    // Handle actions when the "Update" button is clicked
    public void updateOnAction(ActionEvent actionEvent) {

        UserDTO userDTO = userBO.searchUser(txtId.getText());
        userDTO.setName(txtName.getText());
        userDTO.setEmail(txtEmail.getText());
        userDTO.setPassword(txtPassword.getText());
        userDTO.setRole((String) cmbRole.getValue());

        if (txtPassword.getText() != null && txtPassword.getText().equals(txtCMPassword.getText())){

            // Attempt to update the user
            boolean update = userBO.updateUser(userDTO);

            if (update){

                clearText();
                getAllUser();

                new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully!").show();

            }else {

                clearText();
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();

            }

        }else {

            new Alert(Alert.AlertType.ERROR,"Invalid Password!").show();

        }

    }

    // Handle actions when the "Delete" button is clicked
    public void deleteOnAction(ActionEvent actionEvent) {

        UserDTO userDTO = userBO.searchUser(txtId.getText());
        boolean delete = userBO.deleteUser(userDTO);

        if (delete){

            clearText();
            getAllUser();
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted Successfully!");

        }else {

            clearText();
            new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();

        }

    }

    // Handle actions when Enter is pressed while on the Password field
    public void slipToPW(ActionEvent actionEvent) {
        setPattern();
        if (!emailMatcher.matches()){

            txtEmail.setFocusColor(Paint.valueOf("Red"));

        }else {
            txtEmail.setFocusColor(Paint.valueOf("Blue"));
            txtPassword.requestFocus();
        }
    }

    // Handle actions when Enter is pressed while on the Confirm Password field
    public void slipToCmPw(ActionEvent actionEvent) {

        setPattern();

        if (!pwMatcher.matches()){
            txtPassword.setFocusColor(Paint.valueOf("Red"));
        }else {
            txtPassword.setFocusColor(Paint.valueOf("Blue"));
            txtCMPassword.requestFocus();
        }

    }

    // Handle actions when Enter is pressed while on the Role ComboBox
    public void slipToRole(ActionEvent actionEvent) {
        cmbRole.requestFocus();
    }

    // Load user data into the TableView
    private void getAllUser(){

        try {

            ObservableList<UserTDM> allUser = userBO.getAllUser();
            tblUser.setItems(allUser);

        }catch (Exception e){

            System.out.println(e);

        }

    }

    // Clear input fields
    private void clearText(){

        txtId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtCMPassword.clear();

    }

    // Set cell value factory for TableView columns
    private void setCellValue(){

        clmId.setCellValueFactory(new PropertyValueFactory("uId"));
        clmName.setCellValueFactory(new PropertyValueFactory("name"));
        clmEmail.setCellValueFactory(new PropertyValueFactory("email"));
        cmbRole.setCellFactory(new PropertyValueFactory("role"));

    }

    // Load user data when a row is selected in the TableView
    public void loadText(MouseEvent mouseEvent) {

        ObservableList<UserTDM> user = tblUser.getSelectionModel().getSelectedItems();
        txtId.setText(user.get(0).getUId());
        txtName.setText(user.get(0).getName());
        txtEmail.setText(user.get(0).getEmail());

    }

    // Toggle visibility of password fields when "Show Password" checkbox is checked
    public void showPWOnAction(ActionEvent actionEvent) {

        if (cbxShowPW.isSelected()){

            showPW.setText(txtPassword.getText());
            showCMPW.setText(txtCMPassword.getText());
            txtPassword.setVisible(false);
            txtCMPassword.setVisible(false);

            showPW.setVisible(true);
            showCMPW.setVisible(true);

        }else {

            txtPassword.setText(showPW.getText());
            txtCMPassword.setText(showCMPW.getText());
            txtPassword.setVisible(true);
            txtCMPassword.setVisible(true);

            showPW.setVisible(false);
            showCMPW.setVisible(false);

        }
    }

    // Set patterns for user ID, username, email, and password
    private void setPattern(){

        Pattern userIdPattern = Pattern.compile("^(U0)([0-9]{1,})([1-9]{0,})$");
        userIdMatcher = userIdPattern.matcher(txtId.getText());

        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userIdPattern.matcher(txtName.getText());

        Pattern emailPattern = Pattern.compile("^([a-z0-9]{2,})([@])([a-z]{2,9})([.])([a-z]{2,})$");
        emailMatcher = emailPattern.matcher(txtEmail.getText());

        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        pwMatcher = passwordPattern.matcher(txtPassword.getText());

    }

}
