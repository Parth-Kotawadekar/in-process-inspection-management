package com.api_lcms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.api_lcms.dto.EmailSenderDTO;
import com.api_lcms.model.Datasheet;
import com.api_lcms.model.EmailSender;
import com.api_lcms.repository.DatasheetRepository;
import com.api_lcms.repository.EmailSenderRepository;

@Service
public class EmailSenderService {

	@Autowired
	private EmailSenderRepository emailSenderRepository;

	@Autowired
	private DatasheetRepository datasheetRepository;

	@Autowired
	private JavaMailSender mailSender;

	public EmailSender createEmailDetails(EmailSender email) {
		return emailSenderRepository.save(email);
	}

	private String formatDatasheet(Datasheet datasheet) {
        return "Datasheet ID: " + datasheet.getDatasheet_id() + "\n" +
               "Name: " + datasheet.getGaugeMaster() + "\n" +
               "Description: " + datasheet.getCalibration_date();
    }

	public void senEmail(EmailSenderDTO emailSenderDTO) {

		List<Datasheet> datasheets = datasheetRepository.findAll();

		String datasheetsDetails = datasheets.stream()
                .map(datasheet -> formatDatasheet(datasheet))
                .collect(Collectors.joining("\n\n"));

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(emailSenderDTO.getSender_email());
		message.setTo(emailSenderDTO.getReceiver_email());
		message.setSubject("This is Dummy Email...");
		message.setText("Here are the details of all datasheets:\n\n" + datasheetsDetails);

		mailSender.send(message);
	}

	public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
