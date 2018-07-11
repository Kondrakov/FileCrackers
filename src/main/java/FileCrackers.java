import org.dee.file.parsers.ExtractFromFileContainerParser;

import java.io.IOException;


public class FileCrackers {

    public static Boolean DEBUG = true;

    public static void main(String[] args) throws IOException {
        ExtractFromFileContainerParser parser = new ExtractFromFileContainerParser();

        if (DEBUG) {
            parser.extract("test.rcc","output", "png");
        } else {
            if (args.length == 0) {
                System.out.println("input format: input.* output type");
            } else {
                parser.extract(args[0], args[1], args[2]);
            }
        }
    }




}