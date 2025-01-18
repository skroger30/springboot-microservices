package com.example.employeeservice.service.impl;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeservice.dto.ApiResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exceptionHandler.ResourceNotFoundException;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.ApiClient;
import com.example.employeeservice.service.EmployeeService;

@Service
public class EmplyeeServicesImpl implements EmployeeService{

	@Autowired
	private  EmployeeRepository employeeRepository;
	
	@Autowired
	private  ModelMapper modelMapper;
	
//	****************************Synchronous Communication Approaches***************************************************************
//	@Autowired
//	private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient webClient;
//	
	@Autowired
	private ApiClient apiClient;
//	*******************************************************************************************
	
	
	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		return mapToEmployeetDto(employeeRepository.save(mapToEmployeeEntity(employeeDto)));
	}

	@Override
	public ApiResponseDto getEmployee(long employeeId) {
		try {

//			*******************************************************************************************	
//			ResponseEntity<DepartmentDto> responseEntity=restTemplate.getForEntity(
//					"http://localhost:8080/api/departments/"+
//					employeeRepository.findById(employeeId).get().getDepartmentCode(),
//					DepartmentDto.class);
			
//			DepartmentDto departmentDto = responseEntity.getBody();
//			*******************************************************************************************
			
//			DepartmentDto departmentDto = webClient.get()
//					.uri("http://localhost:8080/api/departments/"+
//							employeeRepository.findById(employeeId).get().getDepartmentCode())
//					.retrieve()
//					.bodyToMono(DepartmentDto.class)
//					.block();

//			*******************************************************************************************
			DepartmentDto departmentDto = apiClient.getDepartment(employeeRepository.
					findById(employeeId).get().getDepartmentCode());
			ApiResponseDto apiResponseDto = new ApiResponseDto();
			apiResponseDto.setDepartmentDto(departmentDto);
			apiResponseDto.setEmployeeDto(mapToEmployeetDto(employeeRepository.
													findById(employeeId).get()));
			
			return apiResponseDto;
		}
		catch(NoSuchElementException resourceNotFoundException) {
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
