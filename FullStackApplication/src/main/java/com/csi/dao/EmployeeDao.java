package com.csi.dao;

import com.csi.model.Employee;

import java.util.List;

public interface EmployeeDao {

    void signUp(Employee employee);

    boolean signIn(String empEmailId, String empPassword);

    List<Employee> findAll();

    Employee findById(int empId);

    void updateData(int empId, Employee employee);

    void deleteById(int empId);

    void deleteAll();





}
