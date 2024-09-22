package com.api_lcms.controller;

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

import com.api_lcms.dto.CustomerDTO;
import com.api_lcms.model.Customer;
import com.api_lcms.service.CustomerService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

	@Autowired
	private CustomerService custSer;

	@GetMapping("/showAllCustomer")
	public List<Customer> showAll(){
		return custSer.allCustomers();
	}

	@GetMapping("/getcust/{cust_id}")
	public Customer getEmpById(@PathVariable Integer cust_id) {
		return custSer.getCustomerById(cust_id);
	}

	@PostMapping("/addcustomer")
	public Customer addCustomer(@RequestBody CustomerDTO customerdto) {
		return custSer.createCustomer(customerdto);
	}

	@PutMapping("/editCust/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Integer id) {
		return custSer.updateCustomer(customer, id);
	}

	@DeleteMapping("/deletecust/{id}")
	public void deleteCustomer(@PathVariable Integer id) {
		custSer.deleteCustomer(id);
	}
}
