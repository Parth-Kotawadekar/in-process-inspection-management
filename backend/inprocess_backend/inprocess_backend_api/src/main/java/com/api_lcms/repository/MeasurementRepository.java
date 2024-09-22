package com.api_lcms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api_lcms.model.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer>{

}
