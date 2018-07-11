package org.dee.file.parsers;

import org.dee.file.formats.FileFormat;
import org.dee.file.formats.FileFormats;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExtractFromFileContainerParser {

    public ExtractFromFileContainerParser() {
        //
    }

    public static String START = "start";
    public static String END = "end";



    private String pattern;
    private FileFormat fileFormat;

    public boolean extract(String filenameinput, String filenameoutput, String format)
            throws IOException, NullPointerException {

        fileFormat = FileFormats.getFileFormat(format);
        int patternBufferLength = fileFormat.getHeader().length > fileFormat.getEnding().length ?
                fileFormat.getHeader().length : fileFormat.getEnding().length;

        int outputIndex = 0;

        FileOutputStream fileOutputStream =
                new FileOutputStream(filenameoutput + outputIndex + fileFormat.extension());

        try
        {
            FileInputStream fileInputString = new FileInputStream(filenameinput);


            int[] patternBuffer = new int[patternBufferLength];

            int nextReadByte;
            int nextTestByte;

            Boolean keyChunkComplete;

            pattern = ExtractFromFileContainerParser.START;


            int indexReadTerminationPoint = 0;
            nextReadByte = fileInputString.read();
            while (fileInputString.available() > 0) {

                if (nextReadByte == currentPattern()[0]) {
                    patternBuffer[0] = nextReadByte;
                    keyChunkComplete = true;
                    indexReadTerminationPoint = 0;
                    for (int k = 1; k < currentPattern().length; ++k) {
                        nextTestByte = fileInputString.read();
                        indexReadTerminationPoint = k;
                        if (nextTestByte != currentPattern()[k]) {
                            keyChunkComplete = false;

                            nextReadByte = nextTestByte;
                            break;
                        } else {
                            patternBuffer[k] = nextTestByte;
                        }

                    }
                    if (keyChunkComplete) {

                        System.out.println();
                        for (int j = 0; j < patternBuffer.length; ++j) {
                            fileOutputStream.write(patternBuffer[j]);
                            // Header info:
                            //System.out.print((char) patternBuffer[j]);
                        }



                        if (pattern == ExtractFromFileContainerParser.END) {
                            fileOutputStream.close();
                            outputIndex++;
                            fileOutputStream =
                                    new FileOutputStream(filenameoutput + outputIndex + fileFormat.extension());
                            System.out.println("bytes remaining: " + fileInputString.available());
                            System.out.print(filenameoutput + (outputIndex - 1) + fileFormat.extension() + " complete.. ");
                        }

                        if (pattern == ExtractFromFileContainerParser.START) {
                            pattern = ExtractFromFileContainerParser.END;
                        } else if (pattern == ExtractFromFileContainerParser.END) {
                            pattern = ExtractFromFileContainerParser.START;
                        }


                        nextReadByte = fileInputString.read();
                    } else {
                        for (int j = 0; j < indexReadTerminationPoint; ++j) {
                            // Write any bytes between header and ending only if we searching end of file now
                            if (pattern == ExtractFromFileContainerParser.END) {
                                fileOutputStream.write(patternBuffer[j]);
                            }
                        }
                        patternBuffer = new int[currentPattern().length];
                    }
                } else {
                    // Write any bytes between header and ending only if we searching end of file now
                    if (pattern == ExtractFromFileContainerParser.END) {
                        fileOutputStream.write(nextReadByte);
                    }
                    patternBuffer = new int[currentPattern().length];
                    nextReadByte = fileInputString.read();
                }

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            fileOutputStream.close();
        }


        return true;
    }

    private int[] currentPattern() {
        if (pattern == ExtractFromFileContainerParser.START) {
            return fileFormat.getHeader();
        } else if (pattern == ExtractFromFileContainerParser.END) {
            return fileFormat.getEnding();
        }
        return null;
    }
}
