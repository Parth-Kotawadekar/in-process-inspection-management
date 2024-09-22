package com.api_lcms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_lcms.dto.RemarksDTO;
import com.api_lcms.model.CertificateRemarks;
import com.api_lcms.repository.RemarkRepository;

@Service
public class CertificateRemarkService {

	@Autowired
	private RemarkRepository remarkRepository;

	public List<CertificateRemarks> getAllRemarks(){
		return remarkRepository.findAll();
	}

	public CertificateRemarks getRemarkById(Integer id) {
		return remarkRepository.findById(id).orElse(null);
	}

	public CertificateRemarks saveCertificateRemarks(RemarksDTO remarksdto) {

		CertificateRemarks remks = new CertificateRemarks();

		remks.setRemarks(remarksdto.getRemarks());

		return remarkRepository.save(remks);
	}

	public CertificateRemarks updateCertificateRemarks(Integer id, RemarksDTO remarksdto) {

		CertificateRemarks remarks = new CertificateRemarks();

		if(remarks != null) {
			remarks.setRemarks(remarksdto.getRemarks());

			return remarkRepository.save(remarks);
		}

		return remarks;
	}

}
