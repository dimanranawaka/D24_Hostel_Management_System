package lk.ijse.D24_H_M_S.dao.custom;

import lk.ijse.D24_H_M_S.dao.CrudDAO;
import lk.ijse.D24_H_M_S.entity.Employee;

import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee, String> {

    // This interface extends the CrudDAO interface for Employee entities, specifying the Entity type as Employee
    // and the primary key type as String.

    // Additional method signature to retrieve a list of Employee entities.
    List<Employee> getEmployee();

}
