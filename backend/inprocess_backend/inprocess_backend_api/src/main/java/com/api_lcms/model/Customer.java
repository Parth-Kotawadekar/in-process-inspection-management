package com.api_lcms.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_tb")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cust_id;
	private String customer_name;
	private String adderss;
	private String Country;
	private String state;
	private String city;
	private String owner_name;
	private String owner_email;
	private String contact_no;
	private String phone_no;
	private String pincode;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] logo;

	@ManyToOne
	@JoinColumn(name = "gauge_id")
	private GaugeMaster gaugemaster;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;


	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Employee> employees = new ArrayList<>();

//	@ManyToMany(mappedBy = "customers")
//	private Set<GaugeMaster> gauge;

//	@OneToMany(mappedBy = "customer")
//	private List<Employee> employee_id = new ArrayList<>();

	public Integer getCust_id() {
		return cust_id;
	}
	public void setId(Integer cust_id) {
		this.cust_id = cust_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getAdderss() {
		return adderss;
	}
	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getOwner_email() {
		return owner_email;
	}
	public void setOwner_email(String owner_email) {
		this.owner_email = owner_email;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}


}
