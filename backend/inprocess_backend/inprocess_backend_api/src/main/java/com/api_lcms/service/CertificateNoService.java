package com.api_lcms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_lcms.model.Certificate_No_Details;
import com.api_lcms.repository.CertificateNoRepository;
@Service
public class CertificateNoService {

	@Autowired
	private CertificateNoRepository certificateNoRepository;

	public List<Certificate_No_Details> allcertificatesUlr(){
		return certificateNoRepository.findAll();
	}

	public Certificate_No_Details getCertificateUlrById(Integer id) {
		return certificateNoRepository.findById(id).orElse(null);
	}

	public Certificate_No_Details createCertificateUlrNo(Certificate_No_Details certificate_ulr_details) {
		return certificateNoRepository.save(certificate_ulr_details);
	}

	public Certificate_No_Details updateCertificate_No_Details(Certificate_No_Details cert) {

		Certificate_No_Details cert1 = new Certificate_No_Details();

		if(cert1 != null) {
			cert1.setCertificate_no(cert.getCertificate_no());
			cert1.setUlr_no(cert.getUlr_no());
			cert1.setSuffixes(cert.getSuffixes());
			cert1.setCreated_on_date(cert.getCreated_on_date());

			return certificateNoRepository.save(cert);
		}
		return null;
	}
}
