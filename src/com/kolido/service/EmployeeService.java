package com.kolido.service;

import java.util.List;

import com.kolido.model.Employee;

public interface EmployeeService {
	Employee findById(int id);
	Employee findBySsn(String ssn);
	List<Employee> findAll();
	void saveEmployee(Employee employee);
	void updateEmployee(Employee employee);
	void deleteEmployeeBySsn(String ssn);
	boolean isEmployeeSsnUnique(int id, String ssn);
}
