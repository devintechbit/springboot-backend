package net.devptl.springboot.service.impl;



import net.devptl.springboot.exception.ResourceNotFoundException;
import net.devptl.springboot.model.Employee;
import net.devptl.springboot.repository.EmployeeRepository;
import net.devptl.springboot.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);


    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {

        // APL approaches ..
//        Optional<Employee> employee = employeeRepository.findById(id);
//
//        if(employee.isPresent()){
//            return employee.get();
//        }
//        else {
//            throw new ResourceNotFoundException("Employee", "Id", id);
//        }


        //Lambda expression  approaches..
        return employeeRepository.findById(id).orElseThrow(() ->
                      new ResourceNotFoundException("Employee", "Id", id));

    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        //We need to check whether employee with given id is existed in DB or not.
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() ->
                      new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());


        // save existing employee to DB.
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {

        // We need to check whether employee is existing in DB or not...
        employeeRepository.findById(id).orElseThrow(() ->
                       new ResourceNotFoundException("Employee", "Id", id));

        employeeRepository.deleteById(id);
    }
}
