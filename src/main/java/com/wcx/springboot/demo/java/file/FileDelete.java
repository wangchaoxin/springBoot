package com.wcx.springboot.demo.java.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class FileDelete {
    public static void main(String[] args) {
        delete1();
    }

    /**
     * file.delete
     */
    private static void delete1() {
        File file = new File("C:\\Users\\Mayank\\Desktop\\1.txt");
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
    }

    /**
     * This method deletes a file if it exists.
     * It also deletes a directory mentioned in the path only if the directory is not empty.
     */
    private static void delete2() {
        try
        { Files.deleteIfExists(Paths.get("D:\\b.txt"));
        }
        catch(NoSuchFileException e)
        {
            System.out.println("No such file/directory exists");
        }
        catch(DirectoryNotEmptyException e)
        {
            System.out.println("Directory is not empty.");
        }
        catch(IOException e)
        {
            System.out.println("Invalid permissions.");
        }

        System.out.println("Deletion successful.");
    }

}
