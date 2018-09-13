package com.wcx.springboot.demo.java.file;

import java.io.File;

public class FilePermission {
    public static void main(String[] args) {
        // creating a file instance
        File file = new File("d:\\a.txt");

        // check if the file exists
        boolean exists = file.exists();
        if(exists)
        {
            // printing the permissions associated with the file
            System.out.println("Executable: " + file.canExecute());
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: "+ file.canWrite());


            // changing the file permissions
            file.setExecutable(true);
            file.setReadable(true);
            file.setWritable(false);
            System.out.println("File permissions changed.");

            // printing the permissions associated with the file currently
            System.out.println("Executable: " + file.canExecute());
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: "+ file.canWrite());
        }
        else
        {
            System.out.println("File not found.");
        }
    }
}
