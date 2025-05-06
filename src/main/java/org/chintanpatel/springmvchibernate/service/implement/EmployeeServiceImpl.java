package org.chintanpatel.springmvchibernate.service.implement;

import org.chintanpatel.springmvchibernate.model.Employee;
import org.chintanpatel.springmvchibernate.repository.EmployeeRepository;
import org.chintanpatel.springmvchibernate.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployeeList() {
        return employeeRepository.getAllEmployeeList();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.getEmployeeById(employeeId);
    }

    @Override
    public void editEmployee(Employee employee) {
        employeeRepository.editEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteEmployeeById(employeeId);
    }
}
