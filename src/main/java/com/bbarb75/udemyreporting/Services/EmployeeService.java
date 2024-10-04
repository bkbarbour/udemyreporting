package com.bbarb75.udemyreporting.Services;

import com.bbarb75.udemyreporting.API.ApiClient;
import com.bbarb75.udemyreporting.Entities.Employee;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
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
                    Employee employee = new Employee();
                    JsonNode employeeFirstNameNode = employeeNode.get("user_name");
                    JsonNode employeeLastNameNode = employeeNode.get("user_surname");
                    JsonNode employeeCourseNameNode = employeeNode.get("course_title");
                    JsonNode employeeCourseAccessedNode = employeeNode.get("course_last_accessed_date");
                    JsonNode employeeMinutesSpent = employeeNode.get("num_video_consumed_minutes");

                    if (!employeeCourseAccessedNode.isMissingNode() && !employeeCourseAccessedNode.isNull()) {
                        String courseLastAccessed = employeeCourseAccessedNode.asText();

                        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
                        Instant instant = Instant.parse(courseLastAccessed);
                        LocalDateTime courseAccessedDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

                        // Format the date for display
                        DateTimeFormatter readableFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
                        String courseLastAccessedDateFormatted = courseAccessedDateTime.format(readableFormatter);

                        employee.setLastActivity(courseLastAccessedDateFormatted);  // Set formatted date string
                        employee.setLastActivityDateTime(courseAccessedDateTime);   // Set sortable LocalDateTime
                    } else {
                        employee.setLastActivity(null);
                        employee.setLastActivityDateTime(null);
                    }


                    String firstName = employeeFirstNameNode.asText();
                    String firstNameFinal = firstName.replaceAll("\"", "");
                    String lastName = employeeLastNameNode.asText();
                    String lastNameFinal = lastName.replaceAll("\"", "");
                    String minutesSpent = employeeMinutesSpent.asText();

                    String name = firstNameFinal + " " + lastNameFinal;

                    String courseName = employeeCourseNameNode.toString();


                    employee.setName(name);
                    //employee.setLastActivity(courseAccessedDateTime);
                    employee.setCourseName(courseName);
                    //System.out.println(employee.toString());
                    employee.setMinutesSpent(minutesSpent);

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

        // Sort employees by lastActivityDateTime (most recent first)
        allEmployees.sort((e1, e2) -> {
            LocalDateTime date1 = e1.getLastActivityDateTime();
            LocalDateTime date2 = e2.getLastActivityDateTime();

            if (date1 == null && date2 == null) {
                return 0;  // Both are null, they are equal
            }
            if (date1 == null) {
                return 1;  // Null comes after non-null
            }
            if (date2 == null) {
                return -1; // Non-null comes before null
            }
            return date2.compareTo(date1);  // Compare normally (most recent first)
        });


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
