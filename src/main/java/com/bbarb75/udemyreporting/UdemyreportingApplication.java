package com.bbarb75.udemyreporting;

import com.bbarb75.udemyreporting.API.ApiClient;
import com.bbarb75.udemyreporting.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UdemyreportingApplication implements CommandLineRunner {

	@Autowired
	private ApiClient apiClient;
	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(UdemyreportingApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		apiClient.fetchDataFromApi();
		employeeService.parseApiResponse(apiClient.fetchDataFromApi());
	}

}
