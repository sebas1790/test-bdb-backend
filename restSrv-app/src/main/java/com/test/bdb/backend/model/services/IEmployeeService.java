package com.test.bdb.backend.model.services;

import java.util.List;

import com.test.bdb.backend.model.entity.Employee;

public interface IEmployeeService {
	public List<Employee> findAll();

	public Employee findById(Long id);

	public Employee save(Employee employee);

	public void delete(Long id);
}
