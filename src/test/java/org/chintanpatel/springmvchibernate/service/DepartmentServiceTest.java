package org.chintanpatel.springmvchibernate.service;

import org.chintanpatel.springmvchibernate.model.Department;
import org.chintanpatel.springmvchibernate.repository.DepartmentRepository;
import org.chintanpatel.springmvchibernate.service.implement.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentService departmentService;

    private Department department1;
    private Department department2;

    @BeforeEach
    public void setUp() {
        departmentService = new DepartmentServiceImpl(departmentRepository);

        department1 = new Department(1L, "IT");
        department2 = new Department(2L, "HR");
    }

    @Test
    public void getAllDepartmentList_shouldReturnAllDepartments() {
        // Arrange
        List<Department> expectedDepartments = Arrays.asList(department1, department2);
        when(departmentRepository.getAllDepartmentList()).thenReturn(expectedDepartments);

        // Act
        List<Department> actualDepartments = departmentService.getAllDepartmentList();

        // Assert
        assertThat(actualDepartments).isEqualTo(expectedDepartments);
        verify(departmentRepository, times(1)).getAllDepartmentList();
    }

    @Test
    public void getDepartmentById_shouldReturnDepartmentWithGivenId() {
        // Arrange
        when(departmentRepository.getDepartmentById(1L)).thenReturn(department1);

        // Act
        Department actualDepartment = departmentService.getDepartmentById(1L);

        // Assert
        assertThat(actualDepartment).isEqualTo(department1);
        verify(departmentRepository, times(1)).getDepartmentById(1L);
    }

    @Test
    public void addDepartment_shouldCallRepositoryMethod() {
        // Act
        departmentService.addDepartment(department1);

        // Assert
        verify(departmentRepository, times(1)).addDepartment(department1);
    }

    @Test
    public void editDepartment_shouldCallRepositoryMethod() {
        // Act
        departmentService.editDepartment(department1);

        // Assert
        verify(departmentRepository, times(1)).editDepartment(department1);
    }

    @Test
    public void deleteDepartmentById_shouldCallRepositoryMethod() {
        // Act
        departmentService.deleteDepartmentById(1L);

        // Assert
        verify(departmentRepository, times(1)).deleteDepartmentById(1L);
    }
}