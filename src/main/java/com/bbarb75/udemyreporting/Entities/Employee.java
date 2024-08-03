package com.bbarb75.udemyreporting.Entities;

public class Employee {
    String name;
    String lastActivity;

    public Employee(String name, String lastActivity) {
        this.name = name;
        this.lastActivity = lastActivity;
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


    @Override
    public String toString() { //overridden toString method to test print the ArrayLists to verify data
        return "Employee{" +
                "name='" + name + '\'' +
                ", lastActivity='" + lastActivity + '\'' +
                '}';
    }
}
