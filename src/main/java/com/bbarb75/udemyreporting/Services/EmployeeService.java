package com.bbarb75.udemyreporting.Services;

import com.bbarb75.udemyreporting.API.ApiClient;
import com.bbarb75.udemyreporting.Entities.Employee;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private final ApiClient apiClient;

    @Autowired
    public EmployeeService(ApiClient apiClient){
        this.apiClient = apiClient;
    }

    public List<Employee> parseApiResponse(String jsonResponse){
        List<Employee> employees = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode responseNode = mapper.readTree(jsonResponse).get("results");

            if (responseNode !=null){
                for (JsonNode employeeNode : responseNode){
                    JsonNode employeeFirstNameNode = employeeNode.get("user_name");
                    JsonNode employeeLastNameNode = employeeNode.get("user_surname");
                    JsonNode employeeCourseAccessedNode = employeeNode.get("course_last_accessed_date");

                    String name = employeeFirstNameNode.toString() + " " + employeeLastNameNode.toString();
                    String courseLastAccessed = employeeCourseAccessedNode.toString();

                    Employee employee = new Employee();
                    employee.setName(name);
                    employee.setLastActivity(courseLastAccessed);

                    employees.add(employee);

                    System.out.println(employees);

                }
            }
            System.out.println(employees.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } return employees;
    }

   /* public void testApiResponse(String jsonResponse){
        System.out.println(parseApiResponse(jsonResponse));

    }*/
}
