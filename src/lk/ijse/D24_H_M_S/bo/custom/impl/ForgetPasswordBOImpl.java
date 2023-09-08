package lk.ijse.D24_H_M_S.bo.custom.impl;

import lk.ijse.D24_H_M_S.bo.custom.ForgetPasswordBO;
import lk.ijse.D24_H_M_S.dao.DAOFactory;
import lk.ijse.D24_H_M_S.dao.custom.ForgetPasswordDAO;
import lk.ijse.D24_H_M_S.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ForgetPasswordBOImpl implements ForgetPasswordBO {

    // Create an instance of the ForgetPasswordDAO interface using DAOFactory.
    private final ForgetPasswordDAO forgetPasswordDAO =
            (ForgetPasswordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.FORGET);

    @Override
    public Session getSession() {
        // Get a Hibernate session from FactoryConfiguration.
        return FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public boolean forgetPassword(String id, String name, String password) {

        // Get a Hibernate session.
        Session session = getSession();

        // Begin a database transaction.
        Transaction transaction = session.beginTransaction();

        // Set the Hibernate session for the ForgetPasswordDAO.
        forgetPasswordDAO.setSession(session);

        // Call the checkUser method of ForgetPasswordDAO to update the user's password.
        int i = forgetPasswordDAO.checkUser(id, name, password);

        if (i > 0) {
            // If at least one record (user) was updated successfully:

            // Commit the transaction to save changes to the database.
            transaction.commit();

            // Close the Hibernate session.
            session.close();

            // Return true to indicate a successful password update.
            return true;

        } else {
            // If no records were updated:

            // Rollback the transaction to undo any changes.
            transaction.rollback();

            // Close the Hibernate session.
            session.close();

            // Return false to indicate a failed password update.
            return false;
        }
    }
}

