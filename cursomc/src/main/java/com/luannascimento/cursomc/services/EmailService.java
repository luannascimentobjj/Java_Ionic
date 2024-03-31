package com.luannascimento.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.luannascimento.cursomc.domains.Pedido;

import jakarta.mail.internet.MimeMessage;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido ped);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido ped);
	
	void sendHtmlEmail(MimeMessage msg);

}
