package com.wcx.springboot.demo.java.http;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;
import java.util.concurrent.Future;

public class AsyncClient {
    /**
     * 2. Simple Example
     * @throws Exception
     */
    @Test
    public void whenUseHttpAsyncClient_thenCorrect() throws Exception {
        CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
        //Note how we need to start the async client before using it
        client.start();
        HttpGet request = new HttpGet("http://www.google.com");

        Future<HttpResponse> future = client.execute(request, null);
        HttpResponse response = future.get();
//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

    /**
     * 3. Multi-Threading with HttpAsyncClient
     * Now – let’s see how to use HttpAsyncClient to execute multiple requests simultaneously.
     * In the following example – we send three GET requests to three different host using HttpAsyncClient and PoolingNHttpClientConnectionManager:
     * @throws Exception
     */
    @Test
    public void whenUseMultipleHttpAsyncClient_thenCorrect() throws Exception {
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();
        PoolingNHttpClientConnectionManager cm =
                new PoolingNHttpClientConnectionManager(ioReactor);
        CloseableHttpAsyncClient client =
                HttpAsyncClients.custom().setConnectionManager(cm).build();
        client.start();

        String[] toGet = {
                "http://www.google.com/",
                "http://www.apache.org/",
                "http://www.bing.com/"
        };

        GetThread[] threads = new GetThread[toGet.length];
        for (int i = 0; i < threads.length; i++) {
            HttpGet request = new HttpGet(toGet[i]);
            threads[i] = new GetThread(client, request);
        }

        for (GetThread thread : threads) {
            thread.start();
        }
        for (GetThread thread : threads) {
            thread.join();
        }
    }

    /**
     * 4. Proxy with HttpAsyncClient
     * Next – let’s see how to set up and use a proxy with the HttpAsyncClient.
     * In the following example – we send a HTTP GET request over proxy:
     * @throws Exception
     */
    @Test
    public void whenUseProxyWithHttpClient_thenCorrect() throws Exception {
        CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
        client.start();

        HttpHost proxy = new HttpHost("74.50.126.248", 3127);
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        HttpGet request = new HttpGet("https://issues.apache.org/");
        request.setConfig(config);

        Future<HttpResponse> future = client.execute(request, null);
        HttpResponse response = future.get();

//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

    /**
     * 5. SSL Certificate with HttpAsyncClient
     * Now – let’s see how to use a SSL Certificate with HttpAsyncClient.
     * In the following example – we configure HttpAsyncClient to accept all certificates:
     * @throws Exception
     */
    @Test
    public void whenUseSSLWithHttpAsyncClient_thenCorrect() throws Exception {
        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] certificate, String authType) {
                return true;
            }
        };
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy).build();

        CloseableHttpAsyncClient client = HttpAsyncClients.custom()
                .setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                .setSSLContext(sslContext).build();
        client.start();

        HttpGet request = new HttpGet("https://mms.nw.ru/");
        Future<HttpResponse> future = client.execute(request, null);
        HttpResponse response = future.get();

//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

    /**
     * 6. Cookies with HttpAsyncClient
     * Next – let’s see how to use cookies with HttpAsyncClient.
     *
     * In the following example – we set a cookie value before sending the request:
     * @throws Exception
     */
    @Test
    public void whenUseCookiesWithHttpAsyncClient_thenCorrect() throws Exception {
        BasicCookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", "1234");
        cookie.setDomain(".github.com");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);

        CloseableHttpAsyncClient client = HttpAsyncClients.custom().build();
        client.start();

        HttpGet request = new HttpGet("http://www.github.com");
        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        Future<HttpResponse> future = client.execute(request, localContext, null);
        HttpResponse response = future.get();

//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

    /**
     * 7. Authentication with HttpAsyncClient
     * Next – let’s see how to use authentication with HttpAsyncClient.
     * In the following example – we use the CredentialsProvider to access a host through basic authentication:
     * @throws Exception
     */
    @Test
    public void whenUseAuthenticationWithHttpAsyncClient_thenCorrect() throws Exception {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials creds = new UsernamePasswordCredentials("user", "pass");
        provider.setCredentials(AuthScope.ANY, creds);

        CloseableHttpAsyncClient client =
                HttpAsyncClients.custom().setDefaultCredentialsProvider(provider).build();
        client.start();

        HttpGet request = new HttpGet("http://localhost:8080");
        Future<HttpResponse> future = client.execute(request, null);
        HttpResponse response = future.get();

//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }

    static class GetThread extends Thread {
        private CloseableHttpAsyncClient client;
        private HttpContext context;
        private HttpGet request;

        public GetThread(CloseableHttpAsyncClient client,HttpGet req){
            this.client = client;
            context = HttpClientContext.create();
            this.request = req;
        }

        @Override
        public void run() {
            try {
                Future<HttpResponse> future = client.execute(request, context, null);
                HttpResponse response = future.get();
//                assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }


    }
}
