package com.api_lcms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api_lcms.model.Datasheet;


public interface DatasheetRepository extends JpaRepository<Datasheet, Integer>{

//	@Modifying
//	@Transactional
//	@Query("INSERT INTO datasheet_tb (calibration_date, next_calibration_date, calibration_frequency, frequency_type, date_of_reciept, ref_dc_no, issue_date, identification_marked_by, certificate_no, calibration_place) " +
//		       "SELECT gm.calibration_date, gm.next_calibration_date, gm.calibration_frequency, gm.frequency_type, gm.date_of_reciept, gm.ref_dc_no, gm.issue_date, gm.identification_marked_by, gm.certificate_no, gm.calibration_place " +
//		       "FROM gaugemaster_tb gm WHERE gm.gauge_id =: n")
//		Datasheet insertDataByGaugeId(@Param("n") Integer gauge_id);

}
