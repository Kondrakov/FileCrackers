# FileCrackers

Library for file extraction from file-containers.
Now it can parse and extract only png-files from any file containers if png-files not encoded.
Console mode supported now only.

Use-case example:
You have *.rcc file and you know this file contains *.png files for example
(<b>note:</b> you can extract standard png's from any container).
With FileCrackes util you can extract png's from rcc container easily:

1. Java Runtime Environment have to be installed (version 1.8 supported) and have to be accessible from command line.
If you not sure, run the console and input java -version. It will show current java version installed. Must be 1.8+.
2. Run *.jar file of converter with -jar command for example in git console (Windows):

$ java -jar d:/jars/FileCrackers.jar d\\:\\\jars\\\test.rcc d:\\\output\\\out png

In this example "output" folder must exist before extraction, "out" - base name for extracted files, "png" - mandatory 
(can be only png for now).
d:/jars/FileCrackers.jar - location of extractor, can be relative;
d\\:\\\jars\\\test.rcc - location of source container to extract, can be relative;
d:\\\output\\\out - location of output files and basename, can be relative;
png - type of files to find in container.
