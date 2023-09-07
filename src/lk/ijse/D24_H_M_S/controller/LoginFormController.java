package lk.ijse.D24_H_M_S.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import lk.ijse.D24_H_M_S.bo.BOFactory;
import lk.ijse.D24_H_M_S.bo.custom.LoginBO;
import lk.ijse.D24_H_M_S.util.Navigator;
import lk.ijse.D24_H_M_S.util.Routes;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane  pane;
    public JFXTextField txtUserName;
    
    public JFXCheckBox cbxShowPw;
    public JFXButton btnRegister;

    private final LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.Types.LOGIN);
    public JFXPasswordField txtPassword;
    public JFXTextField txtShowPW;

    public void initialize(){

        txtShowPW.setVisible(false);

    }

    public void slipToPassword(ActionEvent actionEvent) {

        txtPassword.requestFocus();

    }

    public void loadForgetOnAction(ActionEvent actionEvent) throws IOException {

        Navigator.navigate(Routes.FORGET,pane);

    }

    public void showOnAction(ActionEvent actionEvent) {

        if (cbxShowPw.isSelected()){

            txtShowPW.setVisible(true);
            txtShowPW.setText(txtPassword.getText());
            txtPassword.setVisible(false);

        }
        else {

            txtPassword.setVisible(true);
            txtPassword.setText(txtShowPW.getText());
            txtShowPW.setVisible(false);

        }

    }

    public void loadDashBoardOnAction(ActionEvent actionEvent) {

            try {

                String user = loginBO.checkUser(txtUserName.getText(), txtPassword.getText());

                if (user.equals("Admin")) {

                    Navigator.navigate(Routes.DASHBOARD, pane);

                }else if(user.equals("Reception")){

                    Navigator.navigate(Routes.RECEPTION, pane);

                } else if (user.equals("NO")) {

                    new Alert(Alert.AlertType.ERROR,"Invalid userName or Password !").show();

                }

            } catch (Exception e) {

                System.out.println(e);

                new Alert(Alert.AlertType.ERROR,"Invalid userName or Password !").show();
            }


    }

    public void slipToButton(ActionEvent actionEvent) {

        btnRegister.requestFocus();

    }
}
