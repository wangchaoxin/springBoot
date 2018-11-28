package com.wcx.springboot.demo.java.http.basic;

import com.google.common.collect.Lists;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Basic {
    private static final String SAMPLE_URL = "www.baidu.com";

    @Test
    /**
     * 获取status code
     * 1.Retrieve the Status Code from the Http Response
     */
    public void givenGetRequestExecuted_whenAnalyzingTheResponse_thenCorrectStatusCode()
            throws ClientProtocolException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet(SAMPLE_URL));
        int statusCode = response.getStatusLine().getStatusCode();
        assertThat(statusCode, equalTo(HttpStatus.SC_OK));
    }

    @Test
    /**
     * 设置timeout
     * 2.The HttpClient comes with a lot of configuration parameters – and all of these can be set in a generic, map-like manner.
     * There are 3 timeout parameters to configure:
     */
    public void timeOut() throws IOException {
        //1.Configure Timeouts via raw String Parameters
        DefaultHttpClient httpClient = new DefaultHttpClient();
        int timeout = 5; // seconds
        HttpParams httpParams = httpClient.getParams();
        httpParams.setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
        httpParams.setParameter(
                CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);
        // httpParams.setParameter(
        //   ClientPNames.CONN_MANAGER_TIMEOUT, new Long(timeout * 1000));

        //2.Configure Timeouts using the new 4.3. Builder
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        CloseableHttpClient client =
                HttpClientBuilder.create().setDefaultRequestConfig(config).build();

       /* 5. Timeout Properties Explained
        Now, let’s explain what these various types of timeouts mean:

        the Connection Timeout (http.connection.timeout) – the time to establish the connection with the remote host
        the Socket Timeout (http.socket.timeout) – the time waiting for data – after the connection was established; maximum time of inactivity between two data packets
        the Connection Manager Timeout (http.connection-manager.timeout) – the time to wait for a connection from the connection manager/pool
        The first two parameters – the connection and socket timeouts – are the most important, but setting a timeout for obtaining a connection is definitely important in high load scenarios, which is why the third parameter shouldn’t be ignored.*/

        //6. Using the HttpClient
        /*With the previously defined client, the connection to the host will time out in 5 seconds, and if the connection is established but no data is received, the timeout will also be 5 additional seconds.
        Note that the connection timeout will result in an org.apache.http.conn.ConnectTimeoutException being thrown, while socket timeout will result in a java.net.SocketTimeoutException.
        After being configured, the client can now be used to perform HTTP requests:*/
        HttpGet getMethod = new HttpGet("http://host:8080/path");
        HttpResponse response = httpClient.execute(getMethod);
        System.out.println(
                "HTTP Status of response: " + response.getStatusLine().getStatusCode());

       /* 7. Hard Timeout
        While setting timeouts on establishing the HTTP connection and not receiving data is very useful, sometimes we need to set a hard timeout for the entire request.
        For example, the download of a potentially large file fits into this category – in this case, the connection may be successfully established, data may be consistently coming through, but we still need to ensure that the operation doesn’t go over some specific time threshold.
        HttpClient doesn’t have any configuration that allows us to set an overall timeout for a request; it does, however, provide abort functionality for requests, so we can leverage that mechanism to implement a simple timeout mechanism:*/
        HttpGet getMethod1 = new HttpGet(
                "http://localhost:8080/spring-security-rest-template/api/bars/1");

        int hardTimeout = 5; // seconds
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (getMethod1 != null) {
                    getMethod1.abort();
                }
            }
        };
        new Timer(true).schedule(task, hardTimeout * 1000);

        HttpResponse response1 = httpClient.execute(getMethod);
        System.out.println(
                "HTTP Status of response: " + response1.getStatusLine().getStatusCode());
    }
    @Test
    /**
     * 取消request
     * 3.This quick tutorial shows how to cancel a HTTP Request with the Apache HttpClient 4.
     * This is especially useful for potentially long running requests, or large download files that would otherwise
     * unnecessarily consume bandwidth and connections.
     */
    public void whenRequestIsCanceled_thenCorrect()
            throws ClientProtocolException, IOException {
        HttpClient instance = HttpClients.custom().build();
        HttpGet request = new HttpGet(SAMPLE_URL);
        HttpResponse response = instance.execute(request);
        //This will make sure that the client doesn’t have to consume the entire body of the request to release the connection:
        try {
            System.out.println(response.getStatusLine());
            request.abort();
        } finally {
        }
        //This article illustrated how to abort an ongoing request with the HTTP client.
        // Another option to stop long running requests if to make sure that they will time out.
    }
    @Test
    /**
     * 禁止redirect
     * 4.In this article I will show how to configure the Apache HttpClient 4 to stop following redirects.
     * By default, following the HTTP Spec, the HttpClient will automatically follow redirects.
     * For some usecases, that may be perfectly fine, but there are certainly usecases where that’s not desired – and we’ll now look at how to change that default behavior and stop following redirects.
     */
    public void givenRedirectsAreDisabled_whenConsumingUrlWhichRedirects_thenNotRedirected()
            throws ClientProtocolException, IOException {
        //before 4.3
        DefaultHttpClient instance = new DefaultHttpClient();

        HttpParams params = new BasicHttpParams();
        params.setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        // HttpClientParams.setRedirecting(params, false); // alternative

        HttpGet httpGet = new HttpGet("http://t.co/I5YYd9tddw");
        httpGet.setParams(params);
        CloseableHttpResponse response = instance.execute(httpGet);

        assertThat(response.getStatusLine().getStatusCode(), equalTo(301));
        //after 4.3
        HttpClient instance1 = HttpClientBuilder.create().disableRedirectHandling().build();
        HttpResponse response1 = instance.execute(new HttpGet("http://t.co/I5YYd9tddw"));

        assertThat(response.getStatusLine().getStatusCode(), equalTo(301));
    }
    @Test
    /**
     * 设置header
     *  set a custom header with the HttpClient.
     *  illustrated how to add an HTTP header to one or all requests sent via the Apache HttpClient.
     */
    public void setHeader() throws IOException {
        //2. Set Header on Request – Before 4.3
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(SAMPLE_URL);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        client.execute(request);

        //3.Set Header on Request – 4.3 and above
        HttpClient client1 = HttpClients.custom().build();
        HttpUriRequest request1 =    RequestBuilder.get()
                .setUri(SAMPLE_URL)
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
        client.execute(request);

        //4. Set Default Header on the Client – 4.3 and above
        //Instead of setting the Header on each and every request, you can also configure it as a default header on the Client itself:
        Header header = new BasicHeader(
                HttpHeaders.CONTENT_TYPE, "application/json");
        List<Header> headers = Lists.newArrayList(header);
        HttpClient client2 = HttpClients.custom()
                .setDefaultHeaders(headers).build();
        HttpUriRequest request2 = RequestBuilder.get()
                .setUri(SAMPLE_URL).build();
        client.execute(request);

    }
}
