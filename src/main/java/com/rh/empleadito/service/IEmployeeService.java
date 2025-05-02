package com.rh.empleadito.service;


import com.rh.empleadito.model.Employee;

import java.util.List;

public interface IEmployeeService {

    public List<Employee> listEmployees();
    public Employee searchForEmployeeForId(Long id);
    public Employee addEmployee(Employee employee);
    public void deleteEmployee(Employee employee);
}
