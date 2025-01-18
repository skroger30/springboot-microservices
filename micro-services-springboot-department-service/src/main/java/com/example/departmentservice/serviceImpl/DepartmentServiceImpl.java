package com.example.departmentservice.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;
import com.example.departmentservice.exceptionHandler.ResourceNotFoundException;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		return mapToDepartmentDto(departmentRepository.save(mapToDepartmentEntity(departmentDto)));
	}

	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {
		// TODO Auto-generated method stub
		try{
			return mapToDepartmentDto(departmentRepository.
					findByDepartmentCode(departmentCode));
		}

		catch(Exception resourceNotFoundException) {
			throw new ResourceNotFoundException("Department", "id", departmentCode);
		}
	}

	
	private Department mapToDepartmentEntity (DepartmentDto departmentDto) {
		// Convert DepartmentDto to Department Jpa entity.
		return modelMapper.map(departmentDto, Department.class);
		
	}
	
	private DepartmentDto mapToDepartmentDto (Department department) {		
		// Convert Department JPA entity to  DepartmentDto .
		return modelMapper.map(department, DepartmentDto.class);
	}
}
