package com.kolido.dao;

import java.util.List;

import com.kolido.model.Employee;

public interface EmployeeDao {
	Employee findById(int id);
	Employee findBySsn(String ssn);
	List<Employee> findAll();
	void saveEmployee(Employee employee);
	void deleteEmployeeBySsn(String ssn);
}
