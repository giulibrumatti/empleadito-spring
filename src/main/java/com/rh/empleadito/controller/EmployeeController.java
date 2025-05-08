package com.rh.empleadito.controller;

import com.rh.empleadito.model.Employee;
import com.rh.empleadito.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("empleadito-app")
@CrossOrigin(value = "http://localhost:5173")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private IEmployeeService empServ;

    @GetMapping("/empleados")
    public List<Employee> getEmployees(){
        List<Employee> listEmployees = empServ.listEmployees();
        listEmployees.forEach((employee -> logger.info(employee.toString())));
        return listEmployees;
    }

    @PostMapping("/crearEmpleado")
    public Employee crearDuenio(@RequestBody Employee emp){
        logger.info("Empleado a agregar: " + emp);
        return empServ.addEmployee(emp);
    }
}
