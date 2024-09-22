package com.api_lcms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_lcms.dto.EmployeeDTO;
import com.api_lcms.model.Employee;
import com.api_lcms.service.EmployeeService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

	@Autowired
	private EmployeeService empSer;

	@GetMapping("/all")
	public List<Employee> showAllEmployee(){
		return empSer.getAllEmployee();
	}

	@GetMapping("/emp/{id}")
	public Employee showById(@PathVariable Integer id) {
		return empSer.getEmpById(id);
	}

	@PostMapping("/addNew")
	public Employee addNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return empSer.createEmployee(employeeDTO);
	}

	@PutMapping("/editEmployee/{id}")
	public Employee editEmployee(@PathVariable Integer id, @RequestBody EmployeeDTO employee) {
		return empSer.updateEmployee(employee, id);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable Integer id) {
		empSer.deleteEmployee(id);
	}

	//API to download report
	@GetMapping("/allemployeereport")
	public void downloadReport(HttpServletResponse response) {
		try {
			empSer.exportJapserReport(response);
		} catch (JRException | IOException e) {
			e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}
}
