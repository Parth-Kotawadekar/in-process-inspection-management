package com.api_lcms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_lcms.dto.CustomerDTO;
import com.api_lcms.model.Customer;
import com.api_lcms.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository custRepo;

	public List<Customer> allCustomers(){
		return custRepo.findAll();
	}

	public Customer getCustomerById(Integer id) {
		return custRepo.findById(id).orElse(null);
	}

	public Customer createCustomer(CustomerDTO customerdto) {
		
		Customer cust = new Customer();
		
		cust.setCustomer_name(customerdto.getCustomer_name());
		cust.setAdderss(customerdto.getAdderss());
		cust.setCountry(customerdto.getCountry());
		cust.setState(customerdto.getState());
		cust.setCity(customerdto.getCity());
		cust.setOwner_name(customerdto.getOwner_name());
		cust.setOwner_email(customerdto.getOwner_email());
		cust.setContact_no(customerdto.getContact_no());
		cust.setPhone_no(customerdto.getPhone_no());
		cust.setPincode(customerdto.getPincode());
		cust.setLogo(customerdto.getLogo());
		
		return custRepo.save(cust);
	}

	public Customer updateCustomer(Customer customer, Integer id) {
		Customer existingCustomer = custRepo.findById(id).orElse(null);

		if(existingCustomer != null) {
			existingCustomer.setCustomer_name(customer.getCustomer_name());
			existingCustomer.setAdderss(customer.getAdderss());
			existingCustomer.setCountry(customer.getCountry());
			existingCustomer.setState(customer.getState());
			existingCustomer.setCity(customer.getCity());
			existingCustomer.setContact_no(customer.getContact_no());
			existingCustomer.setOwner_email(customer.getOwner_email());
			existingCustomer.setOwner_name(customer.getOwner_name());
			existingCustomer.setPhone_no(customer.getPhone_no());
			existingCustomer.setPincode(customer.getPincode());
			existingCustomer.setLogo(customer.getLogo());

			return custRepo.save(existingCustomer);
		}

		return null;
	}
	public void deleteCustomer(Integer id) {
		custRepo.deleteById(id);
	}

}
