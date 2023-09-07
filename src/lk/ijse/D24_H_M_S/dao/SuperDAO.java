package lk.ijse.D24_H_M_S.dao;

import org.hibernate.Session;

public interface SuperDAO {

    // Method to set the Hibernate session, allowing implementing classes to work with it
    void setSession(Session session);
}
