package com.luannascimento.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		
		LOG.info("Simulando envio de email...");
		mailSender.send(msg);
		LOG.info("Email enviado");
		
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		
		LOG.info("Simulando envio de email HTML...");
		javaMailSender.send(msg);
		LOG.info("Email enviado");
		
		
	}

}
