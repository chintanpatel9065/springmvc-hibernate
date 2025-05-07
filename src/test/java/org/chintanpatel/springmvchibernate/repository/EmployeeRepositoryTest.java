package org.chintanpatel.springmvchibernate.repository;

import org.chintanpatel.springmvchibernate.config.TestDatabaseConfig;
import org.chintanpatel.springmvchibernate.model.Department;
import org.chintanpatel.springmvchibernate.model.Employee;
import org.chintanpatel.springmvchibernate.repository.implement.EmployeeRepositoryImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class EmployeeRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private EmployeeRepository employeeRepository;
    private Department department;
    private Employee employee;

    @BeforeEach
    public void setUp() {
        employeeRepository = new EmployeeRepositoryImpl(sessionFactory);
        
        // Create and save a department
        department = new Department(null, "IT");
        sessionFactory.getCurrentSession().persist(department);
        
        // Create an employee
        employee = new Employee(
                null,
                "John",
                "William",
                "Doe",
                "Male",
                new String[]{"Java", "Python"},
                "john.doe@example.com",
                "1234567890",
                LocalDate.of(1990, 1, 1),
                "johndoe",
                "password123",
                department
        );
    }

    @Test
    public void addEmployee_shouldPersistEmployee() {
        // Act
        employeeRepository.addEmployee(employee);
        
        // Assert
        assertThat(employee.getEmployeeId()).isNotNull();
        
        Employee savedEmployee = sessionFactory.getCurrentSession()
                .get(Employee.class, employee.getEmployeeId());
        
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo("John");
        assertThat(savedEmployee.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void getAllEmployeeList_shouldReturnAllEmployees() {
        // Arrange
        employeeRepository.addEmployee(employee);
        
        Employee employee2 = new Employee(
                null,
                "Jane",
                "Marie",
                "Smith",
                "Female",
                new String[]{"JavaScript", "C++"},
                "jane.smith@example.com",
                "9876543210",
                LocalDate.of(1992, 5, 15),
                "janesmith",
                "password456",
                department
        );
        employeeRepository.addEmployee(employee2);
        
        // Act
        List<Employee> employees = employeeRepository.getAllEmployeeList();
        
        // Assert
        assertThat(employees).hasSize(2);
        assertThat(employees).extracting(Employee::getFirstName)
                .containsExactlyInAnyOrder("John", "Jane");
    }

    @Test
    public void getEmployeeById_shouldReturnEmployeeWithGivenId() {
        // Arrange
        employeeRepository.addEmployee(employee);
        
        // Act
        Employee foundEmployee = employeeRepository.getEmployeeById(employee.getEmployeeId());
        
        // Assert
        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getFirstName()).isEqualTo("John");
        assertThat(foundEmployee.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void editEmployee_shouldUpdateEmployee() {
        // Arrange
        employeeRepository.addEmployee(employee);
        
        // Act
        employee.setFirstName("Jonathan");
        employee.setEmail("jonathan.doe@example.com");
        employeeRepository.editEmployee(employee);
        
        // Assert
        Employee updatedEmployee = sessionFactory.getCurrentSession()
                .get(Employee.class, employee.getEmployeeId());
        
        assertThat(updatedEmployee).isNotNull();
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Jonathan");
        assertThat(updatedEmployee.getEmail()).isEqualTo("jonathan.doe@example.com");
    }

    @Test
    public void deleteEmployeeById_shouldRemoveEmployee() {
        // Arrange
        employeeRepository.addEmployee(employee);
        
        // Act
        employeeRepository.deleteEmployeeById(employee.getEmployeeId());
        
        // Assert
        Employee deletedEmployee = sessionFactory.getCurrentSession()
                .get(Employee.class, employee.getEmployeeId());
        
        assertThat(deletedEmployee).isNull();
    }
}