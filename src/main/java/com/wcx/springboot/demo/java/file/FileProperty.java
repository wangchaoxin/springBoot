package com.wcx.springboot.demo.java.file;

import java.io.File;

public class FileProperty {
    public static void main(String[] args) {

        //pass the filename or directory name to File object
        File f = new File("d:\\a.txt");

        //apply File class methods on File object
        System.out.println("File name :"+f.getName());
        System.out.println("Path: "+f.getPath());
        System.out.println("Absolute path:" +f.getAbsolutePath());
        System.out.println("Parent:"+f.getParent());
        System.out.println("Exists :"+f.exists());
        if(f.exists())
        {
            System.out.println("Is writeable:"+f.canWrite());
            System.out.println("Is readable"+f.canRead());
            System.out.println("Is a directory:"+f.isDirectory());
            System.out.println("File Size in bytes "+f.length());
        }
        System.out.println("Is a file:"+f.isFile());
        File[] files = f.listFiles();   //get list files
        String[] list = f.list();     //get string files
    }
}
