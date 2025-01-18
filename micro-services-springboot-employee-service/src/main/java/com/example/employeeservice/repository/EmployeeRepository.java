package com.example.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeeservice.entity.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long>{

}
