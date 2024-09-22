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

import com.api_lcms.model.Combined_Master_2;
import com.api_lcms.service.CombinedMaster2Service;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CombinedMaster2Comtroller {

	@Autowired
	private CombinedMaster2Service cmbSer2;

	@GetMapping("/showAllmaster2")
	public List<Combined_Master_2> getAll(){
		return cmbSer2.getAllCombined_Master_2();
	}

	@GetMapping("/master2/{check_id}")
	public Combined_Master_2 getByid(@PathVariable Integer check_id) {
		return cmbSer2.getById(check_id);
	}

	@PostMapping("/addNewmaster2")
	public Combined_Master_2 saveCombined_Master_2(@RequestBody Combined_Master_2 master2) {
		return cmbSer2.createCombined_Master_2(master2);
	}

	@PutMapping("/editmaster2/{check_id}")
	public Combined_Master_2 updateCombined_Master_2(@RequestBody Combined_Master_2 master2, @PathVariable Integer check_id) {
		return cmbSer2.updateCombined_Master_2(master2, check_id);
	}

	@DeleteMapping("/deletemaster2/{id}")
	public void delete(@PathVariable Integer id) {
		cmbSer2.deleteMaster(id);
	}
}
