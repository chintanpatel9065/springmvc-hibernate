package org.chintanpatel.springmvchibernate.service;

import org.chintanpatel.springmvchibernate.model.Employee;

import java.util.List;

public interface EmployeeService {

    void addEmployee(Employee employee);

    List<Employee>getAllEmployeeList();

    Employee getEmployeeById(Long employeeId);

    void editEmployee(Employee employee);

    void deleteEmployeeById(Long employeeId);
}
