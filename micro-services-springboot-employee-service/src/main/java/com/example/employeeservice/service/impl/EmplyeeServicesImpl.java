package com.example.employeeservice.service.impl;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.employeeservice.dto.ApiResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.dto.OrganizationDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exceptionHandler.ResourceNotFoundException;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.DepartmentApiClient;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.service.OrganizationApiClient;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class EmplyeeServicesImpl implements EmployeeService{

	private static final Logger LOG = LoggerFactory.getLogger(EmplyeeServicesImpl.class);
	@Autowired
	private  EmployeeRepository employeeRepository;
	
	@Autowired
	private  ModelMapper modelMapper;
	
//	****************************Synchronous Communication Approaches***************************************************************
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private DepartmentApiClient departmentApiClient;
	
	@Autowired
	private OrganizationApiClient organizationApiClient;
//	*******************************************************************************************
	
	
	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		return mapToEmployeetDto(employeeRepository.save(mapToEmployeeEntity(employeeDto)));
	}

//	@CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
	@Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
	@Override
	public ApiResponseDto getEmployee(long employeeId) {
		LOG.info("inside getEmployee() ");
		
		try {

//			*****************Logic to Make API Call from ONE Service to OTHER******************************************************************	
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
			
//			OrganizationDto organizationDto = webClient.get()
//					.uri("http://localhost:8083/api/org-by-code/"+
//							employeeRepository.findById(employeeId).get().getOrganizationCode())
//					.retrieve()
//					.bodyToMono(OrganizationDto.class)
//					.block();

//			*******************************************************************************************
			DepartmentDto departmentDto = departmentApiClient.getDepartment(employeeRepository.
					findById(employeeId).get().getDepartmentCode());
			
			OrganizationDto organizationDto = organizationApiClient.getOrganization(employeeRepository.
					findById(employeeId).get().getOrganizationCode());
			

			
			ApiResponseDto apiResponseDto = new ApiResponseDto();
			apiResponseDto.setOrganizationDto(organizationDto);
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
		// Convert EmployeeDto to Employee Jpa entity.
		return modelMapper.map(EmployeeDto, Employee.class);
		
	}
	
	private EmployeeDto mapToEmployeetDto (Employee employee) {		
		// Convert Employee JPA entity to  EmployeeDto .
		return modelMapper.map(employee, EmployeeDto.class);
	}

	
	public ApiResponseDto getDefaultDepartment(long employeeId, Exception exception) {
		
		LOG.info("inside getDefaultDepartment() ");
		Employee employee = employeeRepository.findById(employeeId).get();
		
		EmployeeDto employeeDto = new EmployeeDto(
				employee.getId(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartmentCode(),
				employee.getOrganizationCode());
		
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("Default");
		departmentDto.setDepartmentDescription("Default");
		departmentDto.setDepartmentCode("Default");
		
		OrganizationDto organizationDto = new OrganizationDto();
		organizationDto.setOrganizationName("DEFAULT");
		organizationDto.setOrganizationCode("DEFAULT");
		
		ApiResponseDto apiResponseDto = new ApiResponseDto();
		apiResponseDto.setDepartmentDto(departmentDto);
		apiResponseDto.setEmployeeDto(employeeDto);
		apiResponseDto.setOrganizationDto(organizationDto);
		
		return apiResponseDto;
		
	}
}
