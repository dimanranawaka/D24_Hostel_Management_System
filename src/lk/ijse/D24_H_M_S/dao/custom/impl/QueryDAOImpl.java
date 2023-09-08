package lk.ijse.D24_H_M_S.dao.custom.impl;

import lk.ijse.D24_H_M_S.dao.custom.QueryDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    // Define a Session variable to hold the Hibernate session.
    private Session session;

    // Override the setSession method to set the Hibernate session.
    @Override
    public void setSession(Session session) {
        // Assign the provided session to the instance variable.
        this.session = session;
    }

    // Override the loadAllStudent method to retrieve a list of student reservations.
    @Override
    public List<Object[]> loadAllStudent() {
        // Define an HQL query to select specific fields from Reservation, Student, and Room tables.
        String hql = "SELECT res.resId, s.sId, s.name, r.rId, r.type, res.status FROM Reservation res JOIN " +
                "Student s ON res.student=s.sId JOIN Room r ON res.room=r.rId \n" +
                "WHERE res.status='Not Paid'";

        // Create a Hibernate Query object based on the HQL query.
        Query query = session.createQuery(hql);

        // Execute the query and store the results in a list of Object arrays.
        List<Object[]> list = query.list();

        // Close the Hibernate session.
        session.close();

        // Return the list of results.
        return list;
    }

    // Override the update method to update the status of a reservation.
    @Override
    public boolean update(String resId, String status) {
        // Define an HQL query to update the status of a reservation based on its ID.
        String hql = "UPDATE Reservation SET status = :new WHERE resId = :id";

        // Create a Hibernate Query object based on the update query.
        Query query = session.createQuery(hql);

        // Set parameters for the query to specify the new status and reservation ID.
        query.setParameter("new", status);
        query.setParameter("id", resId);

        // Execute the update query and store the number of affected rows.
        int i = query.executeUpdate();

        // Check if any rows were updated and return true if so, otherwise return false.
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }
}

