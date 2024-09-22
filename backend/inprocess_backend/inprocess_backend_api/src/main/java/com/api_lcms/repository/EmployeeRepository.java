package com.api_lcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_lcms.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
