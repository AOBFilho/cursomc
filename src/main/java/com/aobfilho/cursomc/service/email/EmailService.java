package com.aobfilho.cursomc.service.email;

import org.springframework.mail.SimpleMailMessage;

import com.aobfilho.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
}
