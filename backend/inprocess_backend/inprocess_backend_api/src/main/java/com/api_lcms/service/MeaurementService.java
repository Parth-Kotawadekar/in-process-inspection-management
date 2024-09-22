package com.api_lcms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_lcms.model.Measurement;
import com.api_lcms.repository.MeasurementRepository;

@Service
public class MeaurementService {

	@Autowired
	private MeasurementRepository measurementRepository;

	public Measurement saveMeasurement(Measurement measurement) {
		return measurementRepository.save(measurement);
	}

	public Optional<Measurement> getMeasurementByDatasheetId(Integer datasheet_id){
		return measurementRepository.findById(datasheet_id);
	}
}
