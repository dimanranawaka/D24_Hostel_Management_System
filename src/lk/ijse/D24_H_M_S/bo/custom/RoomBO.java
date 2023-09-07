package lk.ijse.D24_H_M_S.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.SuperBO;
import lk.ijse.D24_H_M_S.dto.RoomDTO;
import lk.ijse.D24_H_M_S.view.tdm.RoomTDM;

public interface RoomBO extends SuperBO {

    // Add a room using a RoomDTO
    boolean addRoom(RoomDTO dto);

    // Delete a room using a RoomDTO
    boolean deleteRoom(RoomDTO dto);

    // Search for a room by its ID and return a RoomDTO
    RoomDTO searchRoom(String id);

    // Update a room using a RoomDTO
    boolean updateRoom(RoomDTO dto);

    // Retrieve a list of all rooms and convert them to RoomTDM objects
    ObservableList<RoomTDM> getAllRoom();

}
