package com.org.emp.salarymgmt.service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.org.emp.salarymgmt.entity.Employee;

public interface FileUploadService {

	public List<Employee> readAndSaveEmpDetails(InputStream in);
	
	public List<Employee> getAllUsers();
	
	public List<Employee> findByCriteria(double minSalary, double maxSalary, Pageable pageable);
	
	public Optional<Employee> findById(String employeeId);
	
	public Employee saveEmployee(Employee employee);
	
	public void deleteById(String employeeId);
	
}
