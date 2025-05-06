package org.chintanpatel.springmvchibernate.repository;

import org.chintanpatel.springmvchibernate.model.Department;

import java.util.List;

public interface DepartmentRepository {

    void addDepartment(Department department);

    List<Department>getAllDepartmentList();

    Department getDepartmentById(Long departmentId);

    void editDepartment(Department department);

    void deleteDepartmentById(Long departmentId);
}
