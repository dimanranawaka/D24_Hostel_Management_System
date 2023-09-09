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
//                iniUI("LoginForm.fxml");
                Navigator.pane.getChildren().add(FXMLLoader.load(Navigator.class.getResource("../view/LoginForm.fxml")));
                break;
            case FORGET:
                stage.setTitle("Forget Password");
//                iniUI("ForgetPasswordForm.fxml");
                Navigator.pane.getChildren().add(FXMLLoader.load(Navigator.class.getResource("../view/ForgetPasswordForm.fxml")));
                break;
            case DASHBOARD:
                stage.setTitle("DashBoard Form");
//                iniUI("DashBoardForm.fxml");
                Navigator.pane.getChildren().add(FXMLLoader.load(Navigator.class.getResource("../view/DashBoardForm.fxml")));
                break;
            case RECEPTION:
                stage.setTitle("Reception Form");
//                iniUI("ReceptionForm.fxml");
                Navigator.pane.getChildren().add(FXMLLoader.load(Navigator.class.getResource("../view/ReceptionForm.fxml")));
                break;
        }
    }
    private static void iniUI(String location) throws IOException {
        Navigator.pane.getChildren().add(FXMLLoader.load(Navigator.class.getResource("lk/ijse/D24_H_M_S/view/"+location)));
    }
}
