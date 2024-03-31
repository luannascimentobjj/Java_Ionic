package com.luannascimento.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.luannascimento.cursomc.domains.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido ped);
	
	void sendEmail(SimpleMailMessage msg);

}
