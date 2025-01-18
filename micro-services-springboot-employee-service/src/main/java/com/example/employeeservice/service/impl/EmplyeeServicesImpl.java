package com.example.employeeservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exceptionHandler.ResourceNotFoundException;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.EmployeeService;

@Service
public class EmplyeeServicesImpl implements EmployeeService{

	@Autowired
	private  EmployeeRepository employeeRepository;
	
	@Autowired
	private  ModelMapper modelMapper;
	
	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		return mapToEmployeetDto(employeeRepository.save(mapToEmployeeEntity(employeeDto)));
	}

	@Override
	public EmployeeDto getEmployee(long employeeId) {
		try {
			return mapToEmployeetDto(employeeRepository.findById(employeeId).get());
		}
		catch(Exception resourceNotFoundException) {
			throw new ResourceNotFoundException("Employee","id",employeeId);
		}	
	}
	
	
	private Employee mapToEmployeeEntity (EmployeeDto EmployeeDto) {
		// Convert DepartmentDto to Department Jpa entity.
		return modelMapper.map(EmployeeDto, Employee.class);
		
	}
	
	private EmployeeDto mapToEmployeetDto (Employee employee) {		
		// Convert Department JPA entity to  DepartmentDto .
		return modelMapper.map(employee, EmployeeDto.class);
	}

}
