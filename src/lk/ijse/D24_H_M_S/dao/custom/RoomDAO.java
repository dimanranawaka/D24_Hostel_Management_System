package lk.ijse.D24_H_M_S.dao.custom;

import lk.ijse.D24_H_M_S.dao.CrudDAO;
import lk.ijse.D24_H_M_S.entity.Room;

import java.util.List;

public interface RoomDAO extends CrudDAO<Room , String> {

    // Retrieve a list of all rooms in the system
    List<Room> getAll();

    // Retrieve a list of room IDs in the system
    List<String> getRoomId();

    // Retrieve a list of rooms with a specific identifier (e.g., RM-1324)
    List<Room> getRoom1();

    // Retrieve a list of rooms with a specific identifier (e.g., RM-5467)
    List<Room> getRoom2();

    // Retrieve a list of rooms with a specific identifier (e.g., RM-7896)
    List<Room> getRoom3();

    // Retrieve a list of rooms with a specific identifier (e.g., RM-0093)
    List<Room> getRoom4();
}
