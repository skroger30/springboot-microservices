package com.example.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.employeeservice.dto.OrganizationDto;

//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
// To switch URL dynamically.
@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationApiClient {

	@GetMapping("/api/org-by-code/{organization-code}")
	public OrganizationDto getOrganization(@PathVariable("organization-code")  String organizationCode);

}
