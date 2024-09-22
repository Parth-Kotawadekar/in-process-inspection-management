package com.api_lcms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_lcms.model.Measurement;
import com.api_lcms.service.MeaurementService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MeasurementController {


	@Autowired
	private MeaurementService meaurementService;

	@GetMapping("/measurement/{datasheet_id}")
	public Optional<Measurement> getMeasurementsByDatasheetId(@PathVariable Integer datasheet_id){
		return meaurementService.getMeasurementByDatasheetId(datasheet_id);
	}
}
