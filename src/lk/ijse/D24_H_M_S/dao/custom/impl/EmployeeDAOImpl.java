package lk.ijse.D24_H_M_S.dao.custom.impl;

import lk.ijse.D24_H_M_S.dao.custom.EmployeeDAO;
import lk.ijse.D24_H_M_S.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private Session session;

    @Override
    public void setSession(Session session) {
        // Set the Hibernate session for this DAO.
        this.session = session;
    }

    @Override
    public boolean save(Employee employee) {
        // Save an Employee entity to the database if it's not null.
        if (employee != null) {
            session.save(employee);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Employee employee) {
        // Update an Employee entity in the database if it's not null.
        if (employee != null) {
            session.update(employee);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Employee get(String id) {
        // Retrieve an Employee entity from the database by its ID.
        return session.get(Employee.class, id);
    }

    @Override
    public boolean delete(Employee employee) {
        // Delete an Employee entity from the database if it's not null.
        if (employee != null) {
            session.delete(employee);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Employee> getEmployee() {
        // Retrieve a list of all Employee entities from the database.
        String hql = "FROM Employee";
        Query query = session.createQuery(hql);
        List list = query.list();
        session.close();
        return list;
    }
}
