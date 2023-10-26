package com.project.moviebooking.email;

import java.io.File;

import javax.activation.DataSource;
import javax.activation.FileDataSource;

/**
 * An email attachment that represents an actual file.
 * @author BRaper
 *
 */
public class FileEmailAttachment implements EmailAttachment {
    private String fileName;
    
    public FileEmailAttachment(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public DataSource getAttachmentDataSource() {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            return new FileDataSource(fileName);
        }
        return null;
    }

}
