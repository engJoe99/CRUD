package com.example.luv2code.mvc.advancedcrud.controller;


import com.example.luv2code.mvc.advancedcrud.entity.Employee;
import com.example.luv2code.mvc.advancedcrud.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping ("/employees") // Base mapping for URL requests
public class EmployeeController {

   // Inject EmployeeService
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // This method handles GET requests to the "/list" endpoint, retrieves a list of employees from the service layer,
    // adds the list to the model, and returns the view name for displaying the employee list.
    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // Get employees from the service
        List<Employee> theEmployees = employeeService.findAll();

        // Add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    // This method handles GET requests to the "/showEmployeeForm" endpoint,
    // initializes a new Employee object, and adds it to the model for binding
    // with the form data. It then returns the view name for the employee form.
    @GetMapping("/showFormForAdd")
    public String showEmployeeForm(Model theModel) {

        // create a model attribute to bind form data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }


    // This method handles GET requests to show the employee update form. It retrieves an employee by ID using the employee service,
    // adds the employee to the model for prepopulation in the form, and returns the view name for the employee form.
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

        // get the employee from the service
        // If the Employee is not found,
        // it throws a RuntimeException with a message indicating the ID that was not found.
        Employee theEmployee = employeeService.findById(theId).orElseThrow(() ->
                new RuntimeException("Employee not found with ID: " + theId));

        // set employee in the model to prepopulate the form
        theModel.addAttribute("employee", theEmployee);

        // send over to our form
        return "employees/employee-form";
    }



    // to save an employee. It uses the @PostMapping annotation to map the "/save" URL to
    // the saveEmployee method, which accepts an Employee object as a model attribute.
    // After saving the employee using the employeeService, it redirects the user to
    // the employee list page to prevent duplicate submissions.
    @PostMapping("/save")
    public String saveEmpolyee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee using our service
        employeeService.save(theEmployee);

        // using 'redirect' to prevent duplicate submissions
        return "redirect:/employees/list";
    }


    // This method handles the HTTP GET request for deleting an employee by their ID.
    // It retrieves the employee ID from the request parameters, calls the service to delete the employee,
    // and then redirects the user to the employee listing page.
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        // delete the employee
        employeeService.deleteById(theId);

        // redirect to /employees/list
        return "redirect:/employees/list";
    }







}
