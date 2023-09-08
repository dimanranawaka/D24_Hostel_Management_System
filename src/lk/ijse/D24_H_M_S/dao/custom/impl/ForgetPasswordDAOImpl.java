package lk.ijse.D24_H_M_S.dao.custom.impl;

import lk.ijse.D24_H_M_S.dao.custom.ForgetPasswordDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ForgetPasswordDAOImpl implements ForgetPasswordDAO {
    private Session session;

    @Override
    public void setSession(Session session) {
        // Set the Hibernate session for database operations.
        this.session = session;
    }

    @Override
    public int checkUser(String id, String name, String password) {
        // Define HQL query to update the user's password based on user ID and name.
        String hql = "UPDATE User SET password = :pass WHERE uId = :user_id AND name = :user_name";

        // Create a query object.
        Query query = session.createQuery(hql);

        // Set parameters for the query.
        query.setParameter("pass", password);
        query.setParameter("user_id", id);
        query.setParameter("user_name", name);

        // Execute the update query and return the number of affected rows.
        int i = query.executeUpdate();

        return i;
    }
}

