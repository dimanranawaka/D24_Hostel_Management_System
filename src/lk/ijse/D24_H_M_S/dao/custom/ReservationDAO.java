package lk.ijse.D24_H_M_S.dao.custom;

import lk.ijse.D24_H_M_S.dao.SuperDAO;
import lk.ijse.D24_H_M_S.entity.Reservation;

import java.util.List;

public interface ReservationDAO extends SuperDAO {

    boolean register(Reservation reservation);

    String generateNextResId();

    List<Reservation> allData();

}
