package com.api_lcms.dto;

public class GaugeMasterDTO {

	private Integer gauge_id;
	private String gauge_name;
	private String gauge_sr_no;
	private String gauge_type;
	private String manufacture_id;
	private String size;
	private String least_count;
	private String gauge_range;
	private String make;
	private String go_were_limit;
	private String instrument_type;
	private String lower_size;
	private String higher_size;
	private String tolerance_type;
	private String go_tolerance_plus;
	private String go_tolerance_minus;
	private String nogo_tolerance_pus;
	private String nogo_tolerance_minus;
	private String cust_acc_crit;
	private byte[] file;
	private Integer rep_id;
	private Integer check_id;
	private Integer emp_id;
	private Integer cust_id;
	private Integer user_id;


	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public Integer getCust_id() {
		return cust_id;
	}
	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}
	public Integer getGauge_id() {
		return gauge_id;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getLeast_count() {
		return least_count;
	}
	public void setLeast_count(String least_count) {
		this.least_count = least_count;
	}
	public String getGauge_range() {
		return gauge_range;
	}
	public void setGauge_range(String gauge_range) {
		this.gauge_range = gauge_range;
	}
	public void setGauge_id(Integer gauge_id) {
		this.gauge_id = gauge_id;
	}
	public String getGauge_name() {
		return gauge_name;
	}
	public void setGauge_name(String gauge_name) {
		this.gauge_name = gauge_name;
	}
	public String getGauge_sr_no() {
		return gauge_sr_no;
	}
	public void setGauge_sr_no(String gauge_sr_no) {
		this.gauge_sr_no = gauge_sr_no;
	}
	public String getGauge_type() {
		return gauge_type;
	}
	public void setGauge_type(String gauge_type) {
		this.gauge_type = gauge_type;
	}
	public String getManufacture_id() {
		return manufacture_id;
	}
	public void setManufacture_id(String manufacture_id) {
		this.manufacture_id = manufacture_id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getGo_were_limit() {
		return go_were_limit;
	}
	public void setGo_were_limit(String go_were_limit) {
		this.go_were_limit = go_were_limit;
	}
	public String getInstrument_type() {
		return instrument_type;
	}
	public void setInstrument_type(String instrument_type) {
		this.instrument_type = instrument_type;
	}
	public String getLower_size() {
		return lower_size;
	}
	public void setLower_size(String lower_size) {
		this.lower_size = lower_size;
	}
	public String getHigher_size() {
		return higher_size;
	}
	public void setHigher_size(String higher_size) {
		this.higher_size = higher_size;
	}
	public String getTolerance_type() {
		return tolerance_type;
	}
	public void setTolerance_type(String tolerance_type) {
		this.tolerance_type = tolerance_type;
	}
	public String getGo_tolerance_plus() {
		return go_tolerance_plus;
	}
	public void setGo_tolerance_plus(String go_tolerance_plus) {
		this.go_tolerance_plus = go_tolerance_plus;
	}
	public String getGo_tolerance_minus() {
		return go_tolerance_minus;
	}
	public void setGo_tolerance_minus(String go_tolerance_minus) {
		this.go_tolerance_minus = go_tolerance_minus;
	}
	public String getNogo_tolerance_pus() {
		return nogo_tolerance_pus;
	}
	public void setNogo_tolerance_pus(String nogo_tolerance_pus) {
		this.nogo_tolerance_pus = nogo_tolerance_pus;
	}
	public String getNogo_tolerance_minus() {
		return nogo_tolerance_minus;
	}
	public void setNogo_tolerance_minus(String nogo_tolerance_minus) {
		this.nogo_tolerance_minus = nogo_tolerance_minus;
	}
	public Integer getRep_id() {
		return rep_id;
	}
	public void setRep_id(Integer rep_id) {
		this.rep_id = rep_id;
	}
	public Integer getCheck_id() {
		return check_id;
	}
	public void setCheck_id(Integer check_id) {
		this.check_id = check_id;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getCust_acc_crit() {
		return cust_acc_crit;
	}

	public void setCust_acc_crit(String cust_acc_crit) {
		this.cust_acc_crit = cust_acc_crit;
	}

	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public GaugeMasterDTO(Integer gauge_id, String gauge_name, String gauge_sr_no, String gauge_type,
			String manufacture_id, String make, String go_were_limit, String instrument_type, String lower_size,
			String higher_size, String tolerance_type, String go_tolerance_plus, String go_tolerance_minus,
			String nogo_tolerance_pus, String nogo_tolerance_minus, Integer rep_id) {
		super();
		this.gauge_id = gauge_id;
		this.gauge_name = gauge_name;
		this.gauge_sr_no = gauge_sr_no;
		this.gauge_type = gauge_type;
		this.manufacture_id = manufacture_id;
		this.make = make;
		this.go_were_limit = go_were_limit;
		this.instrument_type = instrument_type;
		this.lower_size = lower_size;
		this.higher_size = higher_size;
		this.tolerance_type = tolerance_type;
		this.go_tolerance_plus = go_tolerance_plus;
		this.go_tolerance_minus = go_tolerance_minus;
		this.nogo_tolerance_pus = nogo_tolerance_pus;
		this.nogo_tolerance_minus = nogo_tolerance_minus;
		this.rep_id = rep_id;
	}
}
