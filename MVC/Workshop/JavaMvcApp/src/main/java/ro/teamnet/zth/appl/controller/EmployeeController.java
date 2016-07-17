package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
<<<<<<< HEAD
import ro.teamnet.zth.api.annotations.MyRequestParameter;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.service.EmployeeServiceImpl;

import java.util.List;
import java.util.StringJoiner;

/**
 * Created by cizuss94 on 7/15/2016.
=======

/**
 * Created by user on 7/14/2016.
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4
 */
@MyController(urlPath = "/employees")
public class EmployeeController {
    @MyRequestMethod(urlPath = "/all")
<<<<<<< HEAD
    public List<Employee> getAllEmployees() {
        return new EmployeeServiceImpl().findAllEmployees();
    }
    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmployee(@MyRequestParameter(name = "id") Long employeeId) {
        return new EmployeeServiceImpl().findOneEmployee(employeeId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "DELETE")
    public void deleteOneEmployee(@MyRequestParameter(name = "id") Long employeeId) {
        new EmployeeServiceImpl().deleteOneEmployee(employeeId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "POST")
    public void saveEmployee(@MyRequestParameter(name = "firstname") String firstName,
                             @MyRequestParameter(name = "lastname") String lastName)
    {
        new EmployeeServiceImpl().saveOneEmployee(firstName, lastName);
=======
    public String getAllEmployees() {
        return "allEmployees";
    }
    @MyRequestMethod(urlPath = "/one")
    public String getOneEmployee() {
        return "oneRandomEmployee";
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4
    }
}
