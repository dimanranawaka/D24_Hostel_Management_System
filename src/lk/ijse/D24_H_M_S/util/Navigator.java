package lk.ijse.D24_H_M_S.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    private static AnchorPane pane;

    public static void navigate(Routes routes, AnchorPane pane) throws IOException {
        Navigator.pane = pane;
        Navigator.pane.getChildren().clear();
        Stage stage = (Stage) Navigator.pane.getScene().getWindow();

        switch (routes){
            case LOGIN:
                stage.setTitle("Login Form");
                iniUI("LoginForm.fxml");
                break;
            case FORGET:
                stage.setTitle("Forget Password");
                iniUI("ForgetPasswordForm.fxml");
                break;
            case DASHBOARD:
                stage.setTitle("Reception Form");
                iniUI("ReceptionForm.fxml");
                break;
        }
    }
    private static void iniUI(String location) throws IOException {
        Navigator.pane.getChildren().add(FXMLLoader.load(Navigator.class.getResource("lk/ijse/D24_H_M_S/view/" + location)));
    }
}
