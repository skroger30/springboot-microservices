package com.example.organizationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.organizationService.entity.Organization;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization, Long>{

	Organization findByOrganizationCode(String organizationCode);
}
