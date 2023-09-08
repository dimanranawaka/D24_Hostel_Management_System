package lk.ijse.D24_H_M_S.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.custom.UnpaidStudentBO;
import lk.ijse.D24_H_M_S.dao.DAOFactory;
import lk.ijse.D24_H_M_S.dao.custom.QueryDAO;
import lk.ijse.D24_H_M_S.dto.CustomDTO;
import lk.ijse.D24_H_M_S.util.FactoryConfiguration;
import lk.ijse.D24_H_M_S.view.tdm.CustomTDM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UnpaidStudentBOImpl implements UnpaidStudentBO {

    // Create an instance of QueryDAO to handle database queries related to unpaid students.
    private final QueryDAO unpaidDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.UNPAID);

    // Override the getSession method to retrieve a Hibernate session.
    @Override
    public Session getSession() {
        // Get a Hibernate session using FactoryConfiguration.getInstance().
        return FactoryConfiguration.getInstance().getSession();
    }

    // Override the getUnpaidStudents method to fetch a list of unpaid students.
    @Override
    public ObservableList<CustomTDM> getUnpaidStudents() {
        // Create an empty ObservableList to store CustomTDM objects.
        ObservableList<CustomTDM> std = FXCollections.observableArrayList();

        // Get a Hibernate session.
        Session session = getSession();

        // Set the Hibernate session for the unpaidDAO.
        unpaidDAO.setSession(session);

        // Load a list of unpaid students from the database.
        List<Object[]> students = unpaidDAO.loadAllStudent();

        // Iterate through the list of students and create CustomTDM objects, then add them to std.
        for (Object[] s : students) {
            std.add(new CustomTDM(s[1].toString(), s[2].toString(), s[0].toString(), s[5].toString(), s[3].toString(), s[4].toString()));
        }

        // Return the ObservableList of unpaid students.
        return std;
    }

    // Override the updateStatus method to update the status of a student's reservation.
    @Override
    public boolean updateStatus(String id, String status) {
        // Get a Hibernate session.
        Session session = getSession();

        // Begin a new transaction.
        Transaction transaction = session.beginTransaction();

        try {
            // Set the Hibernate session for the unpaidDAO.
            unpaidDAO.setSession(session);

            // Call the update method in unpaidDAO to update the status of a reservation.
            boolean update = unpaidDAO.update(id, status);

            // Commit the transaction.
            transaction.commit();

            // Close the session.
            session.close();

            // Return the result of the update operation.
            return update;
        } catch (Exception e) {
            // Handle exceptions by printing the error and rolling back the transaction.
            System.out.println(e);
            transaction.commit();
            session.close();
            return false;
        }
    }
}
