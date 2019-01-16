package com.wcx.springboot.demo.midware.redis.jedis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWith {


    public static void main(String[] args) throws IOException {
        //The try-with-resources statement is a try statement that declares one or more resources.
        // A resource is an object that must be closed after the program is finished with it.
        // The try-with-resources statement ensures that each resource is closed at the end of the statement.
        // Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable,
        // can be used as a resource.
        //The following example reads the first line from a file. It uses an instance of BufferedReader to read data from the file. java.io.BufferedReader is a resource that must be closed after the program is finished with it:

        try (BufferedReader br =
                     new BufferedReader(new FileReader(""))) {
            br.readLine();
        }

    }
}
