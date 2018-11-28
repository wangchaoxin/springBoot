package com.wcx.springboot.demo.java.http;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.rabbitmq.client.ConnectionFactory.DEFAULT_PASS;
import static com.rabbitmq.client.ConnectionFactory.DEFAULT_USER;

public class HttpClientBasicAuthentication {
    /**
     * 2. Basic Authentication with the API
     * Let’s start with the standard way of configuring Basic Authentication on the HttpClient – via a CredentialsProvider
     * @throws IOException
     */
    @Test
    public void basicAuth() throws IOException {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials("user1", "user1Pass");
        provider.setCredentials(AuthScope.ANY, credentials);

        HttpClient client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();

        HttpResponse response = client.execute(
                new HttpGet(""));
        int statusCode = response.getStatusLine()
                .getStatusCode();

//        assertThat(statusCode, equalTo(HttpStatus.SC_OK));
        /*The entire Client-Server communication is now clear:
            the Client sends the HTTP Request with no credentials
            the Server sends back a challenge
            the Client negotiates and identifies the right authentication scheme
            the Client sends a second Request, this time with credentials*/
    }

    /**
     * Out of the box, the HttpClient doesn’t do preemptive authentication – this has to be an explicit decision made by the client.
     * First, we need to create the HttpContext – pre-populating it with an authentication cache with the right type of authentication scheme pre-selected. This will mean that the negotiation from the previous example is no longer necessary – Basic Authentication is already chosen:
     */
    @Test
    public void PreemptiveAuth() throws IOException {
        HttpHost targetHost = new HttpHost("localhost", 8080, "http");
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("username", "password"));

        AuthCache authCache = new BasicAuthCache();
        authCache.put(targetHost, new BasicScheme());

        // Add AuthCache to the execution context
        final HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);

        //Now we can use the client with the new context and send the pre-authentication request:
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(
                new HttpGet("url"), context);

        int statusCode = response.getStatusLine().getStatusCode();
//        assertThat(statusCode, equalTo(HttpStatus.SC_OK));

       /* Everything looks OK:

        the “Basic Authentication” scheme is pre-selected
        the Request is sent with the Authorization header
        the Server responds with a 200 OK
        Authentication succeeds*/
    }

    /**
     * Preemptive Basic Authentication basically means pre-sending the Authorization header.
     * So – instead of going through the rather complex previous example to set it up, we can take control of this header
     * and construct it by hand:
     */
    @Test
    public void AuthWithHttpHeaders() throws IOException {
        HttpGet request = new HttpGet("url");
        String auth = DEFAULT_USER + ":" + DEFAULT_PASS;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
//        assertThat(statusCode, equalTo(HttpStatus.SC_OK));
    }
}
