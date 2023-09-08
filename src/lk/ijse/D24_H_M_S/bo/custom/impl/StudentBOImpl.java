package lk.ijse.D24_H_M_S.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.custom.StudentBO;
import lk.ijse.D24_H_M_S.dao.DAOFactory;
import lk.ijse.D24_H_M_S.dao.custom.StudentDAO;
import lk.ijse.D24_H_M_S.dto.StudentDTO;
import lk.ijse.D24_H_M_S.entity.Student;
import lk.ijse.D24_H_M_S.util.FactoryConfiguration;
import lk.ijse.D24_H_M_S.view.tdm.StudentTDM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentBOImpl implements StudentBO {

    // Create a StudentDAO instance using the DAOFactory
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.STUDENT);

    @Override
    public Session getSession() {

        // Retrieve the Hibernate session from FactoryConfiguration
        return FactoryConfiguration.getInstance().getSession();

    }

    @Override
    public boolean addStudent(StudentDTO dto) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            studentDAO.setSession(session); // Set the Hibernate session
            boolean save = studentDAO.save(new Student(dto.getSId(), dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getDate(), dto.getGender()));
            transaction.commit(); // Commit the transaction
            session.close(); // Close the session
            return save; // Return true if the save was successful
        } catch (Exception e) {
            System.out.println(e); // Print any exception that occurs
            transaction.rollback(); // Rollback the transaction in case of an error
            session.close(); // Close the session
            return false; // Return false to indicate a failed save
        }
    }

    @Override
    public boolean updateStudent(StudentDTO dto) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            studentDAO.setSession(session); // Set the Hibernate session
            boolean update = studentDAO.update(new Student(dto.getSId(), dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getDate(), dto.getGender()));
            transaction.commit(); // Commit the transaction
            session.close(); // Close the session
            return update; // Return true if the update was successful
        } catch (Exception e) {
            System.out.println(e); // Print any exception that occurs
            transaction.rollback(); // Rollback the transaction in case of an error
            session.close(); // Close the session
            return false; // Return false to indicate a failed update
        }
    }

    @Override
    public boolean deleteStudent(StudentDTO dto) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {

            studentDAO.setSession(session); // Set the Hibernate session
            boolean delete = studentDAO.delete(new Student(dto.getSId(), dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getDate(), dto.getGender(), dto.getStudentDetails()));
            transaction.commit(); // Commit the transaction
            session.close(); // Close the session
            return delete; // Return true if the delete method was successful

        } catch (Exception e) {

            System.out.println(e); // Print any exception that occurs
            transaction.rollback(); // Rollback the transaction in case of an error
            session.close(); // Close the session
            return false; // Return false to indicate a failed delete

        }
    }

    @Override
    public StudentDTO searchStudent(String id) {

        Session session = getSession();

        try {

            studentDAO.setSession(session); // Set the Hibernate session
            Student student = studentDAO.get(id); // Retrieve a student by ID
            session.close(); // Close the session
            // Create and return a StudentDTO from the retrieved student entity
            return new StudentDTO(student.getSId(), student.getName(), student.getAddress(), student.getContactNo(), student.getDob(), student.getGender());

        } catch (Exception e) {

            System.out.println(e); // Print any exception that occurs
            return null; // Return null in case of an error

        }
    }

    @Override
    public ObservableList<StudentTDM> getAllStudent() {

        Session session = getSession();
        ObservableList<StudentTDM> tdms = FXCollections.observableArrayList();

        studentDAO.setSession(session); // Set the Hibernate session
        List<Student> all = studentDAO.getAll(); // Retrieve all students

        for (Student student : all) {
            // Convert each student entity to a StudentTDM and add to the observable list
            tdms.add(new StudentTDM(student.getSId(), student.getName(), student.getAddress(), student.getContactNo(), student.getDob(), student.getGender()));
        }

        return tdms; // Return the observable list of StudentTDM objects
    }
}
