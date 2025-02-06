package com.example.organizationService.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.organizationService.dto.OrganizationDto;
import com.example.organizationService.entity.Organization;
import com.example.organizationService.repository.OrganizationRepo;
import com.example.organizationService.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService{

	@Autowired
	private OrganizationRepo organizationRepo;
	
	@Autowired
	private  ModelMapper modelMapper;
	
	
	private Organization mapToOrganizationEntity (OrganizationDto organizationDto) {
		// Convert OrganizationDto to Organization Jpa entity.
		return modelMapper.map(organizationDto, Organization.class);
		
	}
	
	private OrganizationDto mapToOrganizationtDto (Organization organization) {		
		// Convert Organization JPA entity to  OrganizationDto .
		return modelMapper.map(organization, OrganizationDto.class);
	}
	
	
	@Override
	public OrganizationDto createOrganization(OrganizationDto organizationDto) {
		OrganizationDto savedOrganizationDto =
				mapToOrganizationtDto(organizationRepo.
						save(mapToOrganizationEntity(organizationDto)));		
		return savedOrganizationDto;
	}

	@Override
	public OrganizationDto findByOrgId(Long organizationId) {
		OrganizationDto getOrganizationDto = mapToOrganizationtDto(
				organizationRepo.findById(organizationId).get());
		return getOrganizationDto;
	}
	

	@Override
	public OrganizationDto findByOrganizationCode(String organizationCode) {
		OrganizationDto getOrganizationDto = mapToOrganizationtDto(
				organizationRepo.findByOrganizationCode(organizationCode));
		return getOrganizationDto;
	}
}
