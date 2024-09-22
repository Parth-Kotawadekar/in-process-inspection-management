package com.api_lcms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_lcms.dto.GaugeMasterDTO;
import com.api_lcms.model.GaugeMaster;
import com.api_lcms.service.GaugeMasterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class GaugeMasterController {

	@Autowired
	private GaugeMasterService gaugeSer;

	@GetMapping("/allGauges")
	public List<GaugeMaster> showAllGaugeMasters(){
		return gaugeSer.getAllGaugeMasters();
	}

	@GetMapping("/gauge/{gauge_id}")
	public GaugeMaster getGaugeById(@PathVariable Integer gauge_id) {
		return gaugeSer.getGaugeById(gauge_id);
	}

	@PostMapping("/addNewGauge")
    public ResponseEntity<?> addNewGauge(@RequestBody GaugeMasterDTO gaugeMasterDTO, HttpSession session) {
        try {
            GaugeMaster savedGaugeMaster = gaugeSer.saveGaugeMaster(gaugeMasterDTO, null, null, session);
            return ResponseEntity.ok(savedGaugeMaster);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseException(null, null, e, "Error saving gauge: " + e.getMessage(), null));
        }
    }

	@PutMapping("/editGauge/{gauge_id}")
	public GaugeMaster updateGaugeMaster(@RequestBody GaugeMasterDTO master, @PathVariable Integer gauge_id, HttpServletResponse response) throws IOException {
		return gaugeSer.updateGaueGaugeMaster(master, gauge_id, response);
	}

	@DeleteMapping("/deleteGauge/{gauge_id}")
	public void deleteGauge(@PathVariable Integer gauge_id) {
		gaugeSer.deleteGauge(gauge_id); 
	}

	
}
