package com.example.organizationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.organizationService.dto.OrganizationDto;
import com.example.organizationService.service.OrganizationService;

@RestController
@RequestMapping("/api")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
	
	@PostMapping(value = "/organization")
	public ResponseEntity<OrganizationDto> createOrganization(@RequestBody OrganizationDto organizationDto){		
		OrganizationDto savedOrganizationDto =organizationService.createOrganization(organizationDto);
		return new ResponseEntity<OrganizationDto>(savedOrganizationDto, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/org-by-id/{id}")
	public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable("id") long organizationId){		
		OrganizationDto getOrganizationDto = organizationService.findByOrgId(organizationId);
		return new ResponseEntity<OrganizationDto>(getOrganizationDto, HttpStatus.OK);

	}
	
	@GetMapping("/org-by-code/{code}")
	public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable("code") String organizationCode){		
		OrganizationDto getOrganizationDto = organizationService.findByOrganizationCode(organizationCode);
		return new ResponseEntity<OrganizationDto>(getOrganizationDto, HttpStatus.OK);

	}
}
