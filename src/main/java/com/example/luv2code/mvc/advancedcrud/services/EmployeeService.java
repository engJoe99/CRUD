package com.example.luv2code.mvc.advancedcrud.services;

import com.example.luv2code.mvc.advancedcrud.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
// This interface defines the contract for an Employee Service, providing methods to
// retrieve all employees, find an employee by ID, save a new employee, and delete
// an employee by ID. It facilitates the management of employee data in an application.

    List<Employee> findAll();

    Optional<Employee> findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
