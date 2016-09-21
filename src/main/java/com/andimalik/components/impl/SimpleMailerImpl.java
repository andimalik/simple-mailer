package com.andimalik.components.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.andimalik.components.SimpleMailer;

/**
 * Implementation of SimpleMailer interface.
 * 
 * @author Andi Malik andi.malik.notifications@gmail.com
 * @since 2016-09-21
 */
public class SimpleMailerImpl implements SimpleMailer {
	private Properties configurationProperties;
	private Session session;
	private MimeMessage message;

	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private Object content;
	private String contentTypeString;

	public SimpleMailerImpl(Properties configurationProperties)
			throws AddressException, MessagingException {
		this.configurationProperties = configurationProperties;
		this.init();
	}

	@Override
	public void setFrom(String emailAddress) {
		this.from = emailAddress;
	}

	@Override
	public void setTo(String emailAddresses) {
		this.to = emailAddresses;
	}

	@Override
	public void setCc(String emailAddresses) {
		this.cc = emailAddresses;
	}

	@Override
	public void setBcc(String emailAddresses) {
		this.bcc = emailAddresses;
	}

	@Override
	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public void setContent(Object content, String typeString) {
		this.content = content;
		this.contentTypeString = typeString;
	}

	@Override
	public void sendMail() throws MessagingException {
		this.prepareMessage();
		Transport.send(this.message);
	}

	private void init() throws AddressException, MessagingException {
		this.retrieveSession();
	}

	private void retrieveSession() {
		final String mailUserName = this.configurationProperties
				.getProperty("mail.username");
		final String mailUserPassword = this.configurationProperties
				.getProperty("mail.password");

		this.session = Session.getDefaultInstance(this.configurationProperties,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(mailUserName,
								mailUserPassword);
					}
				});
	}

	private void prepareMessage() throws AddressException, MessagingException {
		this.message = new MimeMessage(this.session);
		this.message.setFrom(new InternetAddress(this.from));

		this.message.addRecipients(Message.RecipientType.TO,
				InternetAddress.parse(this.to));

		this.message.addRecipients(Message.RecipientType.CC,
				InternetAddress.parse(this.cc));

		this.message.addRecipients(Message.RecipientType.BCC, this.bcc);

		this.message.setSubject(this.subject);
		this.message.setContent(this.content, this.contentTypeString);
	}
}
