package com.kolido.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.kolido.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements EmployeeDao{

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public Employee findBySsn(String ssn) {
		// TODO Auto-generated method stub
		return (Employee) createEntityCriteria().add(Restrictions.eq("ssn", ssn)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return (List<Employee>) createEntityCriteria().list();
	}

	@Override
	public void saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		persist(employee);
	}

	@Override
	public void deleteEmployeeBySsn(String ssn) {
		// TODO Auto-generated method stub
		getCurrentSession().createQuery("delete from Employee where ssn = :ssn").setString("ssn", ssn).executeUpdate();
	}

}
