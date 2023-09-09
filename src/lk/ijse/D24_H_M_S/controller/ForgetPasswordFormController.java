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
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField showPW;
    public JFXTextField showConfirmPW;
    public JFXButton btnConfirmForget;
    public JFXCheckBox cbxShow;

    private final ForgetPasswordBO forgetPasswordBO = (ForgetPasswordBO) BOFactory.getBoFactory().getBO(BOFactory.Types.FORGET);
    public AnchorPane pane;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtCmpPw;
    private Matcher userIdMatcher;

    private Matcher userNameMatcher;

    private Matcher pwMatcher;


    public void initialize(){

        showPW.setVisible(false);
        showPW.setVisible(false);

    }

    public void loadLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        if(txtPassword.getText().equals(txtCmpPw.getText())){

            boolean update = forgetPasswordBO.forgetPassword(txtId.getText(), txtName.getText(), txtPassword.getText());

            if (update){

                new Alert(Alert.AlertType.CONFIRMATION,"Password Update!").show();
                Navigator.navigate(Routes.LOGIN,pane);

            }else{

                new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();

            }

        }else {

            new Alert(Alert.AlertType.WARNING,"Password didn't match!").show();

        }
    }

    public void slipToName(ActionEvent actionEvent) {

        setPattern();
        if (!userIdMatcher.matches()){

            txtId.setFocusColor(Paint.valueOf("Red"));

        }else {

            txtId.setFocusColor(Paint.valueOf("Blue"));
            txtName.requestFocus();

        }

    }

    public void slipToPassword(ActionEvent actionEvent) {

        setPattern();
        if (!userNameMatcher.matches()){

            txtName.setFocusColor(Paint.valueOf("Red"));

        }else {

            txtName.setFocusColor(Paint.valueOf("Blue"));
            txtPassword.requestFocus();

        }

    }

    public void showOnAction(ActionEvent actionEvent) {

        if (cbxShow.isSelected()){

            showPW.setVisible(true);
            showConfirmPW.setVisible(true);
            showPW.setText(txtPassword.getText());
            showConfirmPW.setText(txtCmpPw.getText());
            txtPassword.setVisible(false);
            txtCmpPw.setVisible(false);

        }else {

            txtPassword.setVisible(true);
            txtCmpPw.setVisible(true);
            txtPassword.setText(showPW.getText());
            txtCmpPw.setText(showConfirmPW.getText());
            showPW.setVisible(false);
            showConfirmPW.setVisible(false);

        }

    }

    public void loadLoginForm(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(Routes.LOGIN,pane);
    }

    public void slipTocmPw(ActionEvent actionEvent) {

        setPattern();
        if (!pwMatcher.matches()){

            txtPassword.setFocusColor(Paint.valueOf("Red"));

        }else {

            txtPassword.setFocusColor(Paint.valueOf("Blue"));
            txtCmpPw.requestFocus();

        }

    }

    public void slipToButton(ActionEvent actionEvent) {

        btnConfirmForget.requestFocus();

    }


    private void setPattern(){

        Pattern userIdPattern = Pattern.compile("^(U0)([0-9]{1,})([1-9]{0,})$");
        userIdMatcher = userIdPattern.matcher(txtId.getText());

        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userIdPattern.matcher(txtName.getText());


        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        pwMatcher = passwordPattern.matcher(txtPassword.getText());

    }

}
