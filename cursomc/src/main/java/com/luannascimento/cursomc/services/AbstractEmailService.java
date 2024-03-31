package com.luannascimento.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.luannascimento.cursomc.domains.Pedido;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;



public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Pedido ped) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(ped);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido ped) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(ped.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + ped.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(ped.toString());
		return sm;
	}
	
	
	protected String htmlFromTemplatePedido(Pedido ped) {
		
		Context context = new Context();
		context.setVariable("pedido", ped);
		return  templateEngine.process("email/confirmacaoPedido", context);
				
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido ped) {

		try {

			MimeMessage mime = prepareMimeMessageFromPedido(ped);
			sendHtmlEmail(mime);
			
		} catch (Exception e) {
			sendOrderConfirmationEmail(ped);
		}

	}

	private MimeMessage prepareMimeMessageFromPedido(Pedido ped) throws MessagingException {
	
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(ped.getCliente().getEmail());
		mimeMessage.setFrom(sender);
		mimeMessage.setSubject("Pedido confirmado! Código: " + ped.getId());
		mimeMessage.setSentDate(new Date(System.currentTimeMillis()));
		mimeMessage.setContent(htmlFromTemplatePedido(ped), "text/html");
				
		return mimeMessage;
	}

	
}
