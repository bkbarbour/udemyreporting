package com.bbarb75.udemyreporting.Services;

import com.bbarb75.udemyreporting.API.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final ApiClient apiClient;

    @Autowired
    public EmployeeService(ApiClient apiClient){
        this.apiClient = apiClient;
    }
}
