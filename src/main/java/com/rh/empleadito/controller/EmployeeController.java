package com.rh.empleadito.controller;

import com.rh.empleadito.exception.ResourceNotFoundException;
import com.rh.empleadito.model.Employee;
import com.rh.empleadito.service.IEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("empleadito-app")
@CrossOrigin(value = "http://localhost:3000")
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

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Employee> searchForId(@PathVariable Long id){
        Employee emp = empServ.searchForEmployeeForId(id);
        if(emp == null)
            throw new ResourceNotFoundException("No se encontre el ID: "+ id);

        return ResponseEntity.ok(emp);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Employee> editEmployee(@PathVariable Long id,
                                               @RequestBody Employee empReceived){
        Employee employee = empServ.searchForEmployeeForId(id);
        if(employee == null){
            throw new ResourceNotFoundException("El id recibido no existe: " + id);
        }
        employee.setName(empReceived.getName());
        employee.setDepartament(empReceived.getDepartament());
        employee.setSalary(empReceived.getSalary());
        empServ.addEmployee(employee);
        return ResponseEntity.ok(employee);
    }
}
