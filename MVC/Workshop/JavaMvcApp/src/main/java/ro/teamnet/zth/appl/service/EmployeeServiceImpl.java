package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.domain.Employee;

import java.util.Date;
import java.util.List;

/**
 * Created by cizuss94 on 7/16/2016.
 */
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> findAllEmployees() {
        return new EmployeeDao().getAllEmployees();
    }

    @Override
    public Employee findOneEmployee(Long employeeId) {
        return new EmployeeDao().getEmployeeById(employeeId);
    }

    @Override
    public void deleteOneEmployee(Long employeeId) {
        new EmployeeDao().deleteEmployee(new EmployeeDao().getEmployeeById(employeeId));
    }

    @Override
    public void saveOneEmployee(String firstName, String lastName) {
        Employee newEmployee = new EmployeeDao().getEmployeeById((long) 101);
        newEmployee.setFirstName(firstName);
        newEmployee.setLastName(lastName);
        new EmployeeDao().insertEmployee(newEmployee);
    }
}
