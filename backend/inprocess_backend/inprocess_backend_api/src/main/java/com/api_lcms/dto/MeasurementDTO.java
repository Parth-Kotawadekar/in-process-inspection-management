package com.api_lcms.dto;

public class MeasurementDTO {

	private Integer id;
	private String nominal_size;
	private String r1;
	private String r2;
	private String r3;
	private String mean_reading;
	private String observed_reading;
	private String error_reading;
	private Integer datasheet_id;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNominal_size() {
		return nominal_size;
	}
	public void setNominal_size(String nominal_size) {
		this.nominal_size = nominal_size;
	}
	public String getR1() {
		return r1;
	}
	public void setR1(String r1) {
		this.r1 = r1;
	}
	public String getR2() {
		return r2;
	}
	public void setR2(String r2) {
		this.r2 = r2;
	}
	public String getR3() {
		return r3;
	}
	public void setR3(String r3) {
		this.r3 = r3;
	}
	public String getMean_reading() {
		return mean_reading;
	}
	public void setMean_reading(String mean_reading) {
		this.mean_reading = mean_reading;
	}
	public String getObserved_reading() {
		return observed_reading;
	}
	public void setObserved_reading(String observed_reading) {
		this.observed_reading = observed_reading;
	}
	public String getError_reading() {
		return error_reading;
	}
	public void setError_reading(String error_reading) {
		this.error_reading = error_reading;
	}
	public Integer getDatasheet_id() {
		return datasheet_id;
	}
	public void setDatasheet_id(Integer datasheet_id) {
		this.datasheet_id = datasheet_id;
	}



}
