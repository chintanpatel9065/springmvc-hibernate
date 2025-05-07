package org.chintanpatel.springmvchibernate.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeTest {

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
        Employee employee = new Employee(
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

        // Act
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        // Assert
        assertThat(violations).isEmpty();
    }

    @Test
    public void whenFirstNameIsEmpty_thenViolationOccurs() {
        // Arrange
        Department department = new Department(1L, "IT");
        Employee employee = new Employee(
                null,
                "",  // Empty first name
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

        // Act
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("firstName");
    }

    @Test
    public void whenEmailIsInvalid_thenViolationOccurs() {
        // Arrange
        Department department = new Department(1L, "IT");
        Employee employee = new Employee(
                null,
                "John",
                "William",
                "Doe",
                "Male",
                new String[]{"Java", "Python"},
                "invalid-email",  // Invalid email
                "1234567890",
                LocalDate.of(1990, 1, 1),
                "johndoe",
                "password123",
                department
        );

        // Act
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("email");
    }

    @Test
    public void whenPasswordIsTooShort_thenViolationOccurs() {
        // Arrange
        Department department = new Department(1L, "IT");
        Employee employee = new Employee(
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
                "pass",  // Too short password
                department
        );

        // Act
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("password");
    }

    @Test
    public void whenBirthDateIsNull_thenViolationOccurs() {
        // Arrange
        Department department = new Department(1L, "IT");
        Employee employee = new Employee(
                null,
                "John",
                "William",
                "Doe",
                "Male",
                new String[]{"Java", "Python"},
                "john.doe@example.com",
                "1234567890",
                null,  // Null birth date
                "johndoe",
                "password123",
                department
        );

        // Act
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

        // Assert
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getPropertyPath().toString()).isEqualTo("birthDate");
    }
}