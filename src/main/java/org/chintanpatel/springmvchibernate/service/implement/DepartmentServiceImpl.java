package org.chintanpatel.springmvchibernate.service.implement;

import org.chintanpatel.springmvchibernate.model.Department;
import org.chintanpatel.springmvchibernate.repository.DepartmentRepository;
import org.chintanpatel.springmvchibernate.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void addDepartment(Department department) {
        departmentRepository.addDepartment(department);
    }

    @Override
    public List<Department> getAllDepartmentList() {
        return departmentRepository.getAllDepartmentList();
    }

    @Override
    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.getDepartmentById(departmentId);
    }

    @Override
    public void editDepartment(Department department) {
        departmentRepository.editDepartment(department);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteDepartmentById(departmentId);
    }
}
