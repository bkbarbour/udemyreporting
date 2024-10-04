package com.bbarb75.udemyreporting.Entities;

import java.time.LocalDateTime;

public class Employee {
    String name;
    String lastActivity;
    String courseName;

    public LocalDateTime getLastActivityDateTime() {
        return lastActivityDateTime;
    }

    public void setLastActivityDateTime(LocalDateTime lastActivityDateTime) {
        this.lastActivityDateTime = lastActivityDateTime;
    }

    LocalDateTime lastActivityDateTime;

    public String getMinutesSpent() {
        return minutesSpent;
    }

    public void setMinutesSpent(String minutesSpent) {
        this.minutesSpent = minutesSpent;
    }

    String minutesSpent;

    public Employee(String name, String lastActivity, String courseName, String minutesSpent) {
        this.name = name;
        this.lastActivity = lastActivity;
        this.courseName = courseName;
        this.minutesSpent = minutesSpent;
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
