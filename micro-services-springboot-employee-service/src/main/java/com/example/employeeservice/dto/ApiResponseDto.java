package com.example.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {
	private DepartmentDto departmentDto;
	private EmployeeDto employeeDto;
	private OrganizationDto organizationDto;

}
