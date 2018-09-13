package com.wcx.springboot.demo.java.file;

import java.io.*;

public class FileRead {

    public static final String FILE_PATH = "D:\\a.txt";

    public static void main(String[] args) {
        try {
//            read1();
            read2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * buffered reader,: This method reads text from a character-input stream.
     * It does buffering for efficient reading of characters, arrays, and lines.
     */
    private static void read1() throws IOException {
        // We need to provide file path as the parameter:
        // double backquote is to avoid compiler interpret words
        // like \test as \t (ie. as a escape sequence)
        File file = new File(FILE_PATH);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            System.out.println(st);
    }

    /**
     * file reader,Convenience class for reading character files.
     * The constructors of this class assume that the default character encoding and the default byte-buffer size are appropriate
     */
    private static void read2() throws IOException {
        // pass the path to the file as a parameter
        FileReader fr = new FileReader(FILE_PATH);

        int i;
        while ((i = fr.read()) != -1)
            System.out.print((char) i);
    }
}


