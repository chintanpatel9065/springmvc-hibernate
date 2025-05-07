package org.chintanpatel.springmvchibernate.service;

import org.chintanpatel.springmvchibernate.model.Department;
import org.chintanpatel.springmvchibernate.model.Employee;
import org.chintanpatel.springmvchibernate.repository.EmployeeRepository;
import org.chintanpatel.springmvchibernate.service.implement.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository);

        Department department = new Department(1L, "IT");
        
        employee1 = new Employee(
                1L,
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
        
        employee2 = new Employee(
                2L,
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
    }

    @Test
    public void getAllEmployeeList_shouldReturnAllEmployees() {
        // Arrange
        List<Employee> expectedEmployees = Arrays.asList(employee1, employee2);
        when(employeeRepository.getAllEmployeeList()).thenReturn(expectedEmployees);

        // Act
        List<Employee> actualEmployees = employeeService.getAllEmployeeList();

        // Assert
        assertThat(actualEmployees).isEqualTo(expectedEmployees);
        verify(employeeRepository, times(1)).getAllEmployeeList();
    }

    @Test
    public void getEmployeeById_shouldReturnEmployeeWithGivenId() {
        // Arrange
        when(employeeRepository.getEmployeeById(1L)).thenReturn(employee1);

        // Act
        Employee actualEmployee = employeeService.getEmployeeById(1L);

        // Assert
        assertThat(actualEmployee).isEqualTo(employee1);
        verify(employeeRepository, times(1)).getEmployeeById(1L);
    }

    @Test
    public void addEmployee_shouldCallRepositoryMethod() {
        // Act
        employeeService.addEmployee(employee1);

        // Assert
        verify(employeeRepository, times(1)).addEmployee(employee1);
    }

    @Test
    public void editEmployee_shouldCallRepositoryMethod() {
        // Act
        employeeService.editEmployee(employee1);

        // Assert
        verify(employeeRepository, times(1)).editEmployee(employee1);
    }

    @Test
    public void deleteEmployeeById_shouldCallRepositoryMethod() {
        // Act
        employeeService.deleteEmployeeById(1L);

        // Assert
        verify(employeeRepository, times(1)).deleteEmployeeById(1L);
    }
}