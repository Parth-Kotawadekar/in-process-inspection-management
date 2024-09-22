package com.api_lcms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_lcms.dto.RemarksDTO;
import com.api_lcms.model.CertificateRemarks;
import com.api_lcms.service.CertificateRemarkService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CertRemarkController {

	@Autowired
	private CertificateRemarkService remarkService;

	@GetMapping("/allremarks")
	public List<CertificateRemarks> fetchAll() {
		return remarkService.getAllRemarks();
	}

	@GetMapping("/remks/{id}")
	public CertificateRemarks fetchById(@PathVariable Integer id) {
		return remarkService.getRemarkById(id);
	}

	@PutMapping("/updremks/{id}")
	public CertificateRemarks updateCertificateRemarks(@PathVariable Integer id, @RequestBody RemarksDTO remks) {
		return remarkService.updateCertificateRemarks(id, remks);
	}

	@PostMapping("/save_remks")
	public CertificateRemarks saveCertificateRemarks(@RequestBody RemarksDTO remks) {
		return remarkService.saveCertificateRemarks(remks);
	}

}
