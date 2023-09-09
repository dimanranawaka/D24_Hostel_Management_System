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

public class DashBoardFormController {
    public AnchorPane pane;
    public AnchorPane dashBoardPane;
    public JFXButton txtId1;
    public JFXButton txtId2;
    public JFXButton txtId3;
    public JFXButton txtId4;
    public JFXButton txtStudents;
    public JFXButton txtEmployees;
    public Text txtTime;
    public Text txtDate;



    private final DashBoardBO dashBoardBO = (DashBoardBO) BOFactory.getBoFactory().getBO(BOFactory.Types.DASHBOARD);

    public void initialize(){

        LocalDate date = LocalDate.now();
        txtDate.setText(String.valueOf(date));
        setTime();
        getCount1();
        getCount2();
        getCount3();
        getCount4();
        getStudentCount();
        getEmployeeCount();

    }

    public void setUi(String ui) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource(ui));
        dashBoardPane.getChildren().clear();
        dashBoardPane.getChildren().add(parent);

    }

    private void getStudentCount(){
        try{

            int studentCount = dashBoardBO.getStudentCount();
            txtStudents.setText(String.valueOf(studentCount));

        }catch (Exception e){

            e.printStackTrace();

        }
    }

    private void getEmployeeCount(){

        try {

            int employeeCount = dashBoardBO.getEmployeeCount();
            txtEmployees.setText(String.valueOf(employeeCount));

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
            txtId2.setText(String.valueOf(roomCount2));

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

            int roomCount4 = dashBoardBO.getRoomCount4();
            txtId1.setText(String.valueOf(roomCount4));

        }catch (Exception e){

            e.printStackTrace();

        }

    }

    public void loadLoginFormOnAction(ActionEvent actionEvent) throws IOException {

        Navigator.navigate(Routes.LOGIN,pane);

    }

    public void loadStudentOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/D24_H_M_S/view/ManageStudentForm.fxml");
    }

    public void loadDashBoardUiOnAction(ActionEvent actionEvent) throws IOException {

        Navigator.navigate(Routes.DASHBOARD,pane);

    }

    public void loadRegisterOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/D24_H_M_S/view/RegisterRoomForm.fxml");
    }

    public void loadRoomOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/D24_H_M_S/view/RoomForm.fxml");
    }

    public void loadEmployeeOnAction(ActionEvent actionEvent) throws IOException {

        setUi("/lk/ijse/D24_H_M_S/view/ManageEmployerForm.fxml");

    }

    public void loadReportOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/D24_H_M_S/view/ReservationForm.fxml");
    }

    public void openSettingPageOnAction(ActionEvent actionEvent) throws IOException {
        setUi("/lk/ijse/D24_H_M_S/view/ManageUserForm.fxml");
    }

    private void setTime(){

        Thread clock = new Thread() {

            public void run(){

                while (true){

                    DateFormat hour = new SimpleDateFormat("hh:mm:ss");
                    txtTime.setText(hour.format(new Date()));

                    try {

                        sleep(1000);

                    }catch (InterruptedException ex){

                    }
                }
            }

        };

        clock.start();

    }
}
