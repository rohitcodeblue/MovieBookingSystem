package com.project.moviebooking.email;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.project.moviebooking.constant.AppBaseConstants;
import com.project.moviebooking.util.CommonUtil;


@Component
public class EmailTarget {
//	@Autowired
	private JavaMailSenderImpl emailSender = new JavaMailSenderImpl(); // emailSender;

	// probably do not need this here.
	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private String port;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	/**
	 * Send simple email message with normal priorty
	 * 
	 * @param to
	 * @param from
	 * @param subject
	 * @param text
	 * @throws MessagingException
	 */
	public void send(String to, String from, String subject, String text, boolean isTxtHtml) throws MessagingException {
		send(to, from, subject, text, (EmailAttachmentImpl) null, EmailPriority.NORMAL_PRIORITY, isTxtHtml);
	}

	/**
	 * Send simple email message with set priority
	 * 
	 * @param to
	 * @param from
	 * @param subject
	 * @param text
	 * @param priority
	 * @throws MessagingException
	 */
	public void send(String to, String from, String subject, String text, EmailPriority priority, boolean isTxtHtml)
			throws MessagingException {
		send(to, from, subject, text, (EmailAttachmentImpl) null, priority, isTxtHtml);
	}

	/**
	 * Send multipart email including an attachment with normal priority
	 * 
	 * @param to
	 * @param from
	 * @param subject
	 * @param text
	 * @param attachment
	 * @throws MessagingException
	 */
	public void send(String to, String from, String subject, String text, byte[] attachment, boolean isTxtHtml)
			throws MessagingException {
		EmailAttachmentImpl emailAttachment = null;
		if (attachment != null && attachment.length > 0) {
			emailAttachment = new EmailAttachmentImpl(new ByteArrayEmailAttachment(attachment));
		}
		send(to, from, subject, text, emailAttachment, EmailPriority.NORMAL_PRIORITY, isTxtHtml);
	}

	/**
	 * Send multipart email including an attachment
	 * 
	 * @param to
	 * @param from
	 * @param subject
	 * @param text
	 * @param attachment
	 * @param priority
	 * @throws MessagingException
	 */
	public void send(String to, String from, String subject, String text, byte[] attachment, EmailPriority priority,
			boolean isTxtHtml) throws MessagingException {
		EmailAttachmentImpl emailAttachment = null;
		if (attachment != null && attachment.length > 0) {
			emailAttachment = new EmailAttachmentImpl(new ByteArrayEmailAttachment(attachment));
		}
		send(to, from, subject, text, emailAttachment, priority, isTxtHtml);
	}

	/**
	 * Send multipart email including an attachment
	 * 
	 * @param to
	 * @param from
	 * @param subject
	 * @param text
	 * @param attachment
	 * @param priority
	 * @throws MessagingException
	 */
	public void send(String to, String from, String subject, String text, File attachment, EmailPriority priority,
			boolean isTxtHtml) throws MessagingException {
		EmailAttachmentImpl emailAttachment = null;
		if (attachment != null) {
			emailAttachment = new EmailAttachmentImpl(new FileEmailAttachment(attachment.getAbsolutePath()));
		}
		send(to, from, subject, text, emailAttachment, priority, isTxtHtml);
	}

	/**
	 * Send multipart email including an attachment with normal priority
	 * 
	 * @param to
	 * @param from
	 * @param subject
	 * @param text
	 * @param attachment
	 * @throws MessagingException
	 */
	public void send(String to, String from, String subject, String text, File attachment, boolean isTxtHtml)
			throws MessagingException {
		EmailAttachmentImpl emailAttachment = null;
		if (attachment != null) {
			emailAttachment = new EmailAttachmentImpl(new FileEmailAttachment(attachment.getAbsolutePath()));
		}
		send(to, from, subject, text, emailAttachment, EmailPriority.NORMAL_PRIORITY, isTxtHtml);
	}
	
