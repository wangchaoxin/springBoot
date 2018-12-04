package com.wcx.springboot.demo.java.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PostMethod {

    @Test
    /**
     * 2. Basic POST
     * do a POST with two parameters – “username” and “password“:
     */
    public void whenPostRequestUsingHttpClient_thenCorrect()
            throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.example.com");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", "John"));
        params.add(new BasicNameValuePair("password", "pass"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }
    @Test
    /**
     * 3. POST with Authorization
     * do a POST with Authentication credentials
     */
    public void whenPostRequestWithAuthorizationUsingHttpClient_thenCorrect()
            throws ClientProtocolException, IOException, AuthenticationException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.example.com");

        httpPost.setEntity(new StringEntity("testSoftReference post"));
        UsernamePasswordCredentials creds
                = new UsernamePasswordCredentials("John", "pass");
        httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }
    @Test
    /**
     * 4. POST with JSON
     * send a POST request with a JSON body using the HttpClient.
     */
    public void whenPostJsonUsingHttpClient_thenCorrect()
            throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.example.com");

        //Note how we’re using the StringEntity to set the body of the request.
        //We are also setting the ContentType header to application/json to give the server the necessary
        // information about the representation of the content we’re sending.
        String json = "{\"id\":1,\"name\":\"John\"}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

    /**
     *5. POST with the HttpClient Fluent API
     * @throws ClientProtocolException
     * @throws IOException
     */
    @Test
    public void whenPostFormUsingHttpClientFluentAPI_thenCorrect()
            throws ClientProtocolException, IOException {
        HttpResponse response =
                Request.Post("http://www.example.com").bodyForm(
                        Form.form().add("username", "John").add("password", "pass").build())
                        .execute().returnResponse();

        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
    }

    /**
     *
     * @throws ClientProtocolException
     * @throws IOException
     */
    @Test
    public void whenSendMultipartRequestUsingHttpClient_thenCorrect()
            throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.example.com");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("username", "John");
        builder.addTextBody("password", "pass");
        builder.addBinaryBody("file", new File("testSoftReference.txt"),
                ContentType.APPLICATION_OCTET_STREAM, "file.ext");

        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

    /**
     * 6. POST Multipart Request
     * @throws ClientProtocolException
     * @throws IOException
     */
    @Test
    public void whenUploadFileUsingHttpClient_thenCorrect()
            throws ClientProtocolException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://www.example.com");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", new File("testSoftReference.txt"),
                ContentType.APPLICATION_OCTET_STREAM, "file.ext");
        HttpEntity multipart = builder.build();

        httpPost.setEntity(multipart);

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

}
