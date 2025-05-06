package org.chintanpatel.springmvchibernate.repository;

import org.chintanpatel.springmvchibernate.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    void addEmployee(Employee employee);

    List<Employee>getAllEmployeeList();

    Employee getEmployeeById(Long employeeId);

    void editEmployee(Employee employee);

    void deleteEmployeeById(Long employeeId);
}
