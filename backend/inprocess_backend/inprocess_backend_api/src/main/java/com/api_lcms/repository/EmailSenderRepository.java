package com.api_lcms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api_lcms.model.EmailSender;

public interface EmailSenderRepository extends JpaRepository<EmailSender, Integer>{

	@Override
	Optional<EmailSender> findById(Integer id);

}
