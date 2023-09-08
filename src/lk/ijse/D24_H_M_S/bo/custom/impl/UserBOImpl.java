package lk.ijse.D24_H_M_S.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.custom.UserBO;
import lk.ijse.D24_H_M_S.dao.DAOFactory;
import lk.ijse.D24_H_M_S.dao.custom.UserDAO;
import lk.ijse.D24_H_M_S.dto.UserDTO;
import lk.ijse.D24_H_M_S.entity.User;
import lk.ijse.D24_H_M_S.util.FactoryConfiguration;
import lk.ijse.D24_H_M_S.view.tdm.UserTDM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserBOImpl implements UserBO {

    // Create an instance of the UserDAO using DAOFactory
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.USER);

    // Get a Hibernate session from FactoryConfiguration
    @Override
    public Session getSession(){
        return FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public boolean addUser(UserDTO dto){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Set the session for the userDAO
            userDAO.setSession(session);

            // Call the save method of userDAO to add a new user
            boolean save = userDAO.save(new User(dto.getUId(), dto.getName(), dto.getEmail(), dto.getPassword(),
                    dto.getRole()));

            // Commit the transaction and close the session
            transaction.commit();
            session.close();

            return save;

        }catch (Exception e){
            // Handle exceptions, rollback the transaction, and close the session in case of an error
            System.out.println(e);
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean updateUser(UserDTO dto){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Set the session for the userDAO
            userDAO.setSession(session);

            // Call the update method of userDAO to update user information
            boolean update = userDAO.update(new User(dto.getUId(), dto.getName(), dto.getEmail(),
                    dto.getPassword(), dto.getRole()));

            // Commit the transaction and close the session
            transaction.commit();
            session.close();

            return update;

        }catch (Exception e){
            // Handle exceptions, rollback the transaction, and close the session in case of an error
            System.out.println(e);
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean deleteUser(UserDTO dto){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Set the session for the userDAO
            userDAO.setSession(session);

            // Call the delete method of userDAO to delete a user
            boolean delete = userDAO.delete(new User(dto.getUId(), dto.getName(), dto.getEmail(),
                    dto.getPassword(), dto.getRole()));

            // Commit the transaction and close the session
            transaction.commit();
            session.close();

            return delete;

        }catch (Exception e){
            // Handle exceptions, rollback the transaction, and close the session in case of an error
            System.out.println(e);
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public UserDTO searchUser(String id){
        Session session = getSession();

        try{
            // Set the session for the userDAO
            userDAO.setSession(session);

            // Call the get method of userDAO to retrieve a user by ID
            User user = userDAO.get(id);

            // Close the session
            session.close();

            // Create and return a UserDTO based on the retrieved User entity
            return new UserDTO(user.getUId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole());

        }catch (Exception e){
            // Handle exceptions and close the session in case of an error
            System.out.println(e);
            session.close();
            return null;
        }
    }

    @Override
    public ObservableList<UserTDM> getAllUser(){
        Session session = getSession();

        // Create an ObservableList to store UserTDM objects
        ObservableList<UserTDM> users = FXCollections.observableArrayList();

        // Set the session for the userDAO
        userDAO.setSession(session);

        // Call the getAllUser method of userDAO to retrieve a list of all users
        List<User> allUser = userDAO.getAllUser();

        // Convert User entities to UserTDM objects and add them to the ObservableList
        for (User user : allUser) {
            users.add(new UserTDM(user.getUId(), user.getName(), user.getEmail(), user.getPassword(), user.getRole()));
        }

        return users;
    }
}

