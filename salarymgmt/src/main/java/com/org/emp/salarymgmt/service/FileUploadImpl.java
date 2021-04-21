package com.org.emp.salarymgmt.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.org.emp.salarymgmt.controller.EmployeeController;
import com.org.emp.salarymgmt.dao.EmployeeRepo;
import com.org.emp.salarymgmt.entity.Employee;

@Component
public class FileUploadImpl implements FileUploadService {
	Logger logger = Logger.getLogger(FileUploadImpl.class);
	@Autowired
	public EmployeeRepo employeeRepo;

	public List<Employee> readAndSaveEmpDetails(InputStream in) {

		 try {
			List<String> list = new ArrayList<>();
			 BufferedReader br
		      = new BufferedReader(new InputStreamReader(in , "UTF-8"));
			 	String line;
		        while ((line = br.readLine()) != null) {
		        	if(!line.startsWith("#"))
		        	list.add(line);
		        }
		            List<Employee> employeeList = new ArrayList<>()  ;
		            
		            for(String empRecord : list) {
		            	if(empRecord!=null ) {
		            	String[] contents = empRecord.split(",");
			            	try {
				            	if((contents.length == Employee.class.getDeclaredFields().length) &&
				            			(Double.parseDouble(contents[3]) >= 0.00))
				            	employeeList.add(CreateEmployeeObject(contents));
			            	}catch(Exception e) {
			            		logger.error("Error while reading the line"+empRecord,e);
			            	}
		            	}
		            }
		            List<Employee> savedEmployees = employeeRepo.saveAll(employeeList);
		            logger.info(" Number of employees saved are "+savedEmployees.size());
		            return savedEmployees;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return null;
	}

	public Employee  CreateEmployeeObject(String[] contents) {
		return  new Employee(contents[0],contents[1],contents[2],Double.parseDouble(contents[3]));
	}

	@Override
	public List<Employee> getAllUsers() {
		return employeeRepo.findAll();
	}

	@Override
	public List<Employee> findByCriteria(double minSalary, double maxSalary, Pageable pageable) {
		return employeeRepo.findByCriteria(minSalary, maxSalary, pageable);
	}

	@Override
	public Optional<Employee> findById(String employeeId) {
		return employeeRepo.findById(employeeId);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		 Employee emp= employeeRepo.save(employee);
		 return emp;
	}

	@Override
	public void deleteById(String employeeId) {
		 employeeRepo.deleteById(employeeId);
	}
}
