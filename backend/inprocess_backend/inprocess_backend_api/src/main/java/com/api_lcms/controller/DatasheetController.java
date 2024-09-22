package com.api_lcms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_lcms.dto.DatasheetDTO;
import com.api_lcms.model.Datasheet;
import com.api_lcms.service.DatasheetService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DatasheetController {

	@Autowired
	private DatasheetService dataSer;

	@GetMapping("/showdatasheets")
	public List<Datasheet> allDatasheets() {
		return dataSer.showAll();
	}

	@GetMapping("/datasheet/{datasheet_id}")
	public Datasheet getDatasheetById(@PathVariable Integer datasheet_id) {
		return dataSer.getDatasheet(datasheet_id);
	}

	@PostMapping("/save_datasheet")
	public Datasheet createDatasheet(@RequestBody DatasheetDTO datasheetDTO, HttpServletResponse response) throws IOException {

		if(datasheetDTO.getGauge_id() == null) {
			System.out.println("Unable to fetch gauge id");
		}else{
			System.out.println("Fetched Gauge Id is: " + datasheetDTO.getGauge_id());
		}

		return dataSer.saveDatasheet(datasheetDTO, response);
	}

	@DeleteMapping("/deletedatasheet/{datasheet_id}")
	public void deleteDatasheet(@PathVariable Integer datasheet_id) {
		try {
			dataSer.deleteDatasheet(datasheet_id);
			System.out.println("Deleted Successfully...");
		} catch (Exception e) {
			System.out.println("Unable to delete" + e);
		}
	}
}
