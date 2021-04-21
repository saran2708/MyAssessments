package com.org.emp.salarymgmt.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.org.emp.salarymgmt.entity.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {
	
	@Query(value = "SELECT  e  FROM  Employee  e WHERE e.Salary >= :minSalary AND"
			+ " e.Salary <= :maxSalary")
	public List<Employee> findByCriteria(@Param("minSalary") double minSalary , @Param("maxSalary") double maxSalary ,
			Pageable pageable);
}
