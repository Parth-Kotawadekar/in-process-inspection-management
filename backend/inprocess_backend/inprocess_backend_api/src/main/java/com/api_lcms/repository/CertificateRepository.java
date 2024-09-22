package com.api_lcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_lcms.model.CertificateDetails;

public interface CertificateRepository extends JpaRepository<CertificateDetails, Integer> {

}
