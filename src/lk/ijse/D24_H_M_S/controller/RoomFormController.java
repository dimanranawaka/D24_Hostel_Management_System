package lk.ijse.D24_H_M_S.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.D24_H_M_S.bo.BOFactory;
import lk.ijse.D24_H_M_S.bo.custom.RoomBO;
import lk.ijse.D24_H_M_S.dto.RoomDTO;
import lk.ijse.D24_H_M_S.view.tdm.RoomTDM;

public class RoomFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtMoney;
    public JFXTextField txtQty;
    public TableView tblRoom;
    public TableColumn clmId;
    public TableColumn clmType;
    public TableColumn clmMoney;
    public TableColumn clmQty;

    // Create a RoomBO instance using the BOFactory
    private final RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.Types.ROOM);

    public void initialize() {
        setCellValues(); // Initialize table column values
        getAllRooms(); // Load all rooms into the table
    }

    public void slipToType(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void slipToMoney(ActionEvent actionEvent) {
        txtMoney.requestFocus();
    }

    public void slipToQty(ActionEvent actionEvent) {
        txtQty.requestFocus();
    }

    public void addOnAction(ActionEvent actionEvent) {
        // Add a room using user input
        boolean add = roomBO.addRoom(new RoomDTO(txtId.getText(), txtName.getText(), txtMoney.getText(),
                Integer.parseInt(txtQty.getText())));

        if (add) {
            clearTextFields();
            getAllRooms();
            new Alert(Alert.AlertType.CONFIRMATION, "Room Added Successfully").show();
        } else {
            clearTextFields();
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        // Update a room using user input
        RoomDTO roomDTO = roomBO.searchRoom(txtId.getText());
        roomDTO.setType(txtName.getText());
        roomDTO.setKeyMoney(txtMoney.getText());
        roomDTO.setQty(Integer.parseInt(txtQty.getText()));

        boolean update = roomBO.updateRoom(roomDTO);

        if (update) {
            clearTextFields();
            getAllRooms();
            new Alert(Alert.AlertType.CONFIRMATION, "Updated Successfully!").show();
        } else {
            clearTextFields();
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        // Search for a room by ID and display its details
        RoomDTO roomDTO = roomBO.searchRoom(txtId.getText());

        if (roomDTO != null) {
            txtName.setText(roomDTO.getType());
            txtMoney.setText(roomDTO.getKeyMoney());
            txtQty.setText(String.valueOf(roomDTO.getQty()));
        } else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        // Delete a room using user input
        RoomDTO roomDTO = roomBO.searchRoom(txtId.getText());
        boolean delete = roomBO.deleteRoom(roomDTO);

        if (delete) {
            clearTextFields();
            getAllRooms();
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted Successfully").show();
        } else {
            clearTextFields();
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
        }
    }

    public void getAllRooms() {
        try {
            // Retrieve and display all rooms in the table
            ObservableList<RoomTDM> allRoom = roomBO.getAllRoom();
            tblRoom.setItems(allRoom);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void clearTextFields() {
        // Clear text fields
        txtId.clear();
        txtName.clear();
        txtMoney.clear();
        txtQty.clear();
    }

    private void setCellValues() {
        // Set cell values for table columns
        clmId.setCellValueFactory(new PropertyValueFactory("rId"));
        clmType.setCellValueFactory(new PropertyValueFactory("type"));
        clmMoney.setCellValueFactory(new PropertyValueFactory("keyMoney"));
        clmQty.setCellValueFactory(new PropertyValueFactory("qty"));
    }
}