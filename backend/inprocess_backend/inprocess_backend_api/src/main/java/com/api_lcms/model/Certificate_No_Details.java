package com.api_lcms.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "certificate_details_tb")
@CrossOrigin(origins = "http://localhost:3000")
public class Certificate_No_Details {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String certificate_no;
	private String suffixes;
	private String ulr_no;
	private String created_on_date;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCertificate_no() {
		return certificate_no;
	}
	public void setCertificate_no(String certificate_no) {
		this.certificate_no = certificate_no;
	}
	public String getSuffixes() {
		return suffixes;
	}
	public void setSuffixes(String suffixes) {
		this.suffixes = suffixes;
	}
	public String getUlr_no() {
		return ulr_no;
	}
	public void setUlr_no(String ulr_no) {
		this.ulr_no = ulr_no;
	}
	public String getCreated_on_date() {
		return created_on_date;
	}
	public void setCreated_on_date(String created_on_date) {
		this.created_on_date = created_on_date;
	}
}
