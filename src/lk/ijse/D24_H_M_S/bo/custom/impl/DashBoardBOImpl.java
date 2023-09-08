package lk.ijse.D24_H_M_S.bo.custom.impl;

import lk.ijse.D24_H_M_S.bo.custom.DashBoardBO;
import lk.ijse.D24_H_M_S.dao.DAOFactory;
import lk.ijse.D24_H_M_S.dao.custom.EmployeeDAO;
import lk.ijse.D24_H_M_S.dao.custom.RoomDAO;
import lk.ijse.D24_H_M_S.dao.custom.StudentDAO;
import lk.ijse.D24_H_M_S.entity.Employee;
import lk.ijse.D24_H_M_S.entity.Room;
import lk.ijse.D24_H_M_S.entity.Student;
import lk.ijse.D24_H_M_S.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.List;

public class DashBoardBOImpl implements DashBoardBO {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.ROOM);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.STUDENT);
    private final EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.EMPLOYEE);

    @Override
    public Session getSession() {
        // Get the Hibernate session from the FactoryConfiguration
        return FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public int getRoomCount1() {
        // Get the count of rooms in category 1
        Session session = getSession();
        roomDAO.setSession(session);
        List<Room> room1 = roomDAO.getRoom1();

        int count = 0;

        for (Room r : room1) {
            // Accumulate the room quantities
            count += r.getQty();
        }
        return count;
    }

    @Override
    public int getRoomCount2() {
        // Get the count of rooms in category 2
        Session session = getSession();
        roomDAO.setSession(session);
        List<Room> room2 = roomDAO.getRoom2();

        int count = 0;

        for (Room r : room2) {
            // Accumulate the room quantities
            count += r.getQty();
        }
        return count;
    }

    @Override
    public int getRoomCount3() {
        // Get the count of rooms in category 3
        Session session = getSession();
        roomDAO.setSession(session);
        List<Room> room3 = roomDAO.getRoom3();

        int count = 0;

        for (Room r : room3) {
            // Accumulate the room quantities
            count += r.getQty();
        }
        return count;
    }

    @Override
    public int getRoomCount4() {
        // Get the count of rooms in category 4
        Session session = getSession();
        roomDAO.setSession(session);
        List<Room> room4 = roomDAO.getRoom4();

        int count = 0;

        for (Room r : room4) {
            // Accumulate the room quantities
            count += r.getQty();
        }
        return count;
    }

    @Override
    public int getEmployeeCount() {
        // Get the count of employees
        Session session = getSession();
        employeeDAO.setSession(session);
        List<Employee> employee = employeeDAO.getEmployee();

        int count = 0;

        for (Employee e : employee) {
            // Increment the count for each employee
            count++;
        }
        return count;
    }

    @Override
    public int getStudentCount() {
        // Get the count of students
        Session session = getSession();
        studentDAO.setSession(session);
        List<Student> all = studentDAO.getAll();

        int count = 0;

        for (Student student : all) {
            // Increment the count for each student
            count++;
        }
        return count;
    }
}

