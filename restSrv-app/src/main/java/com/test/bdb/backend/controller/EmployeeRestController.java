package com.test.bdb.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.bdb.backend.model.entity.Employee;
import com.test.bdb.backend.model.services.IEmployeeService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	@Autowired
	private IEmployeeService employeeService;
	
	@GetMapping("/employees")
	public List<Employee> index() {
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Employee employee = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			employee = employeeService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la operación");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		} 
		
		if (employee == null) {
			response.put("mensaje", "Ocurrió un error al realizar la operación.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Employee> (employee, HttpStatus.OK);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<?> create(@RequestBody Employee Employee) {
		Employee employeeNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			employeeNew = employeeService.save(Employee);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la operación");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Empleado ha sido creado con éxito!");
		response.put("employee", employeeNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/employees/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable Long id) {
		Employee employeeActual = employeeService.findById(id);
		Employee employeeUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if (employeeActual == null) {
			response.put("mensaje", "Error al realizar la operación");
			response.put("error", "No existe el empleado actual.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			employeeActual.setFullname(employee.getFullname());
			employeeActual.setFunction(employee.getFunction());
			
			employeeUpdated = employeeService.save(employeeActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la operación");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El empleado ha sido actualizada con éxito!");
		response.put("employee", employeeUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			employeeService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la operación");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Empleado ha sido eliminado correctamente!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
