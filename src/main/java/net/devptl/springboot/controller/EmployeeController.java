package net.devptl.springboot.controller;

import net.devptl.springboot.model.Employee;
import net.devptl.springboot.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    //Build Create employee REST API...
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }


    //Build get all employee REST API...
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //Build get employee by ID REST API...
    // http://localhost:8080/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
    }


    //Build update employee REST API...
    // http://localhost:8080/api/employees/1
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id), HttpStatus.OK);

    }

    //Build Delete Employee REST API...
    // http://localhost:8080/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){

        //delete emplyee from DB
        employeeService.deleteEmployee(id);

        return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);

    }

}
