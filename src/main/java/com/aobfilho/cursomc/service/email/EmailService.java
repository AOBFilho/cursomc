package com.aobfilho.cursomc.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.aobfilho.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	void sendHtmlEmail(MimeMessage msg);
	
}
