package lk.ijse.D24_H_M_S.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.D24_H_M_S.bo.BOFactory;
import lk.ijse.D24_H_M_S.bo.custom.UnpaidStudentBO;
import lk.ijse.D24_H_M_S.view.tdm.CustomTDM;

import java.io.IOException;

public class PaymentDueStudentFormController {
    public AnchorPane unpaidPane;
    public Text txtId;
    public Text txtName;
    public Text txtResId;
    public Text txtRoomId;
    public Text txtPaid;
    public TableView tblStudent;
    public TableColumn clmId;
    public TableColumn clmName;
    public TableColumn clmRId;
    public TableColumn clmRType;
    public TableColumn clmResId;
    public TableColumn clmStatus;

    private final UnpaidStudentBO unpaidStudentBO = (UnpaidStudentBO) BOFactory.getBoFactory().getBO(BOFactory.Types.UNPAID);

    public void initialize(){

        getStudent();
        setColvalues();

    }

    public void setUi(String ui) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(ui));

        unpaidPane.getChildren().clear();
        unpaidPane.getChildren().add(parent);

    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {

        setUi("/lk/ijse/D24_H_M_S/view/ManageStudentForm.fxml");

    }

    public void updateOnAction(ActionEvent actionEvent) {

        boolean update = unpaidStudentBO.updateStatus(txtResId.getText(), txtPaid.getText());

        if (update){

            getStudent();
            new Alert(Alert.AlertType.CONFIRMATION,"Updated Successfully").show();

        }else {

            new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();

        }

    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }

    public void loadText(MouseEvent mouseEvent) {

        ObservableList<CustomTDM> std = tblStudent.getSelectionModel().getSelectedItems();
        txtId.setText(std.get(0).getSId());
        txtName.setText(std.get(0).getName());
        txtResId.setText(std.get(0).getResId());
        txtRoomId.setText(std.get(0).getRId());

    }

    private void getStudent(){

        try {

            ObservableList<CustomTDM> unpaidStudents = unpaidStudentBO.getUnpaidStudents();
            tblStudent.setItems(unpaidStudents);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void setColvalues(){

        clmId.setCellValueFactory(new PropertyValueFactory("sId"));
        clmName.setCellValueFactory(new PropertyValueFactory("name"));
        clmRId.setCellValueFactory(new PropertyValueFactory("rId"));
        clmRType.setCellValueFactory(new PropertyValueFactory("type"));
        clmResId.setCellValueFactory(new PropertyValueFactory("resId"));
        clmStatus.setCellValueFactory(new PropertyValueFactory("status"));

    }
}
