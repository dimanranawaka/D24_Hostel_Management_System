package lk.ijse.D24_H_M_S.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.D24_H_M_S.bo.BOFactory;
import lk.ijse.D24_H_M_S.bo.custom.DashBoardBO;
import lk.ijse.D24_H_M_S.util.Navigator;
import lk.ijse.D24_H_M_S.util.Routes;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ReceptionFormController {
    public AnchorPane pane;
    public AnchorPane dashBordPane;
    public JFXButton txtId1;
    public JFXButton txtId2;
    public JFXButton txtId3;
    public JFXButton txtId4;
    public JFXButton txtStudent;
    public JFXButton txtEmployee;
    public Text txtDate;
    public Text txtTime;

    private final DashBoardBO dashBoardBO = (DashBoardBO) BOFactory.getBoFactory().getBO(BOFactory.Types.DASHBOARD);

    public void initialize(){
        // Initialize the dashboard view
        setTime();

        LocalDate date = LocalDate.now();

        txtDate.setText(String.valueOf(date));

        getCount1();

        getCount2();

        getCount3();

        getCount4();

        getStudentCount();

        getEmployeeCount();

    }

    public void loadDashbordUiOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/D24_H_M_S/view/DashBoardForm.fxml.fxml");
    }

    public void loadRegisterOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/D24_H_M_S/view/RegisterRoomForm.fxml");
    }

    public void loadStudentOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/D24_H_M_S/view/ManageStudentForm.fxml");
    }

    public void loadRoomOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/D24_H_M_S/view/RoomForm.fxml");
    }

    public void loadLoginFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigator.navigate(Routes.LOGIN,pane);
    }

    private void getStudentCount(){

        try {
            // Retrieve and display the student count from the dashboard BO
            int studentCount = dashBoardBO.getStudentCount();

            txtStudent.setText(String.valueOf(studentCount));


        }catch (Exception e){

            e.printStackTrace();

        }

    }

    private void getEmployeeCount(){

        try {
            // Retrieve and display the employee count from the dashboard BO
            int employeeCount = dashBoardBO.getEmployeeCount();

            txtEmployee.setText(String.valueOf(employeeCount));


        }catch (Exception e){

            e.printStackTrace();

        }

    }

    private void getCount1(){

        try {

            int roomCount1 = dashBoardBO.getRoomCount1();
            txtId1.setText(String.valueOf(roomCount1));

        }catch (Exception e){

            e.printStackTrace();

        }

    }

    private void getCount2(){

        try {

            int roomCount2 = dashBoardBO.getRoomCount2();
            txtId1.setText(String.valueOf(roomCount2));

        }catch (Exception e){

            e.printStackTrace();

        }

    }
    private void getCount3(){

        try {

            int roomCount3 = dashBoardBO.getRoomCount3();
            txtId1.setText(String.valueOf(roomCount3));

        }catch (Exception e){

            e.printStackTrace();

        }

    }

    private void getCount4(){

        try {
            // Retrieve and display the room count from the dashboard BO
            int roomCount4 = dashBoardBO.getRoomCount4();
            txtId1.setText(String.valueOf(roomCount4));

        }catch (Exception e){

            e.printStackTrace();

        }

    }

    private void setTime() {

        // Start a separate thread to continuously update and display the current time
        Thread clock = new Thread() {
            public void run() {
                while (true) {
                    DateFormat hour = new SimpleDateFormat("hh:mm:ss");
                    txtTime.setText(hour.format(new Date()));// Display the current time

                    try {
                        sleep(1000);// Sleep for 1 second before updating the time again
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        clock.start();
    }

    private void setUi(String ui) throws IOException {
        // Load and display a specified UI using a FXMLLoader
        Parent load = FXMLLoader.load(getClass().getResource(ui));
        dashBordPane.getChildren().clear();// Clear the current content
        dashBordPane.getChildren().add(load);// Add the new content


    }

}
