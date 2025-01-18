package com.example.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.dto.ApiResponseDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmployee ( @RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(savedEmployeeDto,HttpStatus.CREATED);	
	}
	
	@GetMapping("{employee-id}")
	public ResponseEntity<ApiResponseDto> getEmployee ( @PathVariable("employee-id") long employeeId) {
		ApiResponseDto apiResponseDto = employeeService.getEmployee(employeeId);
		return new ResponseEntity<ApiResponseDto>(apiResponseDto,HttpStatus.OK);	
	}
}
