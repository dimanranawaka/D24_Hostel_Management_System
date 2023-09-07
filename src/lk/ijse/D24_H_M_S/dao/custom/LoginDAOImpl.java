package lk.ijse.D24_H_M_S.dao.custom;

import lk.ijse.D24_H_M_S.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LoginDAOImpl implements LoginDAO {
    private Session session;
    @Override
    public void setSession(Session session){
        this.session = session;
    }

    // Method to check if a user with the provided name and password exists
    @Override
    public List<User> check(String name, String password){

        // Define an HQL (Hibernate Query Language) query to fetch User entities
        // where 'name' and 'password' match the provided parameters.
        String hql = "FROM User WHERE name = : nam AND password = : passwor";

        // Create a query object using the session and the HQL query string
        Query query = session.createQuery(hql);

        // Set the parameters in the query using placeholders ':nam' and ':passwor'
        query.setParameter("nam",name);
        query.setParameter("passwor", password);

        // Execute the query and retrieve the results as a list of User entities
        List <User>list = query.list();

        // Close the session (ensure proper resource management)
        session.close();

        // Return the list of User entities that match the query criteria
        return list;
    }
}
