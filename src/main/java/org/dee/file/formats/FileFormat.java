package org.dee.file.formats;

public interface FileFormat {
    int[] getHeader();
    int[] getEnding();
    String extension();
}
