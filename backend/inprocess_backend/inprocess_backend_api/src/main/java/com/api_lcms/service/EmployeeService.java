package com.api_lcms.service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.api_lcms.dto.EmployeeDTO;
import com.api_lcms.model.Employee;
import com.api_lcms.repository.CustomerRepository;
import com.api_lcms.repository.EmployeeRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository emprepo;

	@Autowired
	private CustomerRepository customerRepository;

	public List<Employee> getAllEmployee(){
		return emprepo.findAll();
	}

	public Employee getEmpById(Integer id) {
		return emprepo.findById(id).orElseThrow(() ->new RuntimeException("Employee not found"));
	}

	public Employee createEmployee(EmployeeDTO employeeDTO) {

		Employee employee = new Employee();

		employee.setFirst_name(employeeDTO.getFirst_name());
	    employee.setMiddle_name(employeeDTO.getMiddle_name());
	    employee.setLast_name(employeeDTO.getLast_name());
	    employee.setAddress(employeeDTO.getAddress());
	    employee.setCountry(employeeDTO.getCountry());
	    employee.setState(employeeDTO.getState());
	    employee.setCity(employeeDTO.getCity());
	    employee.setPincode(employeeDTO.getPincode());
	    employee.setEmail(employeeDTO.getEmail());
	    employee.setContact(employeeDTO.getContact());
	    employee.setDesignation(employeeDTO.getDesignation());
	    employee.setDepartment(employeeDTO.getDepartment());
	    employee.setFile_name(employeeDTO.getFile_name());
	    employee.setRole(employeeDTO.getRole());
	    employee.setSignature(employeeDTO.getSignature());

	    return emprepo.save(employee);

	}

	public Employee updateEmployee(EmployeeDTO employee, Integer id) {

		try {
			Employee existingEmployee = emprepo.findById(id).orElse(null);

//			Customer customer = customerRepository.findById(employee.getCust_id()).orElse(null);

			if(existingEmployee != null) {
				existingEmployee.setFirst_name(employee.getFirst_name());
				existingEmployee.setMiddle_name(employee.getMiddle_name());
				existingEmployee.setLast_name(employee.getLast_name());
				existingEmployee.setAddress(employee.getAddress());
				existingEmployee.setCountry(employee.getCountry());
				existingEmployee.setState(employee.getState());
				existingEmployee.setCity(employee.getCity());
				existingEmployee.setEmail(employee.getEmail());
				existingEmployee.setPincode(employee.getPincode());
				existingEmployee.setContact(employee.getContact());
				existingEmployee.setDesignation(employee.getDesignation());
				existingEmployee.setDepartment(employee.getDepartment());
				existingEmployee.setSignature(employee.getSignature());
//				existingEmployee.setCustomer(customer);
//				existingEmployee.setSignature(employee.getSignature());
				return emprepo.save(existingEmployee);
			}
		} catch (Exception e) {
			System.out.println("Cannot edit employee" + e);
		}

		return null;
	}


	public void deleteEmployee(Integer id) {
		emprepo.deleteById(id);
	}

	//To export JASPER REPORT
	public void exportJapserReport(HttpServletResponse response) throws JRException, IOException {

		List<Employee> employees = emprepo.findAll();

		//Get File and compile it
		File file = ResourceUtils.getFile("classpath:Blank_A4.jrxml");
		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());

		// Data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

		Map<String, Object> parametersMap = new HashMap<>();

		JasperPrint printReport = JasperFillManager.fillReport(jasper, parametersMap, dataSource);

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=Employee_Details.pdf");

		JasperExportManager.exportReportToPdfStream(printReport, response.getOutputStream());
	}

}
