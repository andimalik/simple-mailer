package com.andimalik.components.factories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

import com.andimalik.components.SimpleMailer;
import com.andimalik.components.impl.SimpleMailerImpl;

/**
 * SimpleMailer object factory.
 * 
 * @author Andi Malik
 * @since 2016-09-21
 */
public class SimpleMailerFactory {
	private static SimpleMailer instance = null;

	/**
	 * Get the SimpleMailer instance with configuration properties in default
	 * location ("mail.cfg.properties").
	 * 
	 * @return SimpleMailer instance.
	 * @throws AddressException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static synchronized SimpleMailer getInstance()
			throws AddressException, FileNotFoundException, IOException,
			MessagingException {
		String configurationPropertiesFilePath = "mail.cfg.properties";
		return SimpleMailerFactory
				.getInstanceWithConfiguration(configurationPropertiesFilePath);
	}

	/**
	 * Get the SimpleMailer instance with configuration properties in the
	 * specified location.
	 * 
	 * @param configurationPropertiesFilePath
	 *            Path to properties file.
	 * @return SimpleMailer instance.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static synchronized SimpleMailer getInstance(
			String configurationPropertiesFilePath)
			throws FileNotFoundException, IOException, AddressException,
			MessagingException {
		return SimpleMailerFactory
				.getInstanceWithConfiguration(configurationPropertiesFilePath);
	}

	/**
	 * 
	 * @param configurationPropertiesFilePath
	 *            Path to properties file.
	 * @param propertiesFileEncryptionPassword
	 *            Encryption password used for properties value encryption.
	 * @return SimpleMailer instance.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static synchronized SimpleMailer getInstance(
			String configurationPropertiesFilePath,
			String propertiesFileEncryptionPassword)
			throws FileNotFoundException, IOException, AddressException,
			MessagingException {
		return SimpleMailerFactory.getInstanceWithEncryptableConfiguration(
				configurationPropertiesFilePath,
				propertiesFileEncryptionPassword);
	}

	private static SimpleMailer getInstanceWithConfiguration(
			String configurationPropertiesFilePath)
			throws FileNotFoundException, IOException, AddressException,
			MessagingException {
		if (SimpleMailerFactory.instance == null) {
			Properties properties = SimpleMailerFactory
					.loadConfigurationProperties(configurationPropertiesFilePath);

			SimpleMailerFactory.instance = new SimpleMailerImpl(properties);
		}

		return SimpleMailerFactory.instance;
	}

	private static SimpleMailer getInstanceWithEncryptableConfiguration(
			String configurationPropertiesFilePath, String encryptionPassword)
			throws FileNotFoundException, IOException, AddressException,
			MessagingException {
		if (SimpleMailerFactory.instance == null) {
			Properties properties = SimpleMailerFactory
					.loadEncryptableConfigurationProperties(
							configurationPropertiesFilePath, encryptionPassword);

			SimpleMailerFactory.instance = new SimpleMailerImpl(properties);
		}

		return SimpleMailerFactory.instance;
	}

	private static Properties loadConfigurationProperties(
			String configurationPropertiesFilePath)
			throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(configurationPropertiesFilePath));

		return properties;
	}

	private static Properties loadEncryptableConfigurationProperties(
			String configurationPropertiesFilePath, String encryptionPassword)
			throws FileNotFoundException, IOException {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(encryptionPassword);

		Properties properties = new EncryptableProperties(encryptor);
		properties.load(new FileInputStream(configurationPropertiesFilePath));

		return properties;
	}
}
