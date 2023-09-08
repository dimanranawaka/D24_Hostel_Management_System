package lk.ijse.D24_H_M_S.dao.custom;

import lk.ijse.D24_H_M_S.dao.CrudDAO;
import lk.ijse.D24_H_M_S.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User, String> {

    // Custom method to retrieve a list of all users from the database
    List<User> getAllUser();

}
