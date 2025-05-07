package org.chintanpatel.springmvchibernate.controller;

import org.chintanpatel.springmvchibernate.model.Department;
import org.chintanpatel.springmvchibernate.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private MockMvc mockMvc;

    private Department department1;
    private Department department2;
    private List<Department> departmentList;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();

        department1 = new Department(1L, "IT");
        department2 = new Department(2L, "HR");
        departmentList = Arrays.asList(department1, department2);
    }

    @Test
    public void listDepartment_shouldReturnDepartmentFormView() throws Exception {
        // Arrange
        when(departmentService.getAllDepartmentList()).thenReturn(departmentList);

        // Act & Assert
        mockMvc.perform(get("/departments/listDepartment"))
                .andExpect(status().isOk())
                .andExpect(view().name("department-form"))
                .andExpect(model().attributeExists("department"))
                .andExpect(model().attributeExists("departmentList"));

        verify(departmentService, times(1)).getAllDepartmentList();
    }

    @Test
    public void manageDepartment_shouldReturnDepartmentFormViewWithDepartment() throws Exception {
        // Arrange
        when(departmentService.getDepartmentById(1L)).thenReturn(department1);
        when(departmentService.getAllDepartmentList()).thenReturn(departmentList);

        // Act & Assert
        mockMvc.perform(get("/departments/manageDepartment").param("departmentId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("department-form"))
                .andExpect(model().attributeExists("department"))
                .andExpect(model().attributeExists("departmentList"));

        verify(departmentService, times(1)).getDepartmentById(1L);
        verify(departmentService, times(1)).getAllDepartmentList();
    }

    @Test
    public void deleteDepartment_shouldRedirectToListDepartment() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/departments/deleteDepartment").param("departmentId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments/listDepartment"));

        verify(departmentService, times(1)).deleteDepartmentById(1L);
    }

    // Note: Testing insertOrUpdateDepartment with valid and invalid data would require more complex setup
    // due to form validation and binding. This would typically be covered in an integration test.
}