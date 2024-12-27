package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Landing page to show the form
    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("employee", new Employee());
        return "index";
    }

    // Endpoint to handle form submission and save employee data
    @PostMapping("/employee")
    public String saveEmployee(Employee employee) {
        // Note: employeeId is the user-supplied unique ID,
        // MongoDB _id will be generated automatically.
        employeeRepository.save(employee);
        return "redirect:/displayAll"; // after saving, redirect to displayAll page
    }

    // Display all employees (REST endpoint)
    @GetMapping("/displayAll")
    @ResponseBody
    public List<Employee> displayAllEmployees() {
        return employeeRepository.findAll();
    }

    // Display a specific employee by employeeId (REST endpoint)
    @GetMapping("/display/{employeeId}")
    @ResponseBody
    public Employee displayEmployee(@PathVariable String employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }
}
