package lk.ijse.D24_H_M_S.bo.custom.impl;

import lk.ijse.D24_H_M_S.bo.custom.LoginBO;
import lk.ijse.D24_H_M_S.dao.DAOFactory;
import lk.ijse.D24_H_M_S.dao.custom.LoginDAO;
import lk.ijse.D24_H_M_S.entity.User;
import lk.ijse.D24_H_M_S.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.List;

public class LoginBOImpl implements LoginBO {

    // Initialize the LoginDAO using DAOFactory
    private final LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.LOGIN);


    // Override method to get the Hibernate session
    @Override
    public Session getSession(){
        return FactoryConfiguration.getInstance().getSession();
    }

    // Method to check user credentials and return the user's role
    @Override
    public String checkUser(String name, String password){

        // Get the Hibernate session
        Session session = getSession();

        // Set the Hibernate session for the LoginDAO
        loginDAO.setSession(session);


        // Create a User object
        User user = new User();

        // Call the check method in LoginDAO to verify user credentials
        List<User> check = loginDAO.check(name,password);

        // Loop through the list of Users returned by the DAO
        for (User u:
             check) {

            // Set the role from the first user in the list (assuming only one user matches)
            user.setRole(u.getRole());

        }

        // Check the user's role and return a corresponding string
        if(user.getRole().equals("Admin")){
            return "Admin";
        }

        else if (user.getRole().equals("Reception")){
            return "Reception";
        }

        // If no matching user is found, return "NO"
        return "NO";
    }
}
