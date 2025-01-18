package com.example.departmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
//	Save Department REST API
//	http://localhost:8080/api/departments
	@PostMapping
	public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody  DepartmentDto departmentDto) {
		DepartmentDto savedDepartmentDto = departmentService.saveDepartment(departmentDto);		
		return new ResponseEntity<>(savedDepartmentDto,HttpStatus.CREATED);
		
	}
	
//	Get Department REST API
//	http://localhost:8080/api/departments/{code}
	@GetMapping("{department-code}")
	public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code")  String departmentCode) {
		DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);		
		return new ResponseEntity<>(departmentDto,HttpStatus.OK);
		
	}

}
