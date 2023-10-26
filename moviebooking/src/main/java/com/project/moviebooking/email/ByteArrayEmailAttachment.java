package com.project.moviebooking.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * An email attachment that is a Textual byte array.
 *
 */
public class ByteArrayEmailAttachment implements EmailAttachment {
    private static Logger logger = LogManager.getLogger(ByteArrayEmailAttachment.class);
    private byte[] attachment;
    
    public ByteArrayEmailAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
    
    @Override
    public DataSource getAttachmentDataSource() {
        if (attachment != null && attachment.length > 0) {
            try(ByteArrayInputStream bais = new ByteArrayInputStream(attachment)) {
                return new ByteArrayDataSource(bais, "application/pdf");
            } catch (IOException e) {
                logger.warn("Could not process ByteArrayAttachment.", e);
            }
        }
        return null;
    }
    
}
