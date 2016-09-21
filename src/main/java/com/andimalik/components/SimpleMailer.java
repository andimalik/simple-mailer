package com.andimalik.components;

import javax.mail.MessagingException;

/**
 * The SimpleMailer object used for sending a simple e-mail message.
 * 
 * @author Andi Malik
 * @since 2016-09-21
 */
public interface SimpleMailer {
	/**
	 * Set the sender e-mail address.
	 * 
	 * @param emailAdrress
	 *            The sender's e-mail address.
	 */
	public void setFrom(String emailAdrress);

	/**
	 * Set the recipients e-mail addresses.
	 * 
	 * @param emailAdrresses
	 *            The recipients e-mail addresses (comma separated).
	 */
	public void setTo(String emailAdrresses);

	/**
	 * Set Carbon Copy.
	 * 
	 * @param emailAdrresses
	 *            Carbon copy e-mail addresses (comma separated).
	 */
	public void setCc(String emailAdrresses);

	/**
	 * Set Black Carbon Copy.
	 * 
	 * @param emailAdrresses
	 *            Black Carbon Copy e-mail addresses (comma separated).
	 */
	public void setBcc(String emailAdrresses);

	/**
	 * Set e-mail subject.
	 * 
	 * @param subject
	 *            The subject.
	 */
	public void setSubject(String subject);

	/**
	 * Set e-mail content.
	 * 
	 * @param content
	 *            The content.
	 * @param type
	 *            The content MIME type.
	 */
	public void setContent(Object content, String type);

	/**
	 * Sends the e-mail.
	 * 
	 * @throws MessagingException
	 */
	public void sendMail() throws MessagingException;
}
