package org.dee.file.formats;

public class FileFormats {

    public static String PNG = "png";

    public static FileFormat getFileFormat(String requestFormat) {
        if (requestFormat.equals(PNG)) {
            return new PNGFormat();
        } else {
            System.out.println("File format not supported.");
            return null;
        }

    }
}
