package com.test.bdb.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.test.bdb.backend.model.entity.Employee;

public interface IEmployeeDao extends CrudRepository <Employee, Long>{

}
