package com.api_lcms.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="combined_master_1_tb")
public class CombinedMaster_1 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rep_id;
	private String rep_sr_no;

//	Condition Gauge Receipt
	private String condition_description;

//	Test Purpose
	private String test_purpose;

//	Calibration method number
	private String method_number;
	private String method_description;

//	Uncertainty Measurement
	private String gauge_type;
	private String size;

//	Master equipment used
	private String equipment_description;
	private String equipment_sr_no;
	private Date calibration_date;
	private Date validity_date;

	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "cust_id")
	private Customer customer;

	public Integer getRep_id() {
		return rep_id;
	}
	public void set_id(Integer rep_id) {
		this.rep_id = rep_id;
	}
	public String getCondition_description() {
		return condition_description;
	}
	public void setCondition_description(String condition_description) {
		this.condition_description = condition_description;
	}
	public String getTest_purpose() {
		return test_purpose;
	}
	public void setTest_purpose(String test_purpose) {
		this.test_purpose = test_purpose;
	}
	public String getMethod_number() {
		return method_number;
	}
	public void setMethod_number(String method_number) {
		this.method_number = method_number;
	}
	public String getMethod_description() {
		return method_description;
	}
	public void setMethod_description(String method_description) {
		this.method_description = method_description;
	}
	public String getGauge_type() {
		return gauge_type;
	}
	public void setGauge_type(String gauge_type) {
		this.gauge_type = gauge_type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getEquipment_description() {
		return equipment_description;
	}
	public void setEquipment_description(String equipment_description) {
		this.equipment_description = equipment_description;
	}
	public String getEquipment_sr_no() {
		return equipment_sr_no;
	}
	public void setEquipment_sr_no(String equipment_sr_no) {
		this.equipment_sr_no = equipment_sr_no;
	}
	public Date getCalibration_date() {
		return calibration_date;
	}
	public void setCalibration_date(Date calibration_date) {
		this.calibration_date = calibration_date;
	}
	public Date getValidity_date() {
		return validity_date;
	}
	public void setValidity_date(Date validity_date) {
		this.validity_date = validity_date;
	}
	public String getRep_sr_no() {
		return rep_sr_no;
	}
	public void setRep_sr_no(String rep_sr_no) {
		this.rep_sr_no = rep_sr_no;
	}
}
