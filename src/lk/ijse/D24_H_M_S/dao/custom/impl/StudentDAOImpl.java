package lk.ijse.D24_H_M_S.dao.custom.impl;

import lk.ijse.D24_H_M_S.dao.custom.StudentDAO;
import lk.ijse.D24_H_M_S.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private Session session; // Declare a private instance variable for Hibernate Session

    @Override
    public void setSession(Session session) {
        this.session = session; // Set the Hibernate Session when called
    }

    @Override
    public boolean save(Student student) {
        if (student != null) { // Check if the provided student object is not null
            session.save(student); // Save the student object using the Hibernate session
            return true; // Return true to indicate successful save
        } else {
            return false; // Return false if the student object is null
        }
    }

    @Override
    public boolean update(Student student) {
        if (student != null) { // Check if the provided student object is not null
            session.update(student); // Update the student object using the Hibernate session
            return true; // Return true to indicate successful update
        } else {
            return false; // Return false if the student object is null
        }
    }

    @Override
    public Student get(String id) {
        return session.get(Student.class, id); // Retrieve a Student entity by its ID using Hibernate session
    }

    @Override
    public boolean delete(Student student) {
        if (student != null) { // Check if the provided student object is not null
            session.delete(student); // Delete the student object using the Hibernate session
            return true; // Return true to indicate successful deletion
        } else {
            return false; // Return false if the student object is null
        }
    }

    @Override
    public List<Student> getAll() {
        String hql = "FROM Student"; // Define an HQL (Hibernate Query Language) query to retrieve all students
        Query query = session.createQuery(hql); // Create a Hibernate query object
        List list = query.list(); // Execute the query and store the result in a list
        session.close(); // Close the Hibernate session
        return list; // Return the list of students
    }
}
