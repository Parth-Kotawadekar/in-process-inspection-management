package com.api_lcms.dto;

import java.sql.Date;

public class CertificateDTO {

    private Integer cert_id;
	private String r1;
	private String r2;
	private String r3;
	private String r4;
	private String nominal_size;
	private String observed_size;
	private String error_size;
	private String mean;
	private String product_char;
	private String special_char;
	private String gauge_inst;
	private String nine_thirty;
	private String ten_thirty;
	private String twelve_thirty;
	private String two_thirty;
	private String four_thirty;
	private String six_thirty;
	private String result;
	private String prod_plan_qty;
	private String act_prod_qty;
	private String rework_qty;
	private String reject_qty;
    private Integer datasheet_id;
    private Integer gauge_id;
//    private Integer cust_id;
    private Integer emp_id;
    private String approved;
    private String approvalPassword;
    private String operation_no;
	private String operation_desc;
	private String machine_no;
	private String rejected_reason;
	private String if_rej;
	private Date tod_date;
	private String check_ddm;
	
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

	public String getR4() {
		return r4;
	}

	public void setR4(String r4) {
		this.r4 = r4;
	}

	public String getNominal_size() {
		return nominal_size;
	}

	public void setNominal_size(String nominal_size) {
		this.nominal_size = nominal_size;
	}

	public String getObserved_size() {
		return observed_size;
	}

	public void setObserved_size(String observed_size) {
		this.observed_size = observed_size;
	}

	public String getError_size() {
		return error_size;
	}

	public void setError_size(String error_size) {
		this.error_size = error_size;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
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

	public Integer getGauge_id() {
		return gauge_id;
	}

	public void setGauge_id(Integer gauge_id) {
		this.gauge_id = gauge_id;
	}

	public Integer getDatasheet_id() {
        return datasheet_id;
    }

    public void setDatasheet_id(Integer datasheet_id) {
        this.datasheet_id = datasheet_id;
    }

    public Integer getCert_id() {
		return cert_id;
	}

	public void setCert_id(Integer cert_id) {
		this.cert_id = cert_id;
	}
//
//	public Integer getCust_id() {
//		return cust_id;
//	}
//
//	public void setCust_id(Integer cust_id) {
//		this.cust_id = cust_id;
//	}
//
//	public Integer getEmp_id() {
//		return emp_id;
//	}
//
//	public void setEmp_id(Integer emp_id) {
//		this.emp_id = emp_id;
//	}

	public String getNine_thirty() {
		return nine_thirty;
	}

	public void setNine_thirty(String nine_thirty) {
		this.nine_thirty = nine_thirty;
	}

	public String getTen_thirty() {
		return ten_thirty;
	}

	public void setTen_thirty(String ten_thirty) {
		this.ten_thirty = ten_thirty;
	}

	public String getTwelve_thirty() {
		return twelve_thirty;
	}

	public void setTwelve_thirty(String twelve_thirty) {
		this.twelve_thirty = twelve_thirty;
	}

	public String getTwo_thirty() {
		return two_thirty;
	}

	public void setTwo_thirty(String two_thirty) {
		this.two_thirty = two_thirty;
	}

	public String getFour_thirty() {
		return four_thirty;
	}

	public void setFour_thirty(String four_thirty) {
		this.four_thirty = four_thirty;
	}

	public String getSix_thirty() {
		return six_thirty;
	}

	public void setSix_thirty(String six_thirty) {
		this.six_thirty = six_thirty;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getProd_plan_qty() {
		return prod_plan_qty;
	}

	public void setProd_plan_qty(String prod_plan_qty) {
		this.prod_plan_qty = prod_plan_qty;
	}

	public String getAct_prod_qty() {
		return act_prod_qty;
	}

	public void setAct_prod_qty(String act_prod_qty) {
		this.act_prod_qty = act_prod_qty;
	}

	public String getRework_qty() {
		return rework_qty;
	}

	public void setRework_qty(String rework_qty) {
		this.rework_qty = rework_qty;
	}

	public String getReject_qty() {
		return reject_qty;
	}

	public void setReject_qty(String reject_qty) {
		this.reject_qty = reject_qty;
	}
	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getApprovalPassword() {
		return approvalPassword;
	}

	public void setApprovalPassword(String approvalPassword) {
		this.approvalPassword = approvalPassword;
	}
	public String getOperation_no() {
		return operation_no;
	}

	public void setOperation_no(String operation_no) {
		this.operation_no = operation_no;
	}

	public String getOperation_desc() {
		return operation_desc;
	}

	public void setOperation_desc(String operation_desc) {
		this.operation_desc = operation_desc;
	}

	public String getMachine_no() {
		return machine_no;
	}

	public void setMachine_no(String machine_no) {
		this.machine_no = machine_no;
	}
	public String getRejected_reason() {
		return rejected_reason;
	}

	public void setRejected_reason(String rejected_reason) {
		this.rejected_reason = rejected_reason;
	}

	public String getIf_rej() {
		return if_rej;
	}

	public void setIf_rej(String if_rej) {
		this.if_rej = if_rej;
	}
	public Date getTod_date() {
		return tod_date;
	}

	public void setTod_date(Date tod_date) {
		this.tod_date = tod_date;
	}

	public String getCheck_ddm() {
		return check_ddm;
	}

	public void setCheck_ddm(String check_ddm) {
		this.check_ddm = check_ddm;
	}
}
