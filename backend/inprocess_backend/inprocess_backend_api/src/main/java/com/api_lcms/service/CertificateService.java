package com.api_lcms.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.api_lcms.dto.CertificateDTO;
import com.api_lcms.model.CertificateDetails;
import com.api_lcms.model.Datasheet;
import com.api_lcms.model.Employee;
import com.api_lcms.model.GaugeMaster;
import com.api_lcms.repository.CertificateRepository;
import com.api_lcms.repository.DatasheetRepository;
import com.api_lcms.repository.EmployeeRepository;
import com.api_lcms.repository.GaugeMasterRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.repo.InputStreamResource;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private DatasheetRepository datasheetRepository;

    @Autowired
    private GaugeMasterRepository gaugeMasterRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<CertificateDetails> showAllCertificate() {
        return certificateRepository.findAll();
    }

    public CertificateDetails approveCertificate(Integer cert_id, CertificateDTO certificateDTO) {
    	
    	Employee employee = employeeRepository.findById(certificateDTO.getEmp_id()).orElse(null);
    	
        Optional<CertificateDetails> certificateOptional = certificateRepository.findById(cert_id);
        if (certificateOptional.isPresent()) {
            CertificateDetails certificate = certificateOptional.get();
            certificate.setApproved(true);
            certificate.setEmployee(employee);
            certificate.setApprovalPassword(certificateDTO.getApprovalPassword()); // Store the password (ensure encryption in a real-world scenario)
            return certificateRepository.save(certificate);
        }
        return null;
    }
    
    public CertificateDetails getCertificateById(Integer cert_id,HttpServletResponse response) throws IOException {
        try {
        	return certificateRepository.findById(cert_id).orElse(null);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error generating report: " + e.getMessage());
            System.out.println("Error generating report: " + e);
            return null;
		}
    }

    public CertificateDetails saveCertificateDetails(CertificateDTO certificateDTO, HttpServletResponse response) throws IOException {

    	System.out.println(certificateDTO);

        try {
        	GaugeMaster gaugeMaster = gaugeMasterRepository.findById(certificateDTO.getGauge_id())
            		.orElseThrow(() -> new RuntimeException("Gauge Id Not Found"));

            Datasheet datasheet = datasheetRepository.findById(certificateDTO.getDatasheet_id())
            		.orElseThrow(() -> new RuntimeException("Datasheet Id Not Found"));

            try {

            	CertificateDetails certificate = new CertificateDetails();

                certificate.setR1(certificateDTO.getR1());
                certificate.setR2(certificateDTO.getR2());
                certificate.setR3(certificateDTO.getR3());
                certificate.setMean(certificateDTO.getMean());
                certificate.setNominal_size(certificateDTO.getNominal_size());
                certificate.setObserved_size(certificateDTO.getObserved_size());
                certificate.setError_size(certificateDTO.getError_size());
                certificate.setProduct_char(certificateDTO.getProduct_char());
                certificate.setSpecial_char(certificateDTO.getSpecial_char());
                certificate.setGauge_inst(certificateDTO.getGauge_inst());
                certificate.setNine_thirty(certificateDTO.getNine_thirty());
                certificate.setTen_thirty(certificateDTO.getTen_thirty());
                certificate.setTwelve_thirty(certificateDTO.getTwelve_thirty());
                certificate.setTwo_thirty(certificateDTO.getTwo_thirty());
                certificate.setFour_thirty(certificateDTO.getFour_thirty());
                certificate.setSix_thirty(certificateDTO.getSix_thirty());
                certificate.setResult(certificateDTO.getResult());
                certificate.setProd_plan_qty(certificateDTO.getProd_plan_qty());
                certificate.setAct_prod_qty(certificateDTO.getAct_prod_qty());
                certificate.setRework_qty(certificateDTO.getRework_qty());
                certificate.setReject_qty(certificateDTO.getReject_qty());
                certificate.setOperation_no(certificateDTO.getOperation_no());
                certificate.setOperation_desc(certificateDTO.getOperation_desc());
                certificate.setMachine_no(certificateDTO.getMachine_no());
                certificate.setRejected_reason(certificateDTO.getRejected_reason());
                certificate.setIf_rej(certificateDTO.getIf_rej());
                certificate.setTod_date(certificateDTO.getTod_date());
                certificate.setCheck_ddm(certificateDTO.getCheck_ddm());
                //double mean = calculateMean(certificate.getR1(), certificate.getR2(), certificate.getR3());
                //certificate.setMean(String.valueOf(mean));
                certificate.setDatasheet(datasheet);
                certificate.setGaugeMaster(gaugeMaster);

                return certificateRepository.save(certificate);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException("Cannot save certificate" + ex.getMessage());
			}

        } catch (Exception e) {
        	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error saving certificate details: " + e.getMessage());
            System.out.println("Error saving certificate: " + e);
        }
		return null;
    }

    public CertificateDetails exportBlankReport(Integer cert_id, HttpServletResponse response) throws JRException, IOException {
        try {
            // SQL query to fetch certificate details with related entities
            String queryString = "SELECT\r\n"
            		+ "      certificate_tb.cert_id,\r\n"
            		+ "      certificate_tb.nine_thirty, \r\n"
            		+ "      certificate_tb.ten_thirty, \r\n"
            		+ "      certificate_tb.twelve_thirty, \r\n"
            		+ "      certificate_tb.two_thirty, \r\n"
            		+ "      certificate_tb.four_thirty, \r\n"
            		+ "      certificate_tb.six_thirty, \r\n"
            		+ "      certificate_tb.result,\r\n"
            		+ "      certificate_tb.prod_plan_qty, \r\n"
            		+ "      certificate_tb.act_prod_qty, \r\n"
            		+ "      certificate_tb.rework_qty, \r\n"
            		+ "      certificate_tb.reject_qty, \r\n"
            		+ "      certificate_tb.error_size, \r\n"
            		+ "      certificate_tb.mean, \r\n"
            		+ "      certificate_tb.nominal_size, \r\n"
            		+ "      certificate_tb.observed_size, \r\n"
            		+ "      certificate_tb.r1, \r\n"
            		+ "      certificate_tb.r2, \r\n"
            		+ "      certificate_tb.r3, \r\n"
            		+ "      certificate_tb.r4, \r\n"
            		+ "      certificate_tb.datasheet_id, \r\n"
            		+ "      certificate_tb.gauge_id, \r\n"
            		+ "      certificate_tb.gauge_inst, \r\n"
            		+ "      certificate_tb.product_char, \r\n"
            		+ "      certificate_tb.special_char,      \r\n"
            		+ "      certificate_tb.nominal_size,\r\n"
            		+ "      certificate_tb.observed_size,\r\n"
            		+ "      certificate_tb.error_size,\r\n"
            		+ "      gaugemaster_tb.gauge_id,\r\n"
            		+ "      gaugemaster_tb.gauge_name,\r\n"
            		+ "      gaugemaster_tb.gauge_type,\r\n"
            		+ "      gaugemaster_tb.gauge_sr_no,\r\n"
            		+ "      gaugemaster_tb.size,\r\n"
            		+ "      gaugemaster_tb.least_count,\r\n"
            		+ "      gaugemaster_tb.manufacture_id,\r\n"
            		+ "      gaugemaster_tb.make,\r\n"
            		+ "      datasheet_tb.datasheet_id,\r\n"
            		+ "      datasheet_tb.calibration_date,\r\n"
            		+ "      datasheet_tb.next_calibration_date,\r\n"
            		+ "      datasheet_tb.ref_dc_no,\r\n"
            		+ "      datasheet_tb.date_of_reciept,  -- corrected from \\\"date_of_reciept\\\"\r\n"
            		+ "      datasheet_tb.issue_date,\r\n"
            		+ "      datasheet_tb.certificate_no,\r\n"
            		+ "      datasheet_tb.calibration_place,\r\n"
            		+ "      datasheet_tb.identification_marked_by,\r\n"
            		+ "      employee_tb.emp_id,\r\n"
            		+ "      employee_tb.first_name,\r\n"
            		+ "      employee_tb.last_name,\r\n"
            		+ "      employee_tb.designation,\r\n"
            		+ "      employee_tb.signature,\r\n"
            		+ "      customer_tb.cust_id,\r\n"
            		+ "      customer_tb.customer_name,\r\n"
            		+ "      customer_tb.adderss,\r\n"
            		+ "      customer_tb.logo,\r\n"
            		+ "      combined_master_1_tb.rep_id,\r\n"
            		+ "      combined_master_1_tb.rep_sr_no,\r\n"
            		+ "      combined_master_1_tb.condition_description,\r\n"
            		+ "      combined_master_1_tb.test_purpose,\r\n"
            		+ "      combined_master_1_tb.method_number,\r\n"
            		+ "      combined_master_1_tb.method_description,\r\n"
            		+ "      combined_master_1_tb.gauge_type,\r\n"
            		+ "      combined_master_1_tb.size,\r\n"
            		+ "      combined_master_1_tb.equipment_description,\r\n"
            		+ "      combined_master_1_tb.equipment_sr_no,\r\n"
            		+ "      combined_master_1_tb.calibration_date,\r\n"
            		+ "      combined_master_1_tb.equipment_description,\r\n"
            		+ "      combined_master_1_tb.validity_date,\r\n"
            		+ "      combined_master_2_tb.check_id,\r\n"
            		+ "      combined_master_2_tb.check_ddm\r\n"
            		+ "            		FROM\r\n"
            		+ "      certificate_tb\r\n"
            		+ "            		LEFT JOIN gaugemaster_tb ON certificate_tb.gauge_id = gaugemaster_tb.gauge_id\r\n"
            		+ "            		LEFT JOIN datasheet_tb ON certificate_tb.datasheet_id = datasheet_tb.datasheet_id\r\n"
            		+ "            		LEFT JOIN employee_tb ON gaugemaster_tb.emp_id = employee_tb.emp_id\r\n"
            		+ "            		LEFT JOIN customer_tb ON gaugemaster_tb.cust_id = customer_tb.cust_id\r\n"
            		+ "            		LEFT JOIN combined_master_1_tb ON gaugemaster_tb.rep_id = combined_master_1_tb.rep_id\r\n"
            		+ "                    LEFT JOIN combined_master_2_tb ON gaugemaster_tb.check_id = combined_master_2_tb.check_id\r\n"
            		+ "            		WHERE\r\n"
            		+ "      certificate_tb.cert_id = :cert_id";

            // Execute the query and get the result as a list of maps
            Map<String, Object> params = new HashMap<>();
            params.put("cert_id", cert_id);
            List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(queryString, params);

            if (queryResults.isEmpty()) {
                throw new RuntimeException("Certificate Not Found");
            }

            // Log the query results for debugging
            System.out.println("Query Results: " + queryResults);

            // Load the Jasper report file
//            Resource resource = resourceLoader.getResource("classpath:Reports/BlankReport.jrxml");
//            File file = resource.getFile();
//
//            // Compile the Jasper report
//            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            
            String ddl_process_type = (String) queryResults.get(0).get("check_ddm");
            String reportTemplate = "/Reports/BlankReport.jrxml";
            
            if("IN PROCESS INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/in_process_blank_report.jrxml";
            	System.out.println("IN PROCESS BLANK REPORT DOWNLOADED");
            }else if("INCOMING INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/incoming_inspection_blank_report.jrxml";
            	System.out.println("INCOMING INSPECTION BLANK REPORT DOWNLOADED");
            }else if("PRE-DISPATCH INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/pre_dispatch_blank_report.jrxml";
            	System.out.println("PRE-DISPATCH INSPECTION BLANK REPORT DOWNLOADED");
            }else if("LAYOUT INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/layout_inspection_blank_report.jrxml";
            	System.out.println("LAYOUT INSPECTION REPORT BLANK DOWNLOADED");
            }
            
            InputStream reportstream = this.getClass().getResourceAsStream(reportTemplate);
            JasperReport jasperReport = JasperCompileManager.compileReport(reportstream);

            // Use the query result as a data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(queryResults);

            // Parameters map for Jasper report
            Map<String, Object> parametersMap = new HashMap<>();

            // Fill the report with data
            JasperPrint printReport = JasperFillManager.fillReport(jasperReport, parametersMap, dataSource);

            // Log the filled report for debugging
            System.out.println("JasperPrint: " + printReport);

            // Configure response to return PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=Certificate_" + cert_id + ".pdf");

            try (OutputStream out = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(printReport, out);
                out.flush();
            }catch (Exception ex) {
            	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error generating report: " + ex.getMessage());
                ex.printStackTrace();
			}
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public CertificateDetails exportWithDataReport(Integer cert_id, HttpServletResponse response) throws JRException, IOException {
        try {
            // SQL query to fetch certificate details with related entities
            String queryString = "SELECT\r\n"
            		+ "      certificate_tb.cert_id,\r\n"
            		+ "      certificate_tb.nine_thirty, \r\n"
            		+ "      certificate_tb.ten_thirty, \r\n"
            		+ "      certificate_tb.twelve_thirty, \r\n"
            		+ "      certificate_tb.two_thirty, \r\n"
            		+ "      certificate_tb.four_thirty, \r\n"
            		+ "      certificate_tb.six_thirty, \r\n"
            		+ "      certificate_tb.result,\r\n"
            		+ "      certificate_tb.prod_plan_qty, \r\n"
            		+ "      certificate_tb.act_prod_qty, \r\n"
            		+ "      certificate_tb.rework_qty, \r\n"
            		+ "      certificate_tb.reject_qty, \r\n"
            		+ "      certificate_tb.error_size, \r\n"
            		+ "      certificate_tb.mean, \r\n"
            		+ "      certificate_tb.nominal_size, \r\n"
            		+ "      certificate_tb.observed_size, \r\n"
            		+ "      certificate_tb.r1, \r\n"
            		+ "      certificate_tb.r2, \r\n"
            		+ "      certificate_tb.r3, \r\n"
            		+ "      certificate_tb.r4, \r\n"
            		+ "      certificate_tb.datasheet_id, \r\n"
            		+ "      certificate_tb.gauge_id, \r\n"
            		+ "      certificate_tb.gauge_inst, \r\n"
            		+ "      certificate_tb.product_char, \r\n"
            		+ "      certificate_tb.special_char,      \r\n"
            		+ "      certificate_tb.nominal_size,\r\n"
            		+ "      certificate_tb.observed_size,\r\n"
            		+ "      certificate_tb.error_size,\r\n"
            		+ "      gaugemaster_tb.gauge_id,\r\n"
            		+ "      gaugemaster_tb.gauge_name,\r\n"
            		+ "      gaugemaster_tb.gauge_type,\r\n"
            		+ "      gaugemaster_tb.gauge_sr_no,\r\n"
            		+ "      gaugemaster_tb.size,\r\n"
            		+ "      gaugemaster_tb.least_count,\r\n"
            		+ "      gaugemaster_tb.manufacture_id,\r\n"
            		+ "      gaugemaster_tb.make,\r\n"
            		+ "      datasheet_tb.datasheet_id,\r\n"
            		+ "      datasheet_tb.calibration_date,\r\n"
            		+ "      datasheet_tb.next_calibration_date,\r\n"
            		+ "      datasheet_tb.ref_dc_no,\r\n"
            		+ "      datasheet_tb.date_of_reciept,  -- corrected from \\\"date_of_reciept\\\"\r\n"
            		+ "      datasheet_tb.issue_date,\r\n"
            		+ "      datasheet_tb.certificate_no,\r\n"
            		+ "      datasheet_tb.calibration_place,\r\n"
            		+ "      datasheet_tb.identification_marked_by,\r\n"
            		+ "      employee_tb.emp_id,\r\n"
            		+ "      employee_tb.first_name,\r\n"
            		+ "      employee_tb.last_name,\r\n"
            		+ "      employee_tb.designation,\r\n"
            		+ "      employee_tb.signature,\r\n"
            		+ "      customer_tb.cust_id,\r\n"
            		+ "      customer_tb.customer_name,\r\n"
            		+ "      customer_tb.adderss,\r\n"
            		+ "      customer_tb.logo,\r\n"
            		+ "      combined_master_1_tb.rep_id,\r\n"
            		+ "      combined_master_1_tb.rep_sr_no,\r\n"
            		+ "      combined_master_1_tb.condition_description,\r\n"
            		+ "      combined_master_1_tb.test_purpose,\r\n"
            		+ "      combined_master_1_tb.method_number,\r\n"
            		+ "      combined_master_1_tb.method_description,\r\n"
            		+ "      combined_master_1_tb.gauge_type,\r\n"
            		+ "      combined_master_1_tb.size,\r\n"
            		+ "      combined_master_1_tb.equipment_description,\r\n"
            		+ "      combined_master_1_tb.equipment_sr_no,\r\n"
            		+ "      combined_master_1_tb.calibration_date,\r\n"
            		+ "      combined_master_1_tb.equipment_description,\r\n"
            		+ "      combined_master_1_tb.validity_date,\r\n"
            		+ "      combined_master_2_tb.check_id,\r\n"
            		+ "      combined_master_2_tb.check_ddm\r\n"
            		+ "            		FROM\r\n"
            		+ "      certificate_tb\r\n"
            		+ "            		LEFT JOIN gaugemaster_tb ON certificate_tb.gauge_id = gaugemaster_tb.gauge_id\r\n"
            		+ "            		LEFT JOIN datasheet_tb ON certificate_tb.datasheet_id = datasheet_tb.datasheet_id\r\n"
            		+ "            		LEFT JOIN employee_tb ON gaugemaster_tb.emp_id = employee_tb.emp_id\r\n"
            		+ "            		LEFT JOIN customer_tb ON gaugemaster_tb.cust_id = customer_tb.cust_id\r\n"
            		+ "            		LEFT JOIN combined_master_1_tb ON gaugemaster_tb.rep_id = combined_master_1_tb.rep_id\r\n"
            		+ "                    LEFT JOIN combined_master_2_tb ON gaugemaster_tb.check_id = combined_master_2_tb.check_id\r\n"
            		+ "            		WHERE\r\n"
            		+ "      certificate_tb.cert_id = :cert_id";

            // Execute the query and get the result as a list of maps
            Map<String, Object> params = new HashMap<>();
            params.put("cert_id", cert_id);
            List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(queryString, params);

            if (queryResults.isEmpty()) {
                throw new RuntimeException("Certificate Not Found");
            }

            // Log the query results for debugging
            System.out.println("Query Results: " + queryResults);

            // Load the Jasper report file
//            Resource resource = resourceLoader.getResource("classpath:Reports/WithDataReport.jrxml");
//            File file = resource.getFile();
//
//            // Compile the Jasper report
//            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            
            String ddl_process_type = (String) queryResults.get(0).get("check_ddm");
            String reportTemplate = "/Reports/WithDataReport.jrxml";
            
            if("IN PROCESS INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/in_process_with_data_report.jrxml";
            	System.out.println("IN PROCESS WITH DATA REPORT DOWNLOADED");
            }else if("INCOMING INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/incoming_inspection_with_data_report.jrxml";
            	System.out.println("INCOMING INSPECTION WITH DATA REPORT DOWNLOADED");
            }else if("PRE-DISPATCH INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/pre_dispatch_with_data.jrxml";
            	System.out.println("PRE-DISPATCH INSPECTION WITH DATA REPORT DOWNLOADED");
            }else if("LAYOUT INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/layout_inspection_with_data.jrxml";
            	System.out.println("LAYOUT INSPECTION WITH DATA REPORT DOWNLOADED");
            }
            
            InputStream reportstream = this.getClass().getResourceAsStream(reportTemplate);
            JasperReport jasperReport = JasperCompileManager.compileReport(reportstream);

            // Use the query result as a data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(queryResults);

            // Parameters map for Jasper report
            Map<String, Object> parametersMap = new HashMap<>();

            // Fill the report with data
            JasperPrint printReport = JasperFillManager.fillReport(jasperReport, parametersMap, dataSource);

            // Log the filled report for debugging
            System.out.println("JasperPrint: " + printReport);

            // Configure response to return PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=Certificate_" + cert_id + ".pdf");

            try (OutputStream out = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(printReport, out);
                out.flush();
            }catch (Exception ex) {
            	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error generating report: " + ex.getMessage());
                ex.printStackTrace();
			}
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public CertificateDetails exportBlankDatasheet(Integer cert_id, HttpServletResponse response) throws IOException {
    	try {

    		String query = "SELECT\r\n"
    				+ "      certificate_tb.cert_id,\r\n"
    				+ "      certificate_tb.nine_thirty, \r\n"
    				+ "      certificate_tb.ten_thirty, \r\n"
    				+ "      certificate_tb.twelve_thirty, \r\n"
    				+ "      certificate_tb.two_thirty, \r\n"
    				+ "      certificate_tb.four_thirty, \r\n"
    				+ "      certificate_tb.six_thirty, \r\n"
    				+ "      certificate_tb.result,\r\n"
    				+ "      certificate_tb.prod_plan_qty, \r\n"
    				+ "      certificate_tb.act_prod_qty, \r\n"
    				+ "      certificate_tb.rework_qty, \r\n"
    				+ "      certificate_tb.reject_qty, \r\n"
    				+ "      certificate_tb.error_size, \r\n"
    				+ "      certificate_tb.mean, \r\n"
    				+ "      certificate_tb.nominal_size, \r\n"
    				+ "      certificate_tb.observed_size, \r\n"
    				+ "      certificate_tb.r1, \r\n"
    				+ "      certificate_tb.r2, \r\n"
    				+ "      certificate_tb.r3, \r\n"
    				+ "      certificate_tb.r4, \r\n"
    				+ "      certificate_tb.datasheet_id, \r\n"
    				+ "      certificate_tb.gauge_id, \r\n"
    				+ "      certificate_tb.gauge_inst, \r\n"
    				+ "      certificate_tb.product_char, \r\n"
    				+ "      certificate_tb.special_char,      \r\n"
    				+ "      certificate_tb.nominal_size,\r\n"
    				+ "      certificate_tb.observed_size,\r\n"
    				+ "      certificate_tb.error_size,\r\n"
    				+ "      gaugemaster_tb.gauge_id,\r\n"
    				+ "      gaugemaster_tb.gauge_name,\r\n"
    				+ "      gaugemaster_tb.gauge_type,\r\n"
    				+ "      gaugemaster_tb.gauge_sr_no,\r\n"
    				+ "      gaugemaster_tb.size,\r\n"
    				+ "      gaugemaster_tb.least_count,\r\n"
    				+ "      gaugemaster_tb.manufacture_id,\r\n"
    				+ "      gaugemaster_tb.make,\r\n"
    				+ "      datasheet_tb.datasheet_id,\r\n"
    				+ "      datasheet_tb.calibration_date,\r\n"
    				+ "      datasheet_tb.next_calibration_date,\r\n"
    				+ "      datasheet_tb.ref_dc_no,\r\n"
    				+ "      datasheet_tb.date_of_reciept,  -- corrected from \\\"date_of_reciept\\\"\r\n"
    				+ "      datasheet_tb.issue_date,\r\n"
    				+ "      datasheet_tb.certificate_no,\r\n"
    				+ "      datasheet_tb.calibration_place,\r\n"
    				+ "      datasheet_tb.identification_marked_by,\r\n"
    				+ "      employee_tb.emp_id,\r\n"
    				+ "      employee_tb.first_name,\r\n"
    				+ "      employee_tb.last_name,\r\n"
    				+ "      employee_tb.designation,\r\n"
    				+ "      employee_tb.signature,\r\n"
    				+ "      customer_tb.cust_id,\r\n"
    				+ "      customer_tb.customer_name,\r\n"
    				+ "      customer_tb.adderss,\r\n"
    				+ "      customer_tb.logo,\r\n"
    				+ "      combined_master_1_tb.rep_id,\r\n"
    				+ "      combined_master_1_tb.rep_sr_no,\r\n"
    				+ "      combined_master_1_tb.condition_description,\r\n"
    				+ "      combined_master_1_tb.test_purpose,\r\n"
    				+ "      combined_master_1_tb.method_number,\r\n"
    				+ "      combined_master_1_tb.method_description,\r\n"
    				+ "      combined_master_1_tb.gauge_type,\r\n"
    				+ "      combined_master_1_tb.size,\r\n"
    				+ "      combined_master_1_tb.equipment_description,\r\n"
    				+ "      combined_master_1_tb.equipment_sr_no,\r\n"
    				+ "      combined_master_1_tb.calibration_date,\r\n"
    				+ "      combined_master_1_tb.equipment_description,\r\n"
    				+ "      combined_master_1_tb.validity_date,\r\n"
    				+ "      combined_master_2_tb.check_id,\r\n"
    				+ "      combined_master_2_tb.check_ddm\r\n"
    				+ "            		FROM\r\n"
    				+ "      certificate_tb\r\n"
    				+ "            		LEFT JOIN gaugemaster_tb ON certificate_tb.gauge_id = gaugemaster_tb.gauge_id\r\n"
    				+ "            		LEFT JOIN datasheet_tb ON certificate_tb.datasheet_id = datasheet_tb.datasheet_id\r\n"
    				+ "            		LEFT JOIN employee_tb ON gaugemaster_tb.emp_id = employee_tb.emp_id\r\n"
    				+ "            		LEFT JOIN customer_tb ON gaugemaster_tb.cust_id = customer_tb.cust_id\r\n"
    				+ "            		LEFT JOIN combined_master_1_tb ON gaugemaster_tb.rep_id = combined_master_1_tb.rep_id\r\n"
    				+ "                    LEFT JOIN combined_master_2_tb ON gaugemaster_tb.check_id = combined_master_2_tb.check_id\r\n"
    				+ "            		WHERE\r\n"
    				+ "      certificate_tb.cert_id = :cert_id";

    		Map<String, Object> params = new HashMap<>();
            params.put("cert_id", cert_id);
            List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(query, params);

            if (queryResults.isEmpty()) {
                throw new RuntimeException("Certificate Not Found");
            }

            // Log the query results for debugging
            String ddl_process_type = (String) queryResults.get(0).get("check_ddm");
            String reportTemplate = "/Reports/in_process_verify_report.jrxml";
            
            if("IN PROCESS INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/in_process_verify_report.jrxml";
            	System.out.println("IN PROCESS VERIFY REPORT REPORT DOWNLOADED");
            }else if("INCOMING INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/incoming_inspection_verify_report.jrxml";
            	System.out.println("INCOMING INSPECTION VERIFY REPORT DOWNLOADED");
            }else if("PRE-DISPATCH INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/pre_dispatch_verify.jrxml";
            	System.out.println("PRE-DISPATCH INSPECTION VERIFY REPORT DOWNLOADED");
            }else if("LAYOUT INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/layout_inspection_verify.jrxml";
            	System.out.println("LAYOUT INSPECTION VERIFY REPORT DOWNLOADED");
            }
            
            InputStream reportstream = this.getClass().getResourceAsStream(reportTemplate);
            JasperReport jasperReport = JasperCompileManager.compileReport(reportstream);

            // Use the query result as a data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(queryResults);

            // Parameters map for Jasper report
            Map<String, Object> parametersMap = new HashMap<>();

            // Fill the report with data
            JasperPrint printReport = JasperFillManager.fillReport(jasperReport, parametersMap, dataSource);

            // Log the filled report for debugging
            System.out.println("JasperPrint: " + printReport);

            // Configure response to return PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=Certificate_" + cert_id + ".pdf");

            try (OutputStream out = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(printReport, out);
                out.flush();
            }catch (Exception ex) {
            	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error generating report: " + ex.getMessage());
                ex.printStackTrace();
            }

    	}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Error generation blank report" + e.getMessage());
		}
		return null;
    }

    public CertificateDetails exportCertificateById(Integer cert_id, HttpServletResponse response) throws JRException, IOException {
        try {
        	
        	Optional<CertificateDetails> certificateOptional = certificateRepository.findById(cert_id);
            if (!certificateOptional.isPresent()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Certificate Not Found");
                return null;
            }

            CertificateDetails certificate = certificateOptional.get();

            // Check if the certificate is approved
            if (!certificate.getApproved()) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Certificate not approved for export");
                return null;
            }
        	
            // SQL query to fetch certificate details with related entities
            String queryString = "SELECT\r\n"
            		+ "    certificate_tb.cert_id,\r\n"
            		+ "    certificate_tb.nine_thirty, \r\n"
            		+ "    certificate_tb.ten_thirty, \r\n"
            		+ "    certificate_tb.twelve_thirty, \r\n"
            		+ "    certificate_tb.two_thirty, \r\n"
            		+ "    certificate_tb.four_thirty, \r\n"
            		+ "    certificate_tb.six_thirty, \r\n"
            		+ "    certificate_tb.result,\r\n"
            		+ "    certificate_tb.prod_plan_qty, \r\n"
            		+ "    certificate_tb.act_prod_qty, \r\n"
            		+ "    certificate_tb.rework_qty, \r\n"
            		+ "    certificate_tb.reject_qty, \r\n"
            		+ "    certificate_tb.error_size, \r\n"
            		+ "    certificate_tb.mean, \r\n"
            		+ "    certificate_tb.nominal_size, \r\n"
            		+ "    certificate_tb.observed_size, \r\n"
            		+ "    certificate_tb.r1, \r\n"
            		+ "    certificate_tb.r2, \r\n"
            		+ "    certificate_tb.r3, \r\n"
            		+ "    certificate_tb.r4, \r\n"
            		+ "    certificate_tb.datasheet_id, \r\n"
            		+ "    certificate_tb.gauge_id, \r\n"
            		+ "    certificate_tb.gauge_inst, \r\n"
            		+ "    certificate_tb.product_char, \r\n"
            		+ "    certificate_tb.special_char,\r\n"
            		+ "    certificate_tb.machine_no, \r\n"
            		+ "    certificate_tb.operation_desc, \r\n"
            		+ "    certificate_tb.operation_no,\r\n"
            		+ "    certificate_tb.emp_id,\r\n"
            		+ "    \r\n"
            		+ "    -- Employee details related to certificate_tb.emp_id (Creator)\r\n"
            		+ "    emp1.emp_id AS creator_id,\r\n"
            		+ "    emp1.first_name AS creator_first_name,\r\n"
            		+ "    emp1.last_name AS creator_last_name,\r\n"
            		+ "    emp1.designation AS creator_designation,\r\n"
            		+ "    emp1.signature AS creator_signature,\r\n"
            		+ "\r\n"
            		+ "    -- Employee details related to certificate_tb.approved_by (Approver)\r\n"
            		+ "    emp2.emp_id AS approver_id,\r\n"
            		+ "    emp2.first_name AS approver_first_name,\r\n"
            		+ "    emp2.last_name AS approver_last_name,\r\n"
            		+ "    emp2.designation AS approver_designation,\r\n"
            		+ "    emp2.signature AS approver_signature,\r\n"
            		+ "    \r\n"
            		+ "    gaugemaster_tb.gauge_id,\r\n"
            		+ "    gaugemaster_tb.gauge_name,\r\n"
            		+ "    gaugemaster_tb.gauge_type,\r\n"
            		+ "    gaugemaster_tb.gauge_sr_no,\r\n"
            		+ "    gaugemaster_tb.size,\r\n"
            		+ "    gaugemaster_tb.least_count,\r\n"
            		+ "    gaugemaster_tb.manufacture_id,\r\n"
            		+ "    gaugemaster_tb.make,\r\n"
            		+ "    datasheet_tb.datasheet_id,\r\n"
            		+ "    datasheet_tb.calibration_date,\r\n"
            		+ "    datasheet_tb.next_calibration_date,\r\n"
            		+ "    datasheet_tb.ref_dc_no,\r\n"
            		+ "    datasheet_tb.date_of_reciept,  -- corrected from \"date_of_reciept\"\r\n"
            		+ "    datasheet_tb.issue_date,\r\n"
            		+ "    datasheet_tb.certificate_no,\r\n"
            		+ "    datasheet_tb.calibration_place,\r\n"
            		+ "    datasheet_tb.identification_marked_by,\r\n"
            		+ "    customer_tb.cust_id,\r\n"
            		+ "    customer_tb.customer_name,\r\n"
            		+ "    customer_tb.adderss,\r\n"
            		+ "    customer_tb.logo,\r\n"
            		+ "    combined_master_1_tb.rep_id,\r\n"
            		+ "    combined_master_1_tb.rep_sr_no,\r\n"
            		+ "    combined_master_1_tb.condition_description,\r\n"
            		+ "    combined_master_1_tb.test_purpose,\r\n"
            		+ "    combined_master_1_tb.method_number,\r\n"
            		+ "    combined_master_1_tb.method_description,\r\n"
            		+ "    combined_master_1_tb.gauge_type,\r\n"
            		+ "    combined_master_1_tb.size,\r\n"
            		+ "    combined_master_1_tb.equipment_description,\r\n"
            		+ "    combined_master_1_tb.equipment_sr_no,\r\n"
            		+ "    combined_master_1_tb.calibration_date,\r\n"
            		+ "    combined_master_1_tb.validity_date,\r\n"
            		+ "    combined_master_2_tb.check_id,\r\n"
            		+ "    combined_master_2_tb.check_ddm\r\n"
            		+ "FROM\r\n"
            		+ "    certificate_tb\r\n"
            		+ "    LEFT JOIN gaugemaster_tb ON certificate_tb.gauge_id = gaugemaster_tb.gauge_id\r\n"
            		+ "    LEFT JOIN datasheet_tb ON certificate_tb.datasheet_id = datasheet_tb.datasheet_id\r\n"
            		+ "    LEFT JOIN employee_tb AS emp1 ON datasheet_tb.emp_id = emp1.emp_id\r\n"
            		+ "    LEFT JOIN employee_tb AS emp2 ON certificate_tb.emp_id = emp2.emp_id\r\n"
            		+ "    LEFT JOIN customer_tb ON gaugemaster_tb.cust_id = customer_tb.cust_id\r\n"
            		+ "    LEFT JOIN combined_master_1_tb ON gaugemaster_tb.rep_id = combined_master_1_tb.rep_id\r\n"
            		+ "    LEFT JOIN combined_master_2_tb ON gaugemaster_tb.check_id = combined_master_2_tb.check_id\r\n"
            		+ "WHERE\r\n"
            		+ "    certificate_tb.cert_id = :cert_id";

            // Execute the query and get the result as a list of maps
            Map<String, Object> params = new HashMap<>();
            params.put("cert_id", cert_id);
            List<Map<String, Object>> queryResults = jdbcTemplate.queryForList(queryString, params);

            if (queryResults.isEmpty()) {
                throw new RuntimeException("Certificate Not Found");
            }

            // Log the query results for debugging
            System.out.println("Query Results: " + queryResults);

            String ddl_process_type = (String) queryResults.get(0).get("check_ddm");
            String reportTemplate = "/Reports/CertificateReportById.jrxml";
            
            if("IN PROCESS INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/CertificateReportById.jrxml";
            	System.out.println("IN PROCESS REPORT DOWNLOADED");
            }else if("INCOMING INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/incoming_inspection_final_report.jrxml";
            	System.out.println("INCOMING INSPECTION REPORT DOWNLOADED");
            }else if("PRE-DISPATCH INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/pre_dispatch_final_report.jrxml";
            	System.out.println("PRE-DISPATCH INSPECTION REPORT DOWNLOADED");
            }else if("LAYOUT INSPECTION".equals(ddl_process_type)) {
            	reportTemplate = "/Reports/layout_inspection_final_report.jrxml";
            	System.out.println("LAYOUT INSPECTION REPORT DOWNLOADED");
            }
            
            InputStream reportstream = this.getClass().getResourceAsStream(reportTemplate);
            JasperReport jasperReport = JasperCompileManager.compileReport(reportstream);

            // Use the query result as a data source
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(queryResults);

            // Parameters map for Jasper report
            Map<String, Object> parametersMap = new HashMap<>();

            // Fill the report with data
            JasperPrint printReport = JasperFillManager.fillReport(jasperReport, parametersMap, dataSource);

            // Log the filled report for debugging
            System.out.println("JasperPrint: " + printReport);

            // Configure response to return PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=Certificate_" + cert_id + ".pdf");

            try (OutputStream out = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(printReport, out);
                out.flush();
            }catch (Exception ex) {
            	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error generating report: " + ex.getMessage());
                ex.printStackTrace();
			}
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error generating report: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
