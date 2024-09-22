package com.api_lcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_lcms.model.CertificateRemarks;

public interface RemarkRepository extends JpaRepository<CertificateRemarks, Integer>{

}
