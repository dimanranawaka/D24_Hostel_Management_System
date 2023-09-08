package lk.ijse.D24_H_M_S.dao.custom;

import lk.ijse.D24_H_M_S.dao.SuperDAO;

public interface ForgetPasswordDAO extends SuperDAO {

    int checkUser(String id, String name, String password);

}
