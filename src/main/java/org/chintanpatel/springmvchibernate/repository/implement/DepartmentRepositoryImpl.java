package org.chintanpatel.springmvchibernate.repository.implement;

import org.chintanpatel.springmvchibernate.model.Department;
import org.chintanpatel.springmvchibernate.repository.DepartmentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentRepository")
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final SessionFactory sessionFactory;

    public DepartmentRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addDepartment(Department department) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.persist(department);
    }

    @Override
    public List<Department> getAllDepartmentList() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Department>departmentQuery = currentSession.createQuery("from Department", Department.class);
        return departmentQuery.getResultList();
    }

    @Override
    public Department getDepartmentById(Long departmentId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Department.class, departmentId);
    }

    @Override
    public void editDepartment(Department department) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.merge(department);
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Department department = currentSession.byId(Department.class).load(departmentId);
        currentSession.remove(department);
    }
}
