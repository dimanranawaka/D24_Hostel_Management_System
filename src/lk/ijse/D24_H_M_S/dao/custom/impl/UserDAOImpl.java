package lk.ijse.D24_H_M_S.dao.custom.impl;

import lk.ijse.D24_H_M_S.dao.custom.UserDAO;
import lk.ijse.D24_H_M_S.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private Session session;

    // Set the current Hibernate session
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    // Save a user entity to the database
    @Override
    public boolean save(User user) {
        if (user != null) {
            session.save(user);
            return true; // Return true if the save operation was successful
        } else {
            return false; // Return false if the user object is null
        }
    }

    // Update an existing user entity in the database
    @Override
    public boolean update(User user) {
        if (user != null) {
            session.update(user);
            return true; // Return true if the update operation was successful
        } else {
            return false; // Return false if the user object is null
        }
    }

    // Retrieve a user entity by its unique ID
    @Override
    public User get(String id) {
        return session.get(User.class, id); // Return the user entity or null if not found
    }

    // Delete an existing user entity from the database
    @Override
    public boolean delete(User user) {
        if (user != null) {
            session.delete(user);
            return true; // Return true if the delete operation was successful
        } else {
            return false; // Return false if the user object is null
        }
    }

    // Retrieve a list of all user entities from the database
    @Override
    public List<User> getAllUser() {
        String hql = "FROM User"; // HQL query to select all users

        Query query = session.createQuery(hql);

        List list = query.list(); // Execute the query and retrieve the list of users

        session.close(); // Close the session

        return list; // Return the list of users
    }
}

