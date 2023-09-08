package lk.ijse.D24_H_M_S.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.SuperBO;
import lk.ijse.D24_H_M_S.dto.UserDTO;
import lk.ijse.D24_H_M_S.view.tdm.UserTDM;

public interface UserBO extends SuperBO {

    // Add a user to the system
    boolean addUser(UserDTO dto);

    // Update user information
    boolean updateUser(UserDTO dto);

    // Delete a user from the system
    boolean deleteUser(UserDTO dto);

    // Search for a user by their unique identifier
    UserDTO searchUser(String id);

    // Retrieve a list of all users
    ObservableList<UserTDM> getAllUser();
}

