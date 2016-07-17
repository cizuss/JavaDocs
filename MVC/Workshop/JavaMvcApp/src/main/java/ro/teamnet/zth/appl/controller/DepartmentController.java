package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
<<<<<<< HEAD
import ro.teamnet.zth.api.annotations.MyRequestParameter;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.service.DepartmentServiceImpl;

import java.util.List;

/**
 * Created by cizuss94 on 7/15/2016.
=======

/**
 * Created by user on 7/14/2016.
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4
 */
@MyController(urlPath = "/departments")
public class DepartmentController {
    @MyRequestMethod(urlPath = "/all")
<<<<<<< HEAD
    public List<Department> getAllDepartments() {
        return new DepartmentServiceImpl().findAllDepartments();
    }
    @MyRequestMethod(urlPath = "/one")
    public Department getOneDepartment(@MyRequestParameter(name = "id") Long departmentId) {
        return new DepartmentServiceImpl().findOneDepartment(departmentId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "DELETE")
    public void deleteOneDepartment(@MyRequestParameter(name = "id") Long departmentId) {
        new DepartmentServiceImpl().deleteOneDepartment(departmentId);
    }
    @MyRequestMethod(urlPath = "/one", methodType = "POST")
    public void saveOneDepartment(@MyRequestParameter(name = "name") String departmentName,
                                  @MyRequestParameter(name = "location") Long locationId)
    {
        new DepartmentServiceImpl().saveOneDepartment(departmentName, locationId);
=======
    public String getAllDepartments() {
        return "allDepartments";
    }
    @MyRequestMethod(urlPath = "/one")
    public String getOneDepartment() {
        return "oneRandomDepartment";
>>>>>>> 855c1a6880e16f18104918fdd2e8cbca3602e0f4
    }
}
