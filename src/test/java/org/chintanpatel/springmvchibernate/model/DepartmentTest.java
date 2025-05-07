package org.chintanpatel.springmvchibernate.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DepartmentTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void whenAllFieldsAreValid_thenNoViolations() {
        // Arrange
        Department department = new Department(1L, "IT");

        // Act
        Set<ConstraintViolation<Department>> violations = validator.validate(department);

        // Assert
        assertThat(violations).isEmpty();
    }

    @Test
    public void whenDepartmentNameIsEmpty_thenViolationOccurs() {
        // Arrange
        Department department = new Department(1L, "");  // Empty department name

        // Act
        Set<ConstraintViolation<Department>> violations = validator.validate(department);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("departmentName");
    }

    @Test
    public void whenDepartmentNameIsNull_thenViolationOccurs() {
        // Arrange
        Department department = new Department(1L, null);  // Null department name

        // Act
        Set<ConstraintViolation<Department>> violations = validator.validate(department);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("departmentName");
    }
}