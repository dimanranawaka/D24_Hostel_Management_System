package lk.ijse.D24_H_M_S.dao.custom;

import lk.ijse.D24_H_M_S.dao.SuperDAO;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    List<Object[]> loadAllStudent();

    boolean update(String resId, String status);



}
