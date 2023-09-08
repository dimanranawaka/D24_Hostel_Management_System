package lk.ijse.D24_H_M_S.dao.custom;

import lk.ijse.D24_H_M_S.dao.CrudDAO;
import lk.ijse.D24_H_M_S.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student,String> {

    // Retrieve a list of all students
    List<Student> getAll();

}
