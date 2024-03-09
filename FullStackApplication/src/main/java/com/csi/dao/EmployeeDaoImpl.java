package com.csi.dao;

import com.csi.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    private static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();

    @Override
    public void signUp(Employee employee) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();


    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        boolean status = false;
        for (Employee employee : findAll()) {
            if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
                status = true;
                break;
            }
        }
        return status;
    }

    @Override
    public List<Employee> findAll() {
        Session session = factory.openSession();
        return session.createQuery("From Employee").list();
    }

    @Override
    public Employee findById(int empId) {
        Session session = factory.openSession();
        return (Employee) session.get(Employee.class, empId);
    }

    @Override
    public void updateData(int empId, Employee employee) {
        Employee employee1 = findById(empId);
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpState(employee.getEmpState());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpUID(employee.getEmpUID());
        employee1.setEmpPancard(employee.getEmpPancard());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        session.update(employee1);
        transaction.commit();

    }

    @Override
    public void deleteById(int empId) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = findById(empId);
        session.delete(employee);
        transaction.commit();

    }

    @Override
    public void deleteAll() {
        Session session = factory.openSession();
        for (Employee employee : findAll()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();

        }
    }


}
