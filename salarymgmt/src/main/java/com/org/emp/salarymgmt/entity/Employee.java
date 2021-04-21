package com.org.emp.salarymgmt.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Employee {
	@Id
	private String id;
	
	private String login;
	
	private String name;
	
	private Double Salary;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return Salary;
	}

	public void setSalary(Double salary) {
		Salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", login=" + login + ", name=" + name + ", Salary=" + Salary + "]";
	}
	
	public Employee() {
		
	}
	public Employee(String id, String login, String name, Double salary) {
		this.id = id;
		this.login = login;
		this.name = name;
		Salary = salary;
	}
	
	

}
