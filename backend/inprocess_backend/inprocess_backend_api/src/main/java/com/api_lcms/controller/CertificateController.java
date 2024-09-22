package com.api_lcms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_lcms.dto.CertificateDTO;
import com.api_lcms.model.CertificateDetails;
import com.api_lcms.service.CertificateService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @GetMapping("/all_certificates")
    public List<CertificateDetails> showAllCertificates() {
        return certificateService.showAllCertificate();
    }

    @PostMapping("/save_certificate")
    public CertificateDetails createCertificate(@RequestBody CertificateDTO certificate, HttpServletResponse response) throws IOException {

//    	System.out.println(certificate.getDatasheet_id());
//    	System.out.println(certificate.getGauge_id());
    	if(certificate.getGauge_id() == null) {
    		System.out.println("Cannot fetch gauge id");
    	}
    	else if(certificate.getDatasheet_id() == null) {
    		System.out.println("Cannot fetch datasheet id");
    	}
    	else {
    		System.out.println("Datasheet Id is: " + certificate.getDatasheet_id() + "\n" + "Gauge Id is: " + certificate.getGauge_id());
    	}
    	try {
            return certificateService.saveCertificateDetails(certificate, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"An error occurred while saving the certificate: " + e.getMessage() + "\"}");
            return null;
        }
    }
    
    @PostMapping("/approvecertificates/{cert_id}/approve")
    public CertificateDetails approveCertificate(@PathVariable("cert_id") Integer cert_id, @RequestBody CertificateDTO certificateDTO) {
    	
    	if(cert_id != null) {
    		System.out.println("Approved certificate with id: " + certificateDTO.getCert_id());
    	}if(certificateDTO.getEmp_id() != null) {
			System.out.println("Approved By Id Null");
		}else {
			System.out.println("\nCertificate Id: " + cert_id + "\n Approved By Id: " + certificateDTO.getEmp_id());
		}
    	
    	return certificateService.approveCertificate(cert_id, certificateDTO);
    }
    
//    @GetMapping("/certificate/{cert_id}")
//    public CertificateDetails certificateByid(@PathVariable("cert_id") Integer id, HttpServletResponse response) throws IOException {
//        return certificateService.getCertificateById(id, response);
//    }

    //Export Certificate
    @GetMapping("/downloadfinalreport/{cert_id}")
    public CertificateDetails certificateDownload(@PathVariable Integer cert_id, HttpServletResponse response) throws JRException, IOException{

    	return certificateService.exportCertificateById(cert_id, response);
    }

    @GetMapping("/downloadverifyeport/{cert_id}")
    public CertificateDetails blankCertificateDownload(@PathVariable Integer cert_id, HttpServletResponse response) throws IOException {
    	return certificateService.exportBlankDatasheet(cert_id, response);
    }

    @GetMapping("/downloadblankreport/{cert_id}")
    public CertificateDetails blankReportDownload(@PathVariable Integer cert_id, HttpServletResponse response) throws IOException, JRException {
    	return certificateService.exportBlankReport(cert_id, response);
    }

    @GetMapping("/downloadwithdatareport/{cert_id}")
    public CertificateDetails withDataReportDownload(@PathVariable Integer cert_id, HttpServletResponse response) throws IOException, JRException {
    	return certificateService.exportWithDataReport(cert_id, response);
    }
}
