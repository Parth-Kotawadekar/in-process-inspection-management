package com.api_lcms.dto;

import java.sql.Date;

public class DatasheetDTO {

	private Integer datasheet_id;
	private Date calibration_date;
	private Date next_calibration_date;
	private String calibration_frequency;
	private String frequency_type;
	private Date date_of_reciept;
	private String ref_dc_no;
	private Date issue_date;
	private String identification_marked_by;
	private String certificate_no;
	private String ulr_no;
	private String calibration_place;
	private String doc_no;
	private String rev_no;
	private String name_of_customer;
	private Integer gauge_id;
	private Integer emp_id;
	private Integer cust_id;
	private String email;

	public Integer getDatasheet_Id() {
		return datasheet_id;
	}
	public void setDatasheet_Id(Integer datasheet_id) {
		this.datasheet_id = datasheet_id;
	}
	public Date getCalibration_date() {
		return calibration_date;
	}
	public void setCalibration_date(Date calibration_date) {
		this.calibration_date = calibration_date;
	}
	public Date getNext_calibration_date() {
		return next_calibration_date;
	}
	public void setNext_calibration_date(Date next_calibration_date) {
		this.next_calibration_date = next_calibration_date;
	}
	public String getCalibration_frequency() {
		return calibration_frequency;
	}
	public void setCalibration_frequency(String calibration_frequency) {
		this.calibration_frequency = calibration_frequency;
	}
	public String getFrequency_type() {
		return frequency_type;
	}
	public void setFrequency_type(String frequency_type) {
		this.frequency_type = frequency_type;
	}
	public Date getDate_of_reciept() {
		return date_of_reciept;
	}
	public void setDate_of_reciept(Date date_of_reciept) {
		this.date_of_reciept = date_of_reciept;
	}
	public String getRef_dc_no() {
		return ref_dc_no;
	}
	public void setRef_dc_no(String ref_dc_no) {
		this.ref_dc_no = ref_dc_no;
	}
	public Date getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}
	public String getIdentification_marked_by() {
		return identification_marked_by;
	}
	public void setIdentification_marked_by(String identification_marked_by) {
		this.identification_marked_by = identification_marked_by;
	}
	public String getCertificate_no() {
		return certificate_no;
	}
	public void setCertificate_no(String certificate_no) {
		this.certificate_no = certificate_no;
	}
	public String getCalibration_place() {
		return calibration_place;
	}
	public void setCalibration_place(String calibration_place) {
		this.calibration_place = calibration_place;
	}
	public String getDoc_no() {
		return doc_no;
	}
	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}
	public String getRev_no() {
		return rev_no;
	}
	public void setRev_no(String rev_no) {
		this.rev_no = rev_no;
	}
	public String getName_of_customer() {
		return name_of_customer;
	}
	public void setName_of_customer(String name_of_customer) {
		this.name_of_customer = name_of_customer;
	}
	public Integer getGauge_id() {
		return gauge_id;
	}
	public void setGauge_id(Integer gauge_id) {
		this.gauge_id = gauge_id;
	}
	public String getUlr_no() {
		return ulr_no;
	}
	public void setUlr_no(String ulr_no) {
		this.ulr_no = ulr_no;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public Integer getDatasheet_id() {
		return datasheet_id;
	}
	public void setDatasheet_id(Integer datasheet_id) {
		this.datasheet_id = datasheet_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	@Override
	public String toString() {
		return "DatasheetDTO [id=" + datasheet_id + ", calibration_date=" + calibration_date + ", next_calibration_date="
				+ next_calibration_date + ", calibration_frequency=" + calibration_frequency + ", frequency_type="
				+ frequency_type + ", date_of_reciept=" + date_of_reciept + ", ref_dc_no=" + ref_dc_no + ", issue_date="
				+ issue_date + ", identification_marked_by=" + identification_marked_by + ", certificate_no="
				+ certificate_no + ", calibration_place=" + calibration_place + ", gauge_id=" + gauge_id  + "]";
	}
}
