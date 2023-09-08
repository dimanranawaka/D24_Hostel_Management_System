package lk.ijse.D24_H_M_S.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.SuperBO;
import lk.ijse.D24_H_M_S.dto.CustomDTO;
import lk.ijse.D24_H_M_S.view.tdm.CustomTDM;
import lk.ijse.D24_H_M_S.view.tdm.RoomTDM;

public interface ReservationBO extends SuperBO {

    boolean addReservation(CustomDTO customDTO);

    ObservableList<RoomTDM> loadRoom(String id);

    String generateNextReservationId();

    String newOrderID(String currentOrderId);

    ObservableList<String> loadAllRid();

    ObservableList<CustomTDM> getAllData();

}
