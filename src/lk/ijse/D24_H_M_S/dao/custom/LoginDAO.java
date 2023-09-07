package lk.ijse.D24_H_M_S.dao.custom;

import lk.ijse.D24_H_M_S.dao.SuperDAO;
import lk.ijse.D24_H_M_S.entity.User;

import java.util.List;

public interface LoginDAO extends SuperDAO {

    // Method to check user credentials and retrieve a list of matching users
    List<User> check(String name, String password);
}
