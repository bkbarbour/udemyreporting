package com.bbarb75.udemyreporting.Services;

import com.bbarb75.udemyreporting.API.ApiClient;
import com.bbarb75.udemyreporting.Entities.Employee;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
            JsonNode nextNode = mapper.readTree(jsonResponse).get("next");

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
                    //System.out.println(employee.toString());

                    employees.add(employee);



                    //System.out.println();

                }
            }

            //System.out.println(employees.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } return employees;
    }

    public List<Employee> fetchAllEmployees() {
        List<Employee> allEmployees = new ArrayList<>();
        String nextUrl = "https://comtechnc.udemy.com/api-2.0/organizations/302706/analytics/user-course-activity/";

        while (nextUrl != null) {
            String jsonResponse = apiClient.fetchDataFromApi(nextUrl);
            List<Employee> employees = parseApiResponse(jsonResponse);
            allEmployees.addAll(employees);

            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(jsonResponse);
                JsonNode nextNode = rootNode.get("next");
                nextUrl = nextNode != null ? nextNode.asText() : null;
            } catch (Exception e) {
                e.printStackTrace();
                nextUrl = null;  // Stop on error
            }
        }
        return allEmployees;
    }

    public String fetchEmployeeData(){
        return apiClient.fetchDataFromApi("https://comtechnc.udemy.com/api-2.0/organizations/302706/analytics/user-course-activity/");
    }

    public String fetchEmployeeDataPage2(String nextUrl){
        return apiClient.fetchDataFromApi(nextUrl);
    }

   /* public void testApiResponse(String jsonResponse){
        System.out.println(parseApiResponse(jsonResponse));

    }*/
}
