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

    private final ReservationDAO reservationDAO =
            (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.RESERVATION);

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.ROOM);

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.STUDENT);
    @Override
    public Session getSession(){

        return FactoryConfiguration.getInstance().getSession();

    }
    @Override
    public boolean addReservation(CustomDTO dto){

        Student student = new Student();

        student.setSId(dto.getSId());
        student.setName(dto.getName());
        student.setAddress(dto.getAddress());
        student.setContactNo(dto.getContactNo());
        student.setDob(dto.getDob());
        student.setGender(dto.getGender());

        Session session = getSession();

        roomDAO.setSession(session);
        Room room = roomDAO.get(dto.getResId());
        room.setQty(room.getQty()-1);

        Reservation reservation = new Reservation();

        reservation.setResId(dto.getResId());
        reservation.setDate(dto.getDate());
        reservation.setStatus(dto.getStatus());
        reservation.setStudent(student);
        reservation.setRoom(room);

        student.getStudentDetails().add(reservation);

        room.getRoomDetails().add(reservation);

        Transaction transaction = session.beginTransaction();
        studentDAO.setSession(session);
        boolean save = studentDAO.save(student);

        if (save) {

            roomDAO.setSession(session);

            boolean update = roomDAO.update(room);

            if (update){

                reservationDAO.setSession(session);

                boolean register = reservationDAO.register(reservation);

                if (register){

                    transaction.commit();
                    session.close();
                    return true;

                }
            }
        }

        transaction.rollback();
        session.close();
        return false;

    }
    @Override
    public ObservableList<String> loadAllRid(){

        ObservableList<String> roomId = FXCollections.observableArrayList();
        Session session = getSession();

        roomDAO.setSession(session);

        List<String> rId = roomDAO.getRoomId();

        for (String id:
             rId) {

            roomId.add(id);

        }
        return roomId;
    }
    @Override
    public ObservableList<CustomTDM> getAllData(){

        ObservableList<CustomTDM> all = FXCollections.observableArrayList();
        Session session = getSession();

        reservationDAO.setSession(session);

        List<Reservation> data = reservationDAO.allData();

        for (Reservation r :  data){

            all.add(new CustomTDM(r.getStudent().getSId(),r.getStudent().getName(),r.getResId(),r.getStatus(),
                    r.getRoom().getRId(),r.getRoom().getType(),r.getDate()));

        }
        return all;
    }
    @Override
    public ObservableList<RoomTDM> loadRoom(String id){

        Session session = getSession();

        roomDAO.setSession(session);

        Room room = roomDAO.get(id);

        ObservableList<RoomTDM> rooms = FXCollections.observableArrayList();

        rooms.add(new RoomTDM(room.getRId(),room.getType(),room.getKeMoney(),room.getQty()));

        session.close();

        return rooms;

    }
    @Override
    public String generateNextReservationId(){

        Session session = getSession();
        reservationDAO.setSession(session);

        String id = reservationDAO.generateNextResId();

        if (id !=null){

            return newOrderID(id);

        }

        return newOrderID(null);

    }
    @Override
    public String newOrderID(String currentOrderId){

        if (currentOrderId != null){

            String[] split = currentOrderId.split("R0");
            int id = Integer.parseInt(split[1]);
            id += 1;

            if (id >=10){

                return "R0" + id;

            }
            return "R00" + id;
        }
        return "R001";
    }
}
