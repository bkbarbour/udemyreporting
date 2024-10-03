package com.bbarb75.udemyreporting.Controllers;

import com.bbarb75.udemyreporting.Entities.Employee;
import com.bbarb75.udemyreporting.Services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private final EmployeeService employeeService;

    public MainController(EmployeeService employeeService) {this.employeeService = employeeService;}

    @GetMapping("/home")
    public String getEmployee(@RequestParam(value = "employeeName", defaultValue = "select")String employeeName, Model model) {
        //String jsonResponse = employeeService.fetchEmployeeData();
        List<Employee> employees = employeeService.fetchAllEmployees(); //this is the issue - all data is being added but this is grabbing it before

        List<String> uniqueEmployeeNames = employees.stream()
                        .map(Employee::getName)
                        .distinct()
                        .collect(Collectors.toList());

        if(!employeeName.equals("select")) {
            employees = employees.stream()
                    .filter(employee -> employee.getName().equals(employeeName))
                    .collect(Collectors.toList());
        }
        model.addAttribute("employees", employees);
        model.addAttribute("employeeName", employeeName);
        model.addAttribute("uniqueEmployeeNames", uniqueEmployeeNames);
        return "home";
    }



    @GetMapping("/filterEmployees")
    @ResponseBody
    public List<Employee> filterEmployees(@RequestParam("employeeName") String employeeName) {
        List<Employee> employees = employeeService.fetchAllEmployees();

        // Filter employees based on the selected name
        if (!employeeName.equals("select")) {
            employees = employees.stream()
                    .filter(employee -> employee.getName().equals(employeeName))
                    .collect(Collectors.toList());
        }

        return employees;
    }

}