	/**
	 * Rohit Testing Email
	 */
	private void sendCustom(String to, String from, String subject, String text, boolean isTxtHtml) throws MessagingException {
		MimeMessagePreparator preparator = message -> {
			MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE);
			helper.setFrom(from);
			helper.setTo(InternetAddress.parse(to));
			helper.setSubject(subject);
			helper.setText(text, isTxtHtml);
		};
		configureJavaMailSender();
		emailSender.send(preparator);
	}

	/**
	 * Main method sending mail
	 */
	private void send(String to, String from, String subject, String text, EmailAttachmentImpl attachment,
			EmailPriority priority, boolean isTxtHtml) throws MessagingException {
		MimeMessagePreparator preparator = message -> {
			MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE);
			helper.setFrom(from);
			helper.setTo(InternetAddress.parse(to));
			helper.setSubject(decorateSubject(subject, priority.getPriority()));
			helper.setPriority(priority.getPriority());
			helper.setText(text, isTxtHtml);
			if (attachment != null) {
				helper.addAttachment(attachment.getAttachmentDataSource().getName(),
						attachment.getAttachmentDataSource());
			}
		};
		configureJavaMailSender();
		emailSender.send(preparator);
	}

	/*
	 * TODO look at implementation and separate subject decorator to allow more
	 * generic implementation.
	 */
	private String decorateSubject(String subject, int priority) {
		String priorityTag = priority == 1 ? "URGENT" : "";
		return String.format("%s %s  %s", "", priorityTag, subject);
	}

	/**
	 * Main method sending to multiple mail with cc
	 */
	private void send(List<String> to, List<String> cc, String from, String subject, String text,
			List<EmailAttachmentImpl> emailAttachmentList, EmailPriority priority, boolean isTxtHtml)
			throws MessagingException {
		MimeMessagePreparator preparator = message -> {
			MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE);
			helper.setFrom(from);
			helper.setTo(getList(to));
			if (cc != null && !cc.isEmpty()) {
				helper.setCc(getList(cc));
			}
			helper.setSubject(decorateSubject(subject, priority.getPriority()));
			helper.setPriority(priority.getPriority());
			helper.setText(text, isTxtHtml);
			if (!CommonUtil.isListEmpty(emailAttachmentList)) {
				addAttachments(helper, emailAttachmentList);
			}
		};
		configureJavaMailSender();
		emailSender.send(preparator);
	}

	private void addAttachments(MimeMessageHelper helper, List<EmailAttachmentImpl> emailAttachmentList) {
		emailAttachmentList.forEach(attachment -> {
			try {
				helper.addAttachment(attachment.getOriginalFileName(), attachment.getAttachmentDataSource());
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Main method sending mail
	 */
	private void send(String to, String cc, String from, String subject, String text, EmailAttachmentImpl attachment,
			EmailPriority priority, boolean isTxtHtml) throws MessagingException {
		MimeMessagePreparator preparator = message -> {
			MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE);
			helper.setFrom(from);
			helper.setCc(InternetAddress.parse(cc));
			helper.setTo(InternetAddress.parse(to));
			helper.setSubject(decorateSubject(subject, priority.getPriority()));
			helper.setPriority(priority.getPriority());
			helper.setText(text, isTxtHtml);
			if (attachment != null) {
				helper.addAttachment(attachment.getAttachmentDataSource().getName(),
						attachment.getAttachmentDataSource());
			}
		};
		configureJavaMailSender();
		emailSender.send(preparator);
	}

	/**
	 * Send simple email message with normal priorty
	 * 
	 * @param toList
	 * @param ccList
	 * @param from
	 * @param subject
	 * @param text
	 * @throws MessagingException
	 */
	public void send(List<String> to, List<String> cc, Map<String, byte[]> attachmentList, String from, String subject,
			String text, boolean isTxtHtml) throws MessagingException {
		List<EmailAttachmentImpl> emailAttachmentList = null;
		if (!attachmentList.isEmpty()) {
			emailAttachmentList = new ArrayList<EmailAttachmentImpl>();
			setAttachmentList(emailAttachmentList, attachmentList);
		}
		send(to, cc, from, subject, text, emailAttachmentList, EmailPriority.NORMAL_PRIORITY, isTxtHtml);
	}

	private void setAttachmentList(List<EmailAttachmentImpl> emailAttachmentList, Map<String, byte[]> attachmentList) {
		Iterator<Entry<String, byte[]>> it = attachmentList.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, byte[]> entry = (Map.Entry<String, byte[]>) it.next();
			emailAttachmentList
					.add(new EmailAttachmentImpl(new ByteArrayEmailAttachment(entry.getValue()), entry.getKey()));
		}
	}

	/**
	 * Send simple email message with normal priorty
	 * 
	 * @param to
	 * @param cc
	 * @param from
	 * @param subject
	 * @param text
	 * @throws MessagingException
	 */
	public void send(String to, String cc, String from, String subject, String text, boolean isTxtHtml)
			throws MessagingException {
		send(to, cc, from, subject, text, (EmailAttachmentImpl) null, EmailPriority.NORMAL_PRIORITY, isTxtHtml);
	}

	private InternetAddress[] getList(List<String> list) throws AddressException {
		if (list == null) {
			return null;
		}
		InternetAddress[] address = new InternetAddress[list.size()];
		for (int i = 0; i < list.size(); i++) {
			address[i] = new InternetAddress(list.get(i));
		}
		return address;
	}
	
	private void configureJavaMailSender() {
		emailSender.setHost(host);
		emailSender.setPort(Integer.parseInt(port));
		emailSender.setUsername(username);
		emailSender.setPassword(password);
		Properties props = new Properties();
		String starttls = CommonUtil.getEnvVariableByName(AppBaseConstants.ENVIRONMENT_EMAIL_STARTTLS);
		if (starttls == null) {
			starttls = Boolean.TRUE.toString();
		}
		props.put("mail.smtp.starttls.enable", starttls);
		emailSender.setJavaMailProperties(props);
	}

}
