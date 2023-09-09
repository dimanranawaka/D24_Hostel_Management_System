package lk.ijse.D24_H_M_S.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.ijse.D24_H_M_S.bo.BOFactory;
import lk.ijse.D24_H_M_S.bo.custom.ReservationBO;
import lk.ijse.D24_H_M_S.dto.CustomDTO;
import lk.ijse.D24_H_M_S.view.tdm.RoomTDM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterRoomFormController {
    public JFXTextField txtSID;
    public JFXTextField txtAddress;
    public JFXTextField txtSName;
    public JFXTextField txtContact;
    public JFXDatePicker txtDOB;
    public JFXComboBox cmbGender;
    public JFXComboBox cmbStatus;
    public JFXComboBox cmbRid;
    public TableView tblRoom;
    public JFXButton btnRegister;
    public TableColumn clmType;
    public TableColumn clmKMoney;
    public TableColumn clmQty;

    private Matcher telMatcher;
    private Matcher userNameMatcher;
    private Matcher address;
    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.Types.RESERVATION);
    public Label txtResId;

    public void initialize(){

        loadResId();
        loadRoomId();
        setCellVaue();

        ObservableList<String> gender = FXCollections.observableArrayList();
        gender.addAll("Male","Female");
        cmbGender.setItems(gender);

        ObservableList<String> status = FXCollections.observableArrayList();
        status.addAll("Paid","Not Paid");
        cmbStatus.setItems(status);

    }

    public void slipToName(ActionEvent actionEvent) {
        txtSName.requestFocus();
    }

    public void slipToContact(ActionEvent actionEvent) {
        setPattern();
        if (!address.matches()){
            txtAddress.setFocusColor(Paint.valueOf("red"));
            btnRegister.setDisable(true);
            btnRegister.setText("Something Went Wrong!");
            btnRegister.setStyle("-fx-alignment: red;");
        }else {

            txtAddress.setFocusColor(Paint.valueOf("blue"));
            txtContact.requestFocus();
            btnRegister.setDisable(false);
            btnRegister.setText("Register");
            btnRegister.setStyle("-fx-background-color: blue");

        }


    }

    public void slipToAddress(ActionEvent actionEvent) {

        setPattern();
        if (!userNameMatcher.matches()){

            txtSName.setFocusColor(Paint.valueOf("red"));
            btnRegister.setDisable(true);
            btnRegister.setText("Something Went Wrong!");
            btnRegister.setStyle("-fx-background-color: red;");

        }else {

            txtSName.setFocusColor(Paint.valueOf("blue"));
            txtAddress.requestFocus();
            btnRegister.setDisable(false);
            btnRegister.setText("Register!");
            btnRegister.setStyle("-fx-background-color: blue");

        }

    }

    public void slipToDOB(ActionEvent actionEvent) {

        setPattern();
        if (!telMatcher.matches()){

            txtContact.setFocusColor(Paint.valueOf("Red"));
            btnRegister.setDisable(true);
            btnRegister.setText("Something Went Wrong");
            btnRegister.setStyle("-fx-background-color: red");
        }else{

            txtContact.setFocusColor(Paint.valueOf("Blue"));
            txtDOB.requestFocus();
            btnRegister.setDisable(false);
            btnRegister.setText("Register");
            btnRegister.setStyle("-fx-background-color: blue");
        }

    }

    public void slipToGender(ActionEvent actionEvent) {
        cmbGender.requestFocus();
    }

    public void slipToRid(ActionEvent actionEvent) {
        cmbRid.requestFocus();
    }

    public void loadRoomOnAction(ActionEvent actionEvent) {

        try {

            ObservableList<RoomTDM> roomTDMS = reservationBO.loadRoom((String)cmbRid.getValue());
            tblRoom.setItems(roomTDMS);

        }catch (Exception e){
            System.out.println(e);
        }
        cmbStatus.requestFocus();
    }

    public void registerOnAction(ActionEvent actionEvent) throws NullPointerException{

        try {
            LocalDate date = LocalDate.now();
            boolean added = reservationBO.addReservation(new CustomDTO(txtSID.getText(), txtSName.getText(), txtAddress.getText(),
                    txtContact.getText(), txtDOB.getValue(), (String) cmbGender.getValue(), txtResId.getText(), date, (String) cmbStatus.getValue(), (String) cmbRid.getValue()));
            if (added){

                new Alert(Alert.AlertType.CONFIRMATION,"Room Registered!").show();
//                printBill();
                clearText();

            }else {

                new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();
            }
        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }


    private void loadResId(){

        try {
            String s = reservationBO.generateNextReservationId();
            txtResId.setText(s);
        }catch (Exception e){

            System.out.println(e);

        }

    }

    private void loadRoomId(){

        try {

            ObservableList<String> observableList = reservationBO.loadAllRid();
            cmbRid.setItems(observableList);

        }catch (Exception e){

            System.out.println(e);

        }

    }

    private void setCellVaue(){

        clmType.setCellValueFactory(new PropertyValueFactory("type"));
        clmKMoney.setCellValueFactory(new PropertyValueFactory("keyMoney"));
        clmQty.setCellValueFactory(new PropertyValueFactory("qty"));

    }

    private void setPattern() {
        // Set a regex pattern for validating the user contact
        Pattern telPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        telMatcher = telPattern.matcher(txtContact.getText());

        // Set a regex pattern for validating the username
        Pattern userNamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        userNameMatcher = userNamePattern.matcher(txtSName.getText());

        // Set a regex pattern for validating the address
        Pattern userAddress = Pattern.compile("^[a-zA-Z0-9]{3,}$");
        address = userAddress.matcher(txtAddress.getText());
    }
    private void clearText(){

        txtSID.clear();
        txtAddress.clear();
        txtSName.clear();
        txtContact.clear();

    }

    private void printBill(){

        HashMap bill = new HashMap();

        bill.put("roomId",String.valueOf(cmbRid.getValue()));
        bill.put("studentName",txtSName.getText());
        bill.put("paid",String.valueOf(cmbStatus.getValue()));

        try {

            InputStream resource = this.getClass().getResourceAsStream("lk/ijse/D24_H_M_S/view/bill/Blank_A4_1.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, bill, new JREmptyDataSource(1));

            JasperViewer.viewReport(jasperPrint,false);

        } catch (Exception e) {

            System.out.println(e);

        }
    }
}
