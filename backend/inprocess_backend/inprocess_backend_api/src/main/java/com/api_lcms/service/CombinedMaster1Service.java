package com.api_lcms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_lcms.model.CombinedMaster_1;
import com.api_lcms.repository.CombinedMaster1Repository;

@Service
public class CombinedMaster1Service {

	@Autowired
	private CombinedMaster1Repository cmdRepo;

	public List<CombinedMaster_1> showAllCombinedMaster_1(){
		return cmdRepo.findAll();
	}

	public CombinedMaster_1 getById(Integer id) {
		return cmdRepo.findById(id).orElse(null);
	}

	public CombinedMaster_1 createCombinedMaster_1(CombinedMaster_1 master) {
		return cmdRepo.save(master);
	}

	public CombinedMaster_1 updateCombinedMaster_1(CombinedMaster_1 master, Integer id) {
		CombinedMaster_1 existMaster_1 = cmdRepo.findById(id).orElse(null);

		if(existMaster_1 != null) {
			existMaster_1.set_id(master.getRep_id());
			existMaster_1.setRep_sr_no(master.getRep_sr_no());
			existMaster_1.setCalibration_date(master.getCalibration_date());
			existMaster_1.setCondition_description(master.getCondition_description());
			existMaster_1.setEquipment_description(master.getEquipment_description());
			existMaster_1.setEquipment_sr_no(master.getEquipment_sr_no());
			existMaster_1.setGauge_type(master.getGauge_type());
			existMaster_1.setMethod_description(master.getMethod_description());
			existMaster_1.setMethod_number(master.getMethod_number());
			existMaster_1.setSize(master.getSize());
			existMaster_1.setTest_purpose(master.getTest_purpose());
			existMaster_1.setValidity_date(master.getValidity_date());

			return cmdRepo.save(existMaster_1);
		}

		return null;
	}

	public void deleteMaster(Integer id) {
		cmdRepo.deleteById(id);
	}
}
