package lk.ijse.D24_H_M_S.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.D24_H_M_S.bo.BOFactory;
import lk.ijse.D24_H_M_S.bo.custom.ReservationBO;
import lk.ijse.D24_H_M_S.view.tdm.CustomTDM;

public class ReservationFormController {
    public TableView tblRes;
    public TableColumn clmStdId;
    public TableColumn clmName;
    public TableColumn clmRoomId;
    public TableColumn clmRType;
    public TableColumn clmResId;
    public TableColumn clmStatus;
    public TableColumn clmDate;

    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.Types.RESERVATION);

    public void initialize(){

        loadAll();
        setCellValues();

    }

    public void loadEmployeeReportOnAction(ActionEvent actionEvent) {

    }
    private void loadAll(){
        try {

            ObservableList<CustomTDM> allData = reservationBO.getAllData();
            tblRes.setItems(allData);

        }catch (Exception e){

            e.printStackTrace();

        }
    }

    private void setCellValues(){

        clmStdId.setCellValueFactory(new PropertyValueFactory<>("sId"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmRoomId.setCellValueFactory(new PropertyValueFactory<>("rId"));
        clmRType.setCellValueFactory(new PropertyValueFactory<>("type"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        clmResId.setCellValueFactory(new PropertyValueFactory<>("resId;"));

    }
}
