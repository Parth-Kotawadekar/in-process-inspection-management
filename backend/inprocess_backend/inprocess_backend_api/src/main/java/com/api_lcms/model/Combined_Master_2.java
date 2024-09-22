package com.api_lcms.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "combined_master_2_tb")
public class Combined_Master_2 {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer check_id;
//	Size Master
	private String type_sr_no;
	private String gauge_type;
	private String size_range;
	private String point_of_measurement;
	private String product_char;
	private String special_char;
	private String gauge_inst;
	private String check_ddm;

//	Sub type Master
	private String least_count;

	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;

	public Integer getCheck_id() {
		return check_id;
	}

	public void setCheck_id(Integer check_id) {
		this.check_id = check_id;
	}

	public String getType_sr_no() {
		return type_sr_no;
	}

	public void setType_sr_no(String type_sr_no) {
		this.type_sr_no = type_sr_no;
	}

	public String getGauge_type() {
		return gauge_type;
	}

	public void setGauge_type(String gauge_type) {
		this.gauge_type = gauge_type;
	}

	public String getSize_range() {
		return size_range;
	}

	public void setSize_range(String size_range) {
		this.size_range = size_range;
	}

	public String getPoint_of_measurement() {
		return point_of_measurement;
	}

	public void setPoint_of_measurement(String point_of_measurement) {
		this.point_of_measurement = point_of_measurement;
	}

	public String getLeast_count() {
		return least_count;
	}

	public void setLeast_count(String least_count) {
		this.least_count = least_count;
	}

	public String getProduct_char() {
		return product_char;
	}

	public void setProduct_char(String product_char) {
		this.product_char = product_char;
	}

	public String getSpecial_char() {
		return special_char;
	}

	public void setSpecial_char(String special_char) {
		this.special_char = special_char;
	}

	public String getGauge_inst() {
		return gauge_inst;
	}

	public void setGauge_inst(String gauge_inst) {
		this.gauge_inst = gauge_inst;
	}
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCheck_ddm() {
		return check_ddm;
	}

	public void setCheck_ddm(String check_ddm) {
		this.check_ddm = check_ddm;
	}

}
