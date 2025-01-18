package com.example.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.employeeservice.dto.DepartmentDto;

@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
public interface ApiClient {

//	Get Department REST API
//	http://localhost:8080/api/departments/{code}
	@GetMapping("/api/departments/{department-code}")
	public DepartmentDto getDepartment(@PathVariable("department-code")  String departmentCode);

}
