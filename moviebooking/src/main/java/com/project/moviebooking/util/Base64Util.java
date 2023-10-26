package com.project.moviebooking.util;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class Base64Util {

    /*
	 * Considered base64String as data:image/png;base64,base64EncidedData
     */
    public String getBase64EncodedData(String base64String) {
        String[] strings = base64String.split(",");

        if (strings.length > 0) {
            return strings[1];
        }
        //throw new ValidationRestException("Improper bas64 Format = " + base64String, "");
        //TODO LOG ERROR PROPERLY
        return null;
    }

    public byte[] decode(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

    public byte[] convert(String base64String) {
        return decode(getBase64EncodedData(base64String));
    }

    public String getExtension(String base64String) {
        String[] strings = base64String.split(",");

        switch (strings[0]) {// check image's extension
            case "data:image/jpeg;base64":
                return "jpeg";
            case "data:image/png;base64":
                return "png";
            case "data:image/gif;base64":
                return "gif";
            case "data:image/svg+xml;base64":
                return "svg";
            case "data:	image/tiff;base64":
                return "tiff";
            case "data:application/pdf;base64":
                return "pdf";
            case "data:text/csv;base64":
                return "csv";
            case "data:video/webm;base64":
                return "web";
            case "data:application/vnd.ms-excel;base64":
                return "xls";
            case "data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64":
                return "xlsx";
            case "data:application/msword;base64":
                return "doc";
            case "data:application/vnd.openxmlformats-officedocument.wordprocessing.document;base64":
            case "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64":
                return "docx";
            case "data:video/3gpp;base64":
                return "3gp";
            case "data:video/3gpp2;base64":
                return "3g2";
            case "data:video/x-msvideo;base64":
                return "avi";
            case "data:video/mpeg;base64":
                return "mpeg";
            default:// should write cases for more images types
                return "jpg";
        }
    }

    public String encode(String string) {
        return new String(Base64.getEncoder().encode(string.getBytes()));
    }

    public String fileToBase64String(String filePath) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
