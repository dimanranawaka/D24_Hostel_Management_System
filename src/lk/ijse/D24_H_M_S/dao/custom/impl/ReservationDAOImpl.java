package lk.ijse.D24_H_M_S.dao.custom.impl;

import lk.ijse.D24_H_M_S.dao.custom.ReservationDAO;
import lk.ijse.D24_H_M_S.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    private Session session;

    // Sets the Hibernate session for this DAO.
    @Override
    public void setSession(Session session){
        this.session = session;
    }

    // Registers a new reservation in the database.
    // Returns true if the reservation is successfully saved; otherwise, returns false.
    @Override
    public boolean register(Reservation reservation){
        if (reservation != null){
            session.save(reservation);
            return true;
        }
        return false;
    }

    // Generates the next reservation ID.
    // It retrieves the latest reservation ID from the database, increments it, and returns the new ID.
    @Override
    public String generateNextResId(){
        String hql = "SELECT resId FROM Reservation ORDER BY resId DESC";
        Query query = session.createQuery(hql).setMaxResults(1);

        List<String> list = query.list();

        session.close();

        for (String id : list){
            return id;
        }

        return null;
    }

    // Retrieves all reservation data from the database.
    // Returns a list of Reservation objects containing all reservation records.
    @Override
    public List<Reservation> allData(){
        Query query = session.createQuery("FROM Reservation");

        List<Reservation> list = query.list();

        session.close();

        return list;
    }
}

