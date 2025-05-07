package org.chintanpatel.springmvchibernate.controller;

import org.chintanpatel.springmvchibernate.model.Department;
import org.chintanpatel.springmvchibernate.model.Employee;
import org.chintanpatel.springmvchibernate.service.DepartmentService;
import org.chintanpatel.springmvchibernate.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    private Employee employee1;
    private Employee employee2;
    private Department department;
    private List<Employee> employeeList;
    private List<Department> departmentList;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        department = new Department(1L, "IT");
        List<Department> departmentList = Arrays.asList(department, new Department(2L, "HR"));

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

        employeeList = Arrays.asList(employee1, employee2);
        this.departmentList = departmentList;
    }

    @Test
    public void listEmployee_shouldReturnEmployeeFormView() throws Exception {
        // Arrange
        when(departmentService.getAllDepartmentList()).thenReturn(departmentList);
        when(employeeService.getAllEmployeeList()).thenReturn(employeeList);

        // Act & Assert
        mockMvc.perform(get("/employees/listEmployee"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee-form"))
                .andExpect(model().attributeExists("employee"))
                .andExpect(model().attributeExists("departmentList"))
                .andExpect(model().attributeExists("employeeList"));

        verify(departmentService, times(1)).getAllDepartmentList();
        verify(employeeService, times(1)).getAllEmployeeList();
    }

    @Test
    public void manageEmployee_shouldReturnEmployeeFormViewWithEmployee() throws Exception {
        // Arrange
        when(employeeService.getEmployeeById(1L)).thenReturn(employee1);
        when(departmentService.getAllDepartmentList()).thenReturn(departmentList);
        when(employeeService.getAllEmployeeList()).thenReturn(employeeList);

        // Act & Assert
        mockMvc.perform(get("/employees/manageEmployee").param("employeeId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee-form"))
                .andExpect(model().attributeExists("employee"))
                .andExpect(model().attributeExists("departmentList"))
                .andExpect(model().attributeExists("employeeList"));

        verify(employeeService, times(1)).getEmployeeById(1L);
        verify(departmentService, times(1)).getAllDepartmentList();
        verify(employeeService, times(1)).getAllEmployeeList();
    }

    @Test
    public void deleteEmployee_shouldRedirectToListEmployee() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/employees/deleteEmployee").param("employeeId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees/listEmployee"));

        verify(employeeService, times(1)).deleteEmployeeById(1L);
    }

    // Note: Testing insertOrUpdateEmployee with valid and invalid data would require more complex setup
    // due to form validation and binding. This would typically be covered in an integration test.
}