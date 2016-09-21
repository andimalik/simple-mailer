package com.andimalik.components;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.andimalik.components.factories.SimpleMailerFactory;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws AddressException,
			FileNotFoundException, IOException, MessagingException {
		SimpleMailer mailer = SimpleMailerFactory.getInstance(
				"mail.cfg.properties", "bingo");
		mailer.setFrom("user1@host.tld");
		mailer.setTo("user2@host.tld,user3@host.tld");
		mailer.setCc("user4@host.tld");
		mailer.setBcc("user5@host.tld");
		mailer.setSubject("A Subject");
		mailer.setContent("<h1>A Header</h1><p>A paragraf.</p>", "text/html");

		mailer.sendMail();
	}
}
