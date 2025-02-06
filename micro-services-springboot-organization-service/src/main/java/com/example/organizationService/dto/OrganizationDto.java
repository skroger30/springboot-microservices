package com.example.organizationService.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

	private	long id;
	private String organizationName;
	private String organizationCode;
	private LocalDateTime organizationCreationDate; 
}
