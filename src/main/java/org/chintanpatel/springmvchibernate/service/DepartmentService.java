package org.chintanpatel.springmvchibernate.service;

import org.chintanpatel.springmvchibernate.model.Department;

import java.util.List;

public interface DepartmentService {

    void addDepartment(Department department);

    List<Department>getAllDepartmentList();

    Department getDepartmentById(Long departmentId);

    void editDepartment(Department department);

    void deleteDepartmentById(Long departmentId);
}
