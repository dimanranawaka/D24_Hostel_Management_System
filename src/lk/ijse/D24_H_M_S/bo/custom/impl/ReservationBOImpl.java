package lk.ijse.D24_H_M_S.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.custom.ReservationBO;
import lk.ijse.D24_H_M_S.dao.DAOFactory;
import lk.ijse.D24_H_M_S.dao.custom.ReservationDAO;
import lk.ijse.D24_H_M_S.dao.custom.RoomDAO;
import lk.ijse.D24_H_M_S.dao.custom.StudentDAO;
import lk.ijse.D24_H_M_S.dto.CustomDTO;
import lk.ijse.D24_H_M_S.entity.Reservation;
import lk.ijse.D24_H_M_S.entity.Room;
import lk.ijse.D24_H_M_S.entity.Student;
import lk.ijse.D24_H_M_S.util.FactoryConfiguration;
import lk.ijse.D24_H_M_S.view.tdm.CustomTDM;
import lk.ijse.D24_H_M_S.view.tdm.RoomTDM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    // Create instances of DAOs for handling reservations, rooms, and students.
    private final ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.RESERVATION);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.ROOM);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.STUDENT);

    // Override the getSession method to retrieve a Hibernate session.
    @Override
    public Session getSession() {
        // Get a Hibernate session using FactoryConfiguration.getInstance().
        return FactoryConfiguration.getInstance().getSession();
    }

    // Override the addReservation method to add a new reservation.
    @Override
    public boolean addReservation(CustomDTO dto) {
        // Create a new Student object and set its attributes from the DTO.
        Student student = new Student();
        student.setSId(dto.getSId());
        student.setName(dto.getName());
        student.setAddress(dto.getAddress());
        student.setContactNo(dto.getContactNo());
        student.setDob(dto.getDob());
        student.setGender(dto.getGender());

        // Get a Hibernate session.
        Session session = getSession();

        // Set the session for the roomDAO and retrieve the corresponding room.
        roomDAO.setSession(session);
        Room room = roomDAO.get(dto.getRId());
        room.setQty(room.getQty() - 1);

        // Create a new Reservation object and set its attributes.
        Reservation reservation = new Reservation();
        reservation.setResId(dto.getResId());
        reservation.setDate(dto.getDate());
        reservation.setStatus(dto.getStatus());
        reservation.setStudent(student);
        reservation.setRoom(room);

        // Establish a bidirectional relationship between student and reservation, and room and reservation.
        student.getStudentDetails().add(reservation);
        room.getRoomDetails().add(reservation);

        // Begin a new transaction.
        Transaction transaction = session.beginTransaction();

        // Set the session for the studentDAO and attempt to save the student.
        studentDAO.setSession(session);
        boolean save = studentDAO.save(student);

        if (save) {
            // Set the session for the roomDAO and attempt to update the room.
            roomDAO.setSession(session);
            boolean update = roomDAO.update(room);

            if (update) {
                // Set the session for the reservationDAO and attempt to register the reservation.
                reservationDAO.setSession(session);
                boolean register = reservationDAO.register(reservation);

                if (register) {
                    // Commit the transaction, close the session, and return true upon successful reservation.
                    transaction.commit();
                    session.close();
                    return true;
                }
            }
        }

        // Rollback the transaction, close the session, and return false if any step fails.
        transaction.rollback();
        session.close();
        return false;
    }

    // Override the loadAllRid method to retrieve a list of room IDs.
    @Override
    public ObservableList<String> loadAllRid() {
        // Create an empty ObservableList to store room IDs.
        ObservableList<String> roomId = FXCollections.observableArrayList();

        // Get a Hibernate session.
        Session session = getSession();

        // Set the session for the roomDAO and retrieve a list of room IDs.
        roomDAO.setSession(session);
        List<String> rId = roomDAO.getRoomId();

        // Add room IDs to the ObservableList.
        for (String id : rId) {
            roomId.add(id);
        }

        return roomId;
    }

    // Override the getAllData method to retrieve a list of reservation data.
    @Override
    public ObservableList<CustomTDM> getAllData() {
        // Create an empty ObservableList to store reservation data.
        ObservableList<CustomTDM> all = FXCollections.observableArrayList();

        // Get a Hibernate session.
        Session session = getSession();

        // Set the session for the reservationDAO and retrieve a list of reservations.
        reservationDAO.setSession(session);
        List<Reservation> data = reservationDAO.allData();

        // Convert Reservation objects to CustomTDM objects and add them to the ObservableList.
        for (Reservation r : data) {
            all.add(new CustomTDM(r.getStudent().getSId(), r.getStudent().getName(), r.getResId(), r.getStatus(),
                    r.getRoom().getRId(), r.getRoom().getType(), r.getDate()));
        }

        return all;
    }

    // Override the loadRoom method to retrieve room data based on an ID.
    @Override
    public ObservableList<RoomTDM> loadRoom(String id) {
        // Get a Hibernate session.
        Session session = getSession();

        // Set the session for the roomDAO and retrieve room data.
        roomDAO.setSession(session);
        Room room = roomDAO.get(id);

        // Create an ObservableList to store room data.
        ObservableList<RoomTDM> rooms = FXCollections.observableArrayList();

        // Add room data to the ObservableList.
        rooms.add(new RoomTDM(room.getRId(), room.getType(), room.getKeMoney(), room.getQty()));

        // Close the session.
        session.close();

        return rooms;
    }

    // Override the generateNextReservationId method to generate the next reservation ID.
    @Override
    public String generateNextReservationId() {
        // Get a Hibernate session.
        Session session = getSession();
        reservationDAO.setSession(session);

        // Generate the next reservation ID.
        String id = reservationDAO.generateNextResId();

        if (id != null) {
            return newOrderID(id);
        }

        return newOrderID(null);
    }

    // Helper method to generate a new reservation ID based on the current ID.
    @Override
    public String newOrderID(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("R0");
            int id = Integer.parseInt(split[1]);
            id += 1;

            if (id >= 10) {
                return "R0" + id;
            }
            return "R00" + id;
        }
        return "R001";
    }
}
