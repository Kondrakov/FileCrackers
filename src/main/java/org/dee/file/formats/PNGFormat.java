package org.dee.file.formats;

public class PNGFormat implements FileFormat {

    private int header[];
    private int ending[];
    private int ending_extended[];
    private String extension;



    public PNGFormat() {
        this.header = new int[] { 0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a };
        this.ending = new int[] { 0x49, 0x45, 0x4e, 0x44 };
        this.ending_extended = new int[] { 0x49, 0x45, 0x4e, 0x44, 0xae, 0x42, 0x60, 0x82 };
        this.extension = ".png";
    }

    public int[] getHeader() {
        return this.header;
    }

    public int[] getEnding() {
        return this.ending;
    }

    public int[] getEndingExtended() {
        return this.ending_extended;
    }

    public String extension() {
        return this.extension;
    }
}
