package lk.ijse.D24_H_M_S.bo.custom;

import lk.ijse.D24_H_M_S.bo.SuperBO;

// Interface for the Login Business Object (BO)
public interface LoginBO extends SuperBO {

    // Method to check user credentials and return the user's role
    String checkUser(String name,String password);
}
