package org.chintanpatel.springmvchibernate.repository;

import org.chintanpatel.springmvchibernate.config.TestDatabaseConfig;
import org.chintanpatel.springmvchibernate.model.Department;
import org.chintanpatel.springmvchibernate.repository.implement.DepartmentRepositoryImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class DepartmentRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private DepartmentRepository departmentRepository;
    private Department department;

    @BeforeEach
    public void setUp() {
        departmentRepository = new DepartmentRepositoryImpl(sessionFactory);
        
        // Create a department
        department = new Department(null, "IT");
    }

    @Test
    public void addDepartment_shouldPersistDepartment() {
        // Act
        departmentRepository.addDepartment(department);
        
        // Assert
        assertThat(department.getDepartmentId()).isNotNull();
        
        Department savedDepartment = sessionFactory.getCurrentSession()
                .get(Department.class, department.getDepartmentId());
        
        assertThat(savedDepartment).isNotNull();
        assertThat(savedDepartment.getDepartmentName()).isEqualTo("IT");
    }

    @Test
    public void getAllDepartmentList_shouldReturnAllDepartments() {
        // Arrange
        departmentRepository.addDepartment(department);
        
        Department department2 = new Department(null, "HR");
        departmentRepository.addDepartment(department2);
        
        // Act
        List<Department> departments = departmentRepository.getAllDepartmentList();
        
        // Assert
        assertThat(departments).hasSize(2);
        assertThat(departments).extracting(Department::getDepartmentName)
                .containsExactlyInAnyOrder("IT", "HR");
    }

    @Test
    public void getDepartmentById_shouldReturnDepartmentWithGivenId() {
        // Arrange
        departmentRepository.addDepartment(department);
        
        // Act
        Department foundDepartment = departmentRepository.getDepartmentById(department.getDepartmentId());
        
        // Assert
        assertThat(foundDepartment).isNotNull();
        assertThat(foundDepartment.getDepartmentName()).isEqualTo("IT");
    }

    @Test
    public void editDepartment_shouldUpdateDepartment() {
        // Arrange
        departmentRepository.addDepartment(department);
        
        // Act
        department.setDepartmentName("Information Technology");
        departmentRepository.editDepartment(department);
        
        // Assert
        Department updatedDepartment = sessionFactory.getCurrentSession()
                .get(Department.class, department.getDepartmentId());
        
        assertThat(updatedDepartment).isNotNull();
        assertThat(updatedDepartment.getDepartmentName()).isEqualTo("Information Technology");
    }

    @Test
    public void deleteDepartmentById_shouldRemoveDepartment() {
        // Arrange
        departmentRepository.addDepartment(department);
        
        // Act
        departmentRepository.deleteDepartmentById(department.getDepartmentId());
        
        // Assert
        Department deletedDepartment = sessionFactory.getCurrentSession()
                .get(Department.class, department.getDepartmentId());
        
        assertThat(deletedDepartment).isNull();
    }
}