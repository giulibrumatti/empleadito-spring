package com.rh.empleadito.service;

import com.rh.empleadito.model.Employee;
import com.rh.empleadito.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    private IEmployeeRepository empRepository;

    @Override
    public List<Employee> listEmployees() {
        List<Employee> listExit = empRepository.findAll();
        return listExit;
    }

    @Override
    public Employee searchForEmployeeForId(Long id) {
        Employee emp = empRepository.findById(id).orElse(null);
        return emp;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return empRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        empRepository.delete(employee);
    }
}
