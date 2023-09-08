package lk.ijse.D24_H_M_S.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.SuperBO;
import lk.ijse.D24_H_M_S.dto.EmployeeDTO;
import lk.ijse.D24_H_M_S.view.tdm.EmployeeTDM;

public interface EmployeeBO extends SuperBO {


     /* Save a new Employee using the provided DTO.

      @param dto The EmployeeDTO containing employee information.
      @return true if the employee is saved successfully, false otherwise.*/

    boolean saveEmployee(EmployeeDTO dto);


     /* Update an existing Employee using the provided DTO.

      @param dto The EmployeeDTO containing updated employee information.
      @return true if the employee is updated successfully, false otherwise.*/

    boolean updateEmployee(EmployeeDTO dto);


       /*Search for an Employee by their ID and retrieve their information.

       @param id The ID of the employee to search for.
       @return An EmployeeDTO containing the employee information if found, or null if not found.*/

    EmployeeDTO searchEmployee(String id);

     /* Delete an existing Employee using the provided DTO.

      @param dto The EmployeeDTO containing employee information for deletion.
      @return true if the employee is deleted successfully, false otherwise.

      */

    boolean deleteEmployee(EmployeeDTO dto);

      /*Retrieve a list of all employees in the form of an ObservableList of EmployeeTDM objects.

      @return An ObservableList containing EmployeeTDM objects representing all employees.*/

    ObservableList<EmployeeTDM> getAllEmployee();
}
