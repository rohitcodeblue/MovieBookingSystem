package com.project.moviebooking.email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;

import lombok.Getter;
import lombok.Setter;

/**
 * Used to attach an object to an email message.
 *
 */
public class EmailAttachmentImpl implements EmailAttachment {
	private EmailAttachment emailAttachmentType;
	@Getter
	@Setter
	private String originalFileName;

	public EmailAttachmentImpl(EmailAttachment emailAttachmentType) {
		this.emailAttachmentType = emailAttachmentType;
	}

	public EmailAttachmentImpl(EmailAttachment emailAttachmentType, String originalFileName) {
		this.emailAttachmentType = emailAttachmentType;
		this.originalFileName = originalFileName;
	}

	@Override
	public DataSource getAttachmentDataSource() {
		return emailAttachmentType == null ? null : emailAttachmentType.getAttachmentDataSource();
	}

	public void attach(Multipart multipart) throws MessagingException {
		// Does the attached file actually exist
		DataSource dataSource = getAttachmentDataSource();
		if (dataSource != null) {
			MimeBodyPart part2 = new MimeBodyPart();
			part2.setDataHandler(new DataHandler(dataSource));
			part2.setFileName(dataSource.getName());
			multipart.addBodyPart(part2);
		}
	}

}
