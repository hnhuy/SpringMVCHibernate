package com.kolido.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kolido.dao.EmployeeDao;
import com.kolido.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return employeeDao.findById(id);
	}

	@Override
	public Employee findBySsn(String ssn) {
		// TODO Auto-generated method stub
		return employeeDao.findBySsn(ssn);
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeDao.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.saveEmployee(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Employee entity = employeeDao.findById(employee.getId());

		if (entity != null) {
			entity.setName(employee.getName());
			entity.setJoiningDate(employee.getJoiningDate());
			entity.setSalary(employee.getSalary());
			entity.setSsn(employee.getSsn());
		}
	}

	@Override
	public void deleteEmployeeBySsn(String ssn) {
		// TODO Auto-generated method stub
		employeeDao.deleteEmployeeBySsn(ssn);
	}

	@SuppressWarnings("null")
	@Override
	public boolean isEmployeeSsnUnique(int id, String ssn) {
		// TODO Auto-generated method stub
		Employee employee = employeeDao.findBySsn(ssn);
		return (employee != null) || ((employee.getId() == id));
	}

}
