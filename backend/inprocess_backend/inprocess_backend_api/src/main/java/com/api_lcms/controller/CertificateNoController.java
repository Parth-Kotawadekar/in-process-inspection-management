package com.api_lcms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_lcms.model.Certificate_No_Details;
import com.api_lcms.service.CertificateNoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CertificateNoController {

	@Autowired
	private CertificateNoService certificateNoService;

	@GetMapping("/allcert")
	public List<Certificate_No_Details> allcertificateUlrDetails() {
		return certificateNoService.allcertificatesUlr();
	}

	@GetMapping("/certById/{id}")
	public Certificate_No_Details getByCertificate_No_Details(@PathVariable Integer id) {
		return certificateNoService.getCertificateUlrById(id);
	}

	@PostMapping("/saveulrcert")
	public Certificate_No_Details saveCertUl(@RequestBody Certificate_No_Details ulrcert) {
		return certificateNoService.createCertificateUlrNo(ulrcert);
	}
}
