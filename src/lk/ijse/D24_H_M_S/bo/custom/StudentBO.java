package lk.ijse.D24_H_M_S.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.SuperBO;
import lk.ijse.D24_H_M_S.dto.StudentDTO;
import lk.ijse.D24_H_M_S.view.tdm.StudentTDM;

public interface StudentBO extends SuperBO {

    // Add a student using a StudentDTO
    boolean addStudent(StudentDTO dto);

    // Update a student using a StudentDTO
    boolean updateStudent(StudentDTO dto);

    // Delete a student using a StudentDTO
    boolean deleteStudent(StudentDTO dto);

    // Search for a student by its ID and return a StudentDTO
    StudentDTO searchStudent(String id);

    // Retrieve a list of all students and convert them to StudentTDM objects
    ObservableList<StudentTDM> getAllStudent();
}
