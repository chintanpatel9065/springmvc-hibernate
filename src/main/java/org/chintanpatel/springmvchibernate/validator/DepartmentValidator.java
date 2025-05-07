package org.chintanpatel.springmvchibernate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.chintanpatel.springmvchibernate.model.Department;

public class DepartmentValidator implements ConstraintValidator<ValidDepartment, Department> {

    @Override
    public boolean isValid(Department department, ConstraintValidatorContext constraintValidatorContext) {
        return department != null && department.getDepartmentId() > 0;
    }
}
