package com.org.emp.salarymgmt.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.org.emp.salarymgmt.dao.EmployeeRepo;
import com.org.emp.salarymgmt.entity.Employee;
import com.org.emp.salarymgmt.service.FileUploadService;

@RestController
public class EmployeeController {
	
	@Autowired
	public FileUploadService fileUploadService;
	@Autowired
	public EmployeeRepo employeeRepo;
	
	Logger logger = Logger.getLogger(EmployeeController.class);
	
	@GetMapping("/getUsers")
	public List<Employee> getUsers() {
		return employeeRepo.findAll();
	}
	
	@PostMapping("/users/upload")
	public ResponseEntity<Object> createUser(@RequestBody MultipartFile file) throws IOException {
				InputStream in=file.getInputStream();
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(file).toUri();
		if (! "text/csv".equals(file.getContentType())) {
			return ResponseEntity.badRequest().build();
		}
		List<Employee> savedEmployees =  fileUploadService.readAndSaveEmpDetails(in);
		
		if(savedEmployees!=null && savedEmployees.size()>0) {
			
			return ResponseEntity.created(location).build();
		}
		else {
			return ResponseEntity.status(500).build();
		}
	}

	
	@GetMapping("/users")
	public List<Employee> getUsersWithCriteria(@RequestParam("minSalary") double minSalary,
			@RequestParam("maxSalary") double maxSalary, @RequestParam("offset") int offset,
			@RequestParam("limit") int limit, @RequestParam("sort") String sorting) {
		System.out.println("minSalary="+minSalary);
		System.out.println("sorting="+sorting);
		Sort  sort ;
		String sortingCol = sorting.charAt(0)=='-'?"desc":"asc";
		String sortingProp = sorting.substring(1, sorting.length());
		if(sortingCol=="asc") {
			sort = Sort.by(sortingProp).ascending();
		}else {
			sort = Sort.by(sortingProp).descending();
		}
		Pageable pageable = PageRequest.of(offset, limit,sort);
		return employeeRepo.findByCriteria(minSalary, maxSalary, pageable);
	}
	
	@PatchMapping("/users/update/{id}")
	public ResponseEntity<Object> updateEmployee(@PathVariable("id") String employeeId ,@RequestBody Employee employee) {
		Optional<Employee> emp = employeeRepo.findById(employeeId);
		if(emp.isPresent()) {
			emp.get().setLogin(employee.getLogin());
			emp.get().setName(employee.getName());
			emp.get().setSalary(employee.getSalary());
			employeeRepo.save(emp.get());
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/users/create")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
			employeeRepo.save(employee);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(employee.getId()).toUri();
			return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("users/delete/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable("id") String employeeId) {
		Optional<Employee> emp = employeeRepo.findById(employeeId);
		if(emp.isPresent()) {
			employeeRepo.delete(emp.get());
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/users/{id}")
	public Employee getEmployee(@PathVariable("id") String employeeId) {
		Optional<Employee> emp = employeeRepo.findById(employeeId);
		if(emp.isPresent()) {
			return emp.get();
		}else {
			return null;
		}
	}
}
