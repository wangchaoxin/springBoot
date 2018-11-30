package com.wcx.springboot.demo.java.file;

import com.google.common.io.ByteSource;
import com.google.common.io.CharStreams;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class InputStreamToString {

    /**
     * 2. Converting with Guava
     * Let’s start with a Guava example – leveraging the ByteSource functionality:
     *
     * Let’s go over the steps:
     *
     * first – we wrap our InputStream a ByteSource – and as far as I’m aware, this is the easiest way to do so
     * then – we view our ByteSource as a CharSource with a UTF8 charset.
     * finally – we use the CharSource to read it as a String.
     * @throws IOException
     */
    @Test
    public void givenUsingGuava_whenConvertingAnInputStreamToAString_thenCorrect()
            throws IOException {
        String originalString = randomAlphabetic(8);
        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream openStream() throws IOException {
                return inputStream;
            }
        };

        String text = byteSource.asCharSource(Charsets.UTF_8).read();

//        assertThat(text, equalTo(originalString));
    }

    /**
     * A simpler way of doing the conversion with Guava, but the stream needs to be explicitly closed; luckily,
     * we can simply use the try-with-resources syntax to take care of that:
     * @throws IOException
     */
    @Test
    public void givenUsingGuavaAndJava7_whenConvertingAnInputStreamToAString_thenCorrect()
            throws IOException {
        String originalString = randomAlphabetic(8);
        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        String text = null;
        try (final Reader reader = new InputStreamReader(inputStream)) {
            text = CharStreams.toString(reader);
        }
//        assertThat(text, equalTo(originalString));
    }

    /**
     * 3. Converting with Apache Commons IO
     * An important caveat here is that – as opposed to Guava –
     * neither of these examples will close the InputStream –
     * which is why I personally prefer the Guava solution.
     * @throws IOException
     */
    @Test
    public void givenUsingCommonsIo_whenConvertingAnInputStreamToAString_thenCorrect()
            throws IOException {
        String originalString = randomAlphabetic(8);
        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
//        assertThat(text, equalTo(originalString));
    }
    //We can also use a StringWriter to do the conversion:
    @Test
    public void givenUsingCommonsIoWithCopy_whenConvertingAnInputStreamToAString_thenCorrect()
            throws IOException {
        String originalString = randomAlphabetic(8);
        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        StringWriter writer = new StringWriter();
        String encoding = StandardCharsets.UTF_8.name();
        IOUtils.copy(inputStream, writer, encoding);

//        assertThat(writer.toString(), equalTo(originalString));
    }

    /**
     * 4. Converting with Java – InputStream
     * Let’s look now at a lower level approach using plain Java – an InputStream and a simple StringBuilder:
     * @throws IOException
     */
    @Test
    public void givenUsingJava5_whenConvertingAnInputStreamToAString_thenCorrect()
            throws IOException {
        String originalString = randomAlphabetic(100);
        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
//        assertEquals(textBuilder.toString(), originalString);
    }

    /**
     * 5. Converting with Java and a Scanner
     * Note that the InputStream is going to be closed by the closing of the Scanner.
     * The only reason this is a Java 7 example, and not a Java 5 one is the use of the try-with-resources statement – turning that into a standard try-finally block will compile just fine with Java 5.
     * @throws IOException
     */
    @Test
    public void givenUsingJava7_whenConvertingAnInputStreamToAString_thenCorrect()
            throws IOException {
        String originalString = randomAlphabetic(8);
        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        String text = null;
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            text = scanner.useDelimiter("\\A").next();
        }

//        assertThat(text, equalTo(originalString));
    }

    /**
     * 6. Converting Using ByteArrayOutputStream
     * In this example, first the InputStream is converted to a ByteArrayOutputStream by reading and writing byte blocks,
     * then the OutputStream is transformed to a byte array, which is used to create a String.
     * @throws IOException
     */
    @Test
    public final void givenUsingPlainJava_whenConvertingAnInputStreamToString_thenCorrect()
            throws IOException {
        String originalString = randomAlphabetic(8);
        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        byte[] byteArray = buffer.toByteArray();

        String text = new String(byteArray, StandardCharsets.UTF_8);
//        assertThat(text, equalTo(originalString));
    }

    /**
     * 7. Converting with java.nio
     * Another solution is to copy the content of the InputStream to a file, then convert this to a String:
     *
     * Here, we’re using the java.nio.file.Files class to create a temporary file, as well as copy the content of the InputStream to the file. Then,
     * the same class is used to convert the file content to a String with the readAllBytes() method.
     * @throws IOException
     */
    @Test
    public final void givenUsingTempFile_whenConvertingAnInputStreamToAString_thenCorrect()
            throws IOException {
        String originalString = randomAlphabetic(100);
        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        Path tempFile = Files.createTempDirectory("").resolve(UUID.randomUUID().toString() + ".tmp");
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        String result = new String(Files.readAllBytes(tempFile));

//        assertThat(result, equalTo(originalString));
    }
}
