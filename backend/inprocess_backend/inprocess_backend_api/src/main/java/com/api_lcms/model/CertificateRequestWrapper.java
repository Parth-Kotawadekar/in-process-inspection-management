package com.api_lcms.model;

import com.api_lcms.dto.CertificateDTO;
import com.api_lcms.dto.DatasheetDTO;

public class CertificateRequestWrapper {

	private DatasheetDTO datasheetDTO;
	private CertificateDTO certificateDTO;
	public DatasheetDTO getDatasheetDTO() {
		return datasheetDTO;
	}
	public void setDatasheetDTO(DatasheetDTO datasheetDTO) {
		this.datasheetDTO = datasheetDTO;
	}
	public CertificateDTO getCertificateDTO() {
		return certificateDTO;
	}
	public void setCertificateDTO(CertificateDTO certificateDTO) {
		this.certificateDTO = certificateDTO;
	}
}
