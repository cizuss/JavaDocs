package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by cizuss94 on 7/16/2016.
 */
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public List<Department> findAllDepartments() {
        return new DepartmentDao().findAllDepartments();
    }

    @Override
    public Department findOneDepartment(Long id) {
        return new DepartmentDao().findDepartmentById(id);
    }

    @Override
    public void deleteOneDepartment(Long id) {
        new DepartmentDao().deleteDepartment(new DepartmentDao().findDepartmentById(id));
    }

    @Override
    public void saveOneDepartment(String departmentName, Long locationId) {
        Department newDepartment = new Department();
        newDepartment.setDepartmentName(departmentName);
        newDepartment.setLocation(locationId);
        new DepartmentDao().insertDepartment(newDepartment);
    }
}
