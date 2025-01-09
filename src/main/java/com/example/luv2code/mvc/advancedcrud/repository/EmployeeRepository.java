package com.example.luv2code.mvc.advancedcrud.repository;

import com.example.luv2code.mvc.advancedcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This interface defines the EmployeeRepository,
// which extends JpaRepository to provide CRUD operations for Employee entities identified by an Integer ID.
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // that's it .... no need to write any code here
    // Spring Data JPA will take care of the implementation,
    // and it will generate the SQL queries for us,
    // and it will also map the result set to the Employee object,
    // and it will also map the Employee object to the result set

    // add a method to sort by last name
    public List<Employee> findAllByOrderByLastNameAsc();

}
