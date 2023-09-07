package lk.ijse.D24_H_M_S.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.custom.RoomBO;
import lk.ijse.D24_H_M_S.dao.DAOFactory;
import lk.ijse.D24_H_M_S.dao.custom.RoomDAO;
import lk.ijse.D24_H_M_S.dto.RoomDTO;
import lk.ijse.D24_H_M_S.entity.Room;
import lk.ijse.D24_H_M_S.util.FactoryConfiguration;
import lk.ijse.D24_H_M_S.view.tdm.RoomTDM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomBOImpl implements RoomBO {

    // Create a RoomDAO instance using the DAOFactory
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.ROOM);

    // Retrieve the Hibernate session from FactoryConfiguration
    @Override
    public Session getSession(){

        return FactoryConfiguration.getInstance().getSession();

    }

    // Add a room using a RoomDTO
    @Override
    public boolean addRoom(RoomDTO dto){

        Session session = getSession();

        Transaction transaction = session.beginTransaction();

        try {

            roomDAO.setSession(session);
            boolean save = roomDAO.save(new Room(dto.getRId(), dto.getType(), dto.getKeyMoney(), dto.getQty()));

            transaction.commit();
            session.close();
            return save;

        }catch (Exception e){

            System.out.println(e);
            transaction.rollback();
            session.close();
            return false;

        }
    }

    // Delete a room using a RoomDTO
    @Override
    public boolean deleteRoom(RoomDTO dto){

        Session session = getSession();

        Transaction transaction = session.beginTransaction();

        try {

            roomDAO.setSession(session);
            boolean delete = roomDAO.delete(new Room(dto.getRId(), dto.getType(), dto.getKeyMoney(), dto.getQty(), dto.getRoomDetails()));

            transaction.commit();
            session.close();
            return delete;

        } catch (Exception e){

            System.out.println(e);
            transaction.rollback();
            session.close();
            return false;

        }

    }

    // Search for a room by its ID and return a RoomDTO
    @Override
    public RoomDTO searchRoom(String id){

        try {

            Session session = getSession();
            roomDAO.setSession(session);
            Room room = roomDAO.get(id);
            session.close();
            return new RoomDTO(room.getRId(), room.getType(), room.getKeMoney(), room.getQty());

        }catch (Exception e){

            System.out.println(e);
            return null;

        }

    }

    // Update a room using a RoomDTO
    @Override
    public boolean updateRoom(RoomDTO dto){

        Session session = getSession();

        Transaction transaction = session.beginTransaction();

        try {

            roomDAO.setSession(session);
            boolean update = roomDAO.update(new Room(dto.getRId(), dto.getType(), dto.getKeyMoney(), dto.getQty()));
            transaction.commit();
            session.close();
            return update;

        }catch (Exception e){

            System.out.println(e);
            transaction.rollback();
            session.close();
            return false;

        }

    }

    // Retrieve a list of all rooms and convert them to RoomTDM objects
    @Override
    public ObservableList<RoomTDM> getAllRoom() {

        Session session = getSession();
        roomDAO.setSession(session);
        ObservableList<RoomTDM> rooms = FXCollections.observableArrayList();

        List<Room> all = roomDAO.getAll();

        for (Room room : all){

            rooms.add(new RoomTDM(room.getRId(), room.getType(), room.getKeMoney(), room.getQty()));

        }

        return rooms;

    }

}
