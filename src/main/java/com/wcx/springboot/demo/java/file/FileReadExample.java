package com.wcx.springboot.demo.java.file;

import com.google.inject.matcher.Matchers;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * we will see how we can read a file from a classpath,
 * URL or inside a JAR file, using standard Java classes.
 */
public class FileReadExample {
    /**
     * Tests will share a common readFromInputStream method that transforms an InputStream
     * to String for easier asserting of results:
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    /**
     * 3. Read File from Classpath
     * This section explains how to read a file that is available on a classpath.
     * We will read the “fileTest.txt” available under src/main/resources:
     *
     * @throws IOException
     */
    @Test
    public void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() throws IOException {
        String expectedData = "Hello World from fileTest.txt!!!";

        Class clazz = FileReadExample.class;
        InputStream inputStream = clazz.getResourceAsStream("/fileTest.txt");
        String data = readFromInputStream(inputStream);

//        Assert.assertThat(data, containsString(expectedData));
    }

    /**
     * In the above code snippet, we used the current class to load a file using getResourceAsStream method and passed the absolute path of the file to load.
     * The same method is available on a ClassLoader instance as well:
     *
     * We obtain the classLoader of the current class using getClass().getClassLoader().
     * The main difference is that when using the getResourceAsStream on a ClassLoader instance, the path is treated as absolute starting from the root of the classpath.
     *
     * When used against a Class instance, the path could be relative to the package, or an absolute path, which is hinted by the leading slash.
     * @throws IOException
     */
    public void readFromClassPath() throws IOException {
        InputStream inputStream = null;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
             inputStream = classLoader.getResourceAsStream("fileTest.txt");
            String data = readFromInputStream(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * 5. Read File with JDK8
     * JDK8 offers lines() method inside the Files class. It returns a Stream of String elements.
     * Let’s look at an example of how to read data into bytes and decode using UTF-8 charset:
     *
     * Using Stream with IO channels like file operations, we need to close the stream explicitly using close() method.
     * As we can see, the Files API offers another easy way to read the file contents into a String.
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test
    public void givenFilePath_whenUsingFilesLines_thenFileData() throws URISyntaxException, IOException {
        String expectedData = "Hello World from fileTest.txt!!!";

        Path path = Paths.get(getClass().getClassLoader()
                .getResource("fileTest.txt").toURI());

        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();

//        Assert.assertEquals(expectedData, data.trim());
    }

    /**
     * 6. Read File with FileUtils
     * Here we pass the File object to the method readFileToString() of FileUtils class.
     * This utility class manages to load the content without the necessity of writing any boilerplate
     * code to create an InputStream instance and read data.
     */
    @Test
    public void givenFileName_whenUsingFileUtils_thenFileData() throws IOException {
        String expectedData = "Hello World from fileTest.txt!!!";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("fileTest.txt").getFile());
        String data = FileUtils.readFileToString(file, "UTF-8");
//        Assert.assertEquals(expectedData, data.trim());
    }

    /**
     * 7. Read File with IOUtils
     * Here we pass the FileInputStream object to the method toString() of IOUtils class.
     * This utility class manages to load the content without the necessity of writing any boilerplate code
     * to create an InputStream instance and read data.
     * @throws IOException
     */
    @Test
    public void givenFileName_whenUsingIOUtils_thenFileData() throws IOException {
        String expectedData = "This is a content of the file";

        FileInputStream fis = new FileInputStream("src/testSoftReference/resources/fileToRead.txt");
        String data = IOUtils.toString(fis, "UTF-8");

//        assertEquals(expectedData, data.trim());
    }

    /**
     * 8. Read Content from URL
     *
     */
    @Test
    public void givenURLName_whenUsingURL_thenFileData() throws IOException {
        String expectedData = "Baeldung";

        URL urlObject = new URL("/");
        URLConnection urlConnection = urlObject.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        String data = readFromInputStream(inputStream);

        Assert.assertThat(data, containsString(expectedData));
    }

    /**
     * To read a file which is located inside a JAR file, we will need a JAR with a file inside it.
     * For our example we will read “LICENSE.txt” from the “hamcrest-library-1.3.jar” file:
     *
     * Here we want to load LICENSE.txt that resides in Hamcrest library, so we will use the Matcher’s class that helps to get a resource.
     * The same file can be loaded using the classloader too.
     * @throws IOException
     */
    @Test
    public void givenFileName_whenUsingJarFile_thenFileData() throws IOException {
        String expectedData = "BSD License";

        Class clazz = Matchers.class;
        InputStream inputStream = clazz.getResourceAsStream("/LICENSE.txt");
        String data = readFromInputStream(inputStream);

        Assert.assertThat(data, containsString(expectedData));
    }
}
