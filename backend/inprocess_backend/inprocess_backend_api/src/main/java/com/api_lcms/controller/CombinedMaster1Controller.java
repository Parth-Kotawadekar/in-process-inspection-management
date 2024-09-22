package com.api_lcms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_lcms.model.CombinedMaster_1;
import com.api_lcms.service.CombinedMaster1Service;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CombinedMaster1Controller {

	@Autowired
	private CombinedMaster1Service cmbSer;

	@GetMapping("/showAllmaster1")
	public List<CombinedMaster_1> showAll(){
		return cmbSer.showAllCombinedMaster_1();
	}

	@GetMapping("/master1/{id}")
	public CombinedMaster_1 getGauge2ById(@PathVariable Integer id) {
		return cmbSer.getById(id);
	}

	@PostMapping("/addNewmaster1")
	public CombinedMaster_1 createCombinedMaster_1(@RequestBody CombinedMaster_1 master) {
		return cmbSer.createCombinedMaster_1(master);
	}

	@PutMapping("/editmaster1/{id}")
	public CombinedMaster_1 updateCombinedMaster_1(@RequestBody CombinedMaster_1 master, @PathVariable Integer id) {
		return cmbSer.updateCombinedMaster_1(master, id);
	}

	@DeleteMapping("/deletemaster1/{rep_id}")
	public void delete(@PathVariable Integer id) {
		cmbSer.deleteMaster(id);
	}


}
