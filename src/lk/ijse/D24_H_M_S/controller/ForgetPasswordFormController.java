package lk.ijse.D24_H_M_S.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.D24_H_M_S.bo.BOFactory;
import lk.ijse.D24_H_M_S.bo.custom.ForgetPasswordBO;
import lk.ijse.D24_H_M_S.util.Navigator;
import lk.ijse.D24_H_M_S.util.Routes;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPasswordFormController {
    // These are JavaFX UI components defined in your FXML file
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField showPW;
    public JFXTextField showConfirmPW;
    public JFXButton btnConfirmForget;
    public JFXCheckBox cbxShow;

    // You create an instance of the ForgetPasswordBO class using a factory
    private final ForgetPasswordBO forgetPasswordBO = (ForgetPasswordBO) BOFactory.getBoFactory().getBO(BOFactory.Types.FORGET);
    public AnchorPane pane;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtCmpPw;
    private Matcher userIdMatcher;
    private Matcher userNameMatcher;
    private Matcher pwMatcher;

    // This method is called when the controller is initialized
    public void initialize() {
        // Initially, you set two text fields (showPW and showConfirmPW) to be invisible
        showPW.setVisible(false);
        showPW.setVisible(false);
    }

    // This method is called when a button is clicked to load the login form
    public void loadLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        // Check if the entered passwords match
        if (txtPassword.getText().equals(txtCmpPw.getText())) {
            // Call the forgetPasswordBO to update the password
            boolean update = forgetPasswordBO.forgetPassword(txtId.getText(), txtName.getText(), txtPassword.getText());

            if (update) {
                // Show a confirmation dialog if the password is updated successfully
                new Alert(Alert.AlertType.CONFIRMATION, "Password Update!").show();
                // Navigate to the login page using the Navigator class
                Navigator.navigate(Routes.LOGIN, pane);
            } else {
                // Show an error message if something goes wrong during password update
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }
        } else {
            // Show a warning message if passwords don't match
            new Alert(Alert.AlertType.WARNING, "Password didn't match!").show();
        }
    }

    // This method is called when the focus moves to the "Name" field
    public void slipToName(ActionEvent actionEvent) {
        // Set a regex pattern for validating the user ID
        setPattern();
        if (!userIdMatcher.matches()) {
            // If the pattern doesn't match, set the focus color to red
            txtId.setFocusColor(Paint.valueOf("Red"));
        } else {
            // If the pattern matches, set the focus color to blue and move focus to the "Name" field
            txtId.setFocusColor(Paint.valueOf("Blue"));
            txtName.requestFocus();
        }
    }

    // This method is called when the focus moves to the "Password" field
    public void slipToPassword(ActionEvent actionEvent) {
        // Set a regex pattern for validating the user name
        setPattern();
        if (!userNameMatcher.matches()) {
            // If the pattern doesn't match, set the focus color to red
            txtName.setFocusColor(Paint.valueOf("Red"));
        } else {
            // If the pattern matches, set the focus color to blue and move focus to the "Password" field
            txtName.setFocusColor(Paint.valueOf("Blue"));
            txtPassword.requestFocus();
        }
    }

    // This method is called when the "Show" checkbox is clicked
    public void showOnAction(ActionEvent actionEvent) {
        if (cbxShow.isSelected()) {
            // If the checkbox is selected, show plain text passwords and hide password fields
            showPW.setVisible(true);
            showConfirmPW.setVisible(true);
            showPW.setText(txtPassword.getText());
            showConfirmPW.setText(txtCmpPw.getText());
            txtPassword.setVisible(false);
            txtCmpPw.setVisible(false);
        } else {
            // If the checkbox is not selected, hide plain text passwords and show password fields
            txtPassword.setVisible(true);
            txtCmpPw.setVisible(true);
            txtPassword.setText(showPW.getText());
            txtCmpPw.setText(showConfirmPW.getText());
            showPW.setVisible(false);
            showConfirmPW.setVisible(false);
        }
    }

    // This method is called to load the login form
    public void loadLoginForm(ActionEvent actionEvent) throws IOException {
        // Navigate to the login page using the Navigator class
        Navigator.navigate(Routes.LOGIN, pane);
    }

    // This method is called when the focus moves to the "Confirm Password" field
    public void slipTocmPw(ActionEvent actionEvent) {
        // Set a regex pattern for validating the password
        setPattern();
        if (!pwMatcher.matches()) {
            // If the pattern doesn't match, set the focus color to red
            txtPassword.setFocusColor(Paint.valueOf("Red"));
        } else {
            // If the pattern matches, set the focus color to blue and move focus to the "Confirm Password" field
            txtPassword.setFocusColor(Paint.valueOf("Blue"));
            txtCmpPw.requestFocus();
        }
    }

    // This method is called when the focus moves to the "Confirm" button
    public void slipToButton(ActionEvent actionEvent) {
        // Set focus on the "Confirm" button
        btnConfirmForget.requestFocus();
    }

    // This method sets regex patterns for validation
    private void setPattern() {
        // Set a regex pattern for validating the user ID
        Pattern userIdPattern = Pattern.compile("^(U0)([0-9]{1,})([1-9]{0,})$");
        userIdMatcher = userIdPattern.matcher(txtId.getText());

        // Set a regex pattern for validating the user name
        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userIdPattern.matcher(txtName.getText());

        // Set a regex pattern for validating the password
        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        pwMatcher = passwordPattern.matcher(txtPassword.getText());
    }
}

