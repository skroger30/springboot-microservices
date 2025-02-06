package com.example.organizationService.service;

import com.example.organizationService.dto.OrganizationDto;
import com.example.organizationService.entity.Organization;

public interface OrganizationService {

	OrganizationDto createOrganization(OrganizationDto organizationDto);
	OrganizationDto findByOrgId(Long organizationId);
	OrganizationDto findByOrganizationCode(String organizationCode);

}
