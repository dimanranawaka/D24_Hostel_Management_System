package lk.ijse.D24_H_M_S.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.D24_H_M_S.bo.custom.EmployeeBO;
import lk.ijse.D24_H_M_S.dao.DAOFactory;
import lk.ijse.D24_H_M_S.dao.custom.EmployeeDAO;
import lk.ijse.D24_H_M_S.dto.EmployeeDTO;
import lk.ijse.D24_H_M_S.entity.Employee;
import lk.ijse.D24_H_M_S.util.FactoryConfiguration;
import lk.ijse.D24_H_M_S.view.tdm.EmployeeTDM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    private final EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.Types.EMPLOYEE);

    @Override
    public Session getSession(){
        // Get the Hibernate session from the FactoryConfiguration.
        return FactoryConfiguration.getInstance().getSession();
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO){

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Set the Hibernate session for the EmployeeDAO.
            employeeDAO.setSession(session);

            // Create and save an Employee entity from the provided DTO.
            boolean save = employeeDAO.save(new Employee(employeeDTO.getEID(), employeeDTO.getName(), employeeDTO.getAddress(),
                    employeeDTO.getContact(), employeeDTO.getRole()));

            transaction.commit();
            session.close();
            return save;

        }catch (Exception e){
            // Handle exceptions and return false if an error occurs.
            transaction.rollback();
            session.close();
            e.printStackTrace();
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO){

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Set the Hibernate session for the EmployeeDAO.
            employeeDAO.setSession(session);

            // Update an existing Employee entity with the data from the DTO.
            boolean update = employeeDAO.update(new Employee(employeeDTO.getEID(), employeeDTO.getName(), employeeDTO.getAddress(),
                    employeeDTO.getContact(), employeeDTO.getRole()));

            transaction.commit();
            session.close();
            return update;

        }catch (Exception e){
            // Handle exceptions and return false if an error occurs.
            System.out.println(e);
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public EmployeeDTO searchEmployee(String id){

        try {
            Session session = getSession();
            // Set the Hibernate session for the EmployeeDAO.
            employeeDAO.setSession(session);

            // Retrieve an Employee entity by its ID.
            Employee employee = employeeDAO.get(id);
            session.close();

            // Convert the retrieved entity into a DTO and return it.
            return new EmployeeDTO(employee.getEId(), employee.getName(), employee.getAddress(),
                    employee.getContact(), employee.getRole());

        }catch (Exception e){
            // Handle exceptions and return null if an error occurs.
            e.printStackTrace();
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean deleteEmployee(EmployeeDTO employeeDTO){

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Set the Hibernate session for the EmployeeDAO.
            employeeDAO.setSession(session);

            // Delete an Employee entity from the database using the DTO data.
            boolean delete = employeeDAO.delete(new Employee(employeeDTO.getEID(), employeeDTO.getName(), employeeDTO.getAddress(),
                    employeeDTO.getContact(), employeeDTO.getRole()));

            transaction.commit();
            session.close();
            return delete;

        }catch (Exception e){
            // Handle exceptions and return false if an error occurs.
            System.out.println(e);
            transaction.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public ObservableList<EmployeeTDM> getAllEmployee(){

        Session session = getSession();
        // Set the Hibernate session for the EmployeeDAO.
        employeeDAO.setSession(session);

        // Retrieve a list of all Employee entities from the database.
        List<Employee> employee = employeeDAO.getEmployee();

        ObservableList<EmployeeTDM> empList = FXCollections.observableArrayList();

        for (Employee emp: employee) {
            // Convert each Employee entity into a TDM and add it to the list.
            empList.add(new EmployeeTDM(emp.getEId(), emp.getName(), emp.getAddress(), emp.getContact(), emp.getRole()));
        }

        return empList;
    }
}
