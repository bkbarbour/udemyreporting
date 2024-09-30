package com.bbarb75.udemyreporting.Controllers;

import com.bbarb75.udemyreporting.Entities.Employee;
import com.bbarb75.udemyreporting.Services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    private final EmployeeService employeeService;

    public MainController(EmployeeService employeeService) {this.employeeService = employeeService;}

    @GetMapping("/home")
    public String getEmployee(@RequestParam(value = "employeeName", defaultValue = "select")String employeeName, Model model) {
        //String jsonResponse = employeeService.fetchEmployeeData();
        List<Employee> employees = employeeService.fetchAllEmployees(); //this is the issue - all data is being added but this is grabbing it before
        model.addAttribute("employees", employees);
        model.addAttribute("employeeName", employeeName);
        return "home";
    }
}
