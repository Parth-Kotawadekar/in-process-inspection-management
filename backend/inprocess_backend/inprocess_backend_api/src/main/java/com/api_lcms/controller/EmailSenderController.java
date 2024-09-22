package com.api_lcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_lcms.dto.EmailSenderDTO;
import com.api_lcms.model.EmailSender;
import com.api_lcms.service.EmailSenderService;

@RestController
@RequestMapping("/api")
public class EmailSenderController {

	@Autowired
	private EmailSenderService emailSenderService;

	@PostMapping("/send")
	public void sendEmail(@RequestBody EmailSenderDTO emailSenderDTO) {
		emailSenderService.senEmail(emailSenderDTO);
		System.out.println("Email sent successfully...");
	}

	@PostMapping("/saveemail")
	public void saveEmailDetails(@RequestBody EmailSender email) {
		emailSenderService.createEmailDetails(email);
	}
}
