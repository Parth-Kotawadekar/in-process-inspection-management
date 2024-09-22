package com.api_lcms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_lcms.model.Combined_Master_2;
import com.api_lcms.repository.Combined_Master_2_Repository;

@Service
public class CombinedMaster2Service{

	@Autowired
	private Combined_Master_2_Repository cmbRep2;

	public List<Combined_Master_2> getAllCombined_Master_2(){
		return cmbRep2.findAll();
	}

	public Combined_Master_2 getById(Integer check_id) {
		return cmbRep2.findById(check_id).orElse(null);
	}

	public Combined_Master_2 createCombined_Master_2(Combined_Master_2 master) {
		return cmbRep2.save(master);
	}

	public Combined_Master_2 updateCombined_Master_2(Combined_Master_2 master, Integer id) {
		Combined_Master_2 existingMaster_2 = cmbRep2.findById(id).orElse(null);

		if(existingMaster_2 != null) {
			existingMaster_2.setGauge_type(master.getGauge_type());
			existingMaster_2.setLeast_count(master.getLeast_count());
			existingMaster_2.setPoint_of_measurement(master.getPoint_of_measurement());
			existingMaster_2.setSize_range(master.getSize_range());
			existingMaster_2.setType_sr_no(master.getType_sr_no());
			existingMaster_2.setProduct_char(master.getProduct_char());
			existingMaster_2.setSpecial_char(master.getSpecial_char());
			existingMaster_2.setGauge_inst(master.getGauge_inst());

			return cmbRep2.save(existingMaster_2);
		}

		return null;
	}

	public void deleteMaster(Integer id) {
		cmbRep2.deleteById(id);
	}
}
