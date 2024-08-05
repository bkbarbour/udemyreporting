package com.bbarb75.udemyreporting.Entities;

public class Employee {
    String name;
    String lastActivity;



    String courseName;

    public Employee(String name, String lastActivity) {
        this.name = name;
        this.lastActivity = lastActivity;
        this.courseName = courseName;
    }

    public Employee(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(String lastActivity) {
        this.lastActivity = lastActivity;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() { //overridden toString method to test print the ArrayLists to verify data
        return "Employee{" +
                "name='" + name + '\'' +
                ", lastActivity='" + lastActivity + '\'' +
                '}';
    }
}
