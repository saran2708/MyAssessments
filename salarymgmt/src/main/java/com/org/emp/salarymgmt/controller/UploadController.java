package com.org.emp.salarymgmt.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.org.emp.salarymgmt.entity.Employee;
import com.org.emp.salarymgmt.entity.SearchEmployees;
import com.org.emp.salarymgmt.service.FileUploadService;

@Controller
public class UploadController {
	
	
	@Autowired
	public FileUploadService fileUploadService;
	
	@RequestMapping("/showupload")
	public String fileUploadPage() {
		return "Fileupload";
	}
	
	@RequestMapping("/")
	public String showIndex() {
		return "Index";
	}
	
	@PostMapping("/upload")
	public String readFile(HttpServletRequest request , @RequestParam("file")  MultipartFile file , Model model) throws IOException {
		/*
		 * String fileName = null ; if(file!=null) { fileName =
		 * StringUtils.cleanPath(file.getOriginalFilename()); }else {
		 * model.addAttribute("message", "Invalid File"); return "ErrorPage"; }
		 */
		InputStream in=file.getInputStream();
		
		
		/*
		 * if (! "text/csv".equals(file.getContentType())) {
		 * model.addAttribute("message", "Please upload file in text/csv format");
		 * return "ErrorPage"; }
		 */
		 
		List<Employee> savedEmployees =  fileUploadService.readAndSaveEmpDetails(in);
		
		if(savedEmployees!=null && savedEmployees.size()>0) {
		model.addAttribute("employeeList", savedEmployees);
		return "Uploadsuccess";
		}
		else {
			model.addAttribute("message", "Error occured while saving data");
			return "ErrorPage";
		}
		/*
		 * URI location =
		 * ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(fileName).
		 * toUri(); return ResponseEntity.created(location).build();
		 */
	}
	
	@RequestMapping("/search")
	public String showSearch(Model model) {
		List<Employee> employeeList =  fileUploadService.getAllUsers();
		SearchEmployees searchEmployees = new SearchEmployees();
		searchEmployees.setOffset(0);
		searchEmployees.setPage(1);
		model.addAttribute(searchEmployees); 
		model.addAttribute("employeeList", employeeList);
		return "SearchDetails";
	}
	
	@PostMapping("/searchAndSort")
	public String searchAndSort(@ModelAttribute("student") SearchEmployees searchEmployees, Model model) {
		Sort  sort ;
		String sortingCol = searchEmployees.getSortOrder();
		String sortingProp = searchEmployees.getSortingElement();
		System.out.println("sortingCol"+sortingCol);
		System.out.println("sortingProp"+sortingProp);
		System.out.println("Page"+searchEmployees.getPage());
		System.out.println("offset"+searchEmployees.getOffset());
		model.addAttribute(new SearchEmployees());
		if (sortingCol == "Ascending") {
			sort = Sort.by(sortingProp).ascending();
		} else {
			sort = Sort.by(sortingProp).descending();
		}
		Pageable pageable = PageRequest.of(searchEmployees.getPage(), 3, sort);
		List<Employee> employeeList =  fileUploadService.findByCriteria(searchEmployees.getMinSalary(), searchEmployees.getMaxSalary(), pageable);
		 model.addAttribute("employeeList",employeeList);
		 //searchEmployees.setPage(searchEmployees.getPage()+1);
		 //searchEmployees.setPage(employeeList.size());
		 model.addAttribute(searchEmployees);
		return "SearchDetails";
	}
	
	@RequestMapping("/updateEmployee/{id}")
	public String searchAndSort(@PathVariable("id") String employeeId, Model model) {
		System.out.println("employeeId"+employeeId);
		Optional<Employee> emp = fileUploadService.findById(employeeId);
		if(emp.isPresent()) {
			model.addAttribute("employee",emp);	
			return "ShowEmployee";
		}else {
			model.addAttribute("message", "Employee"+employeeId+"does not exists");
			return "ErrorPage";
		}
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, Model model) {
		fileUploadService.saveEmployee(employee);
		List<Employee> employeeList =  fileUploadService.getAllUsers();
		SearchEmployees searchEmployees = new SearchEmployees();
		searchEmployees.setOffset(0);
		searchEmployees.setPage(1);
		model.addAttribute(searchEmployees); 
		model.addAttribute("employeeList", employeeList);
		return "SearchDetails";
	}
	
	@RequestMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable("id") String employeeId, Model model) {
		System.out.println("employeeId"+employeeId);
		Optional<Employee> emp = fileUploadService.findById(employeeId);
		if(emp.isPresent()) {
			fileUploadService.deleteById(employeeId);
			List<Employee> employeeList =  fileUploadService.getAllUsers();
			SearchEmployees searchEmployees = new SearchEmployees();
			searchEmployees.setOffset(0);
			searchEmployees.setPage(1);
			model.addAttribute(searchEmployees); 
			model.addAttribute("employeeList", employeeList);
			return "SearchDetails";
		}else {
			model.addAttribute("message", "Employee"+employeeId+"does not exists");
			return "ErrorPage";
		}
	}
}
