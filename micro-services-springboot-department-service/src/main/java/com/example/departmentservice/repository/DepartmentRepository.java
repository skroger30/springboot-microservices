package com.example.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.departmentservice.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	Department findByDepartmentCode(String departmentCode);
}
