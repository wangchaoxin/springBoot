package com.wcx.springboot.demo.java.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * This article will show how to configure the Apache HttpClient 4 with “Accept All” SSL support.
 * The goal is simple – consume HTTPS URLs which do not have valid certificates.
 */
public class SSL {
    public class RestClientLiveManualTest {

        /**
         * 2. The SSLPeerUnverifiedException
         * Without configuring SSL with the HttpClient, the following testSoftReference – consuming an HTTPS URL – will fail:
         * The javax.net.ssl.SSLPeerUnverifiedException exception occurs whenever a valid chain of trust couldn’t be established for the URL.
         * @throws ClientProtocolException
         * @throws IOException
         */
        @Test(expected = SSLPeerUnverifiedException.class)
        public void whenHttpsUrlIsConsumed_thenException()
                throws ClientProtocolException, IOException {

            CloseableHttpClient httpClient = HttpClients.createDefault();
            String urlOverHttps
                    = "https://localhost:8082/spring-security-rest-basic-auth";
            HttpGet getMethod = new HttpGet(urlOverHttps);

            HttpResponse response = httpClient.execute(getMethod);
            assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        }

        /**
         * 3. Configure SSL – Accept All (HttpClient < 4.3)
         * Let’s now configure the HTTP client to trust all certificate chains regardless of their validity:
         * @throws IOException
         * @throws GeneralSecurityException
         */
        @Test
        public void givenAcceptingAllCertificates_whenHttpsUrlIsConsumed_thenException()
                throws IOException, GeneralSecurityException {
            //With the new TrustStrategy now overriding the standard certificate verification process (which should consult a configured trust manager) –
            // the testSoftReference now passes and the client is able to consume the HTTPS URL.
            TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
            SSLSocketFactory sf = new SSLSocketFactory(
                    acceptingTrustStrategy, ALLOW_ALL_HOSTNAME_VERIFIER);
            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("https", 8443, sf));
            ClientConnectionManager ccm = new PoolingClientConnectionManager(registry);

            DefaultHttpClient httpClient = new DefaultHttpClient(ccm);

            String urlOverHttps
                    = "https://localhost:8443/spring-security-rest-basic-auth/api/bars/1";
            HttpGet getMethod = new HttpGet(urlOverHttps);

            HttpResponse response = httpClient.execute(getMethod);
            assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        }

        /**
         * 4. The Spring RestTemplate with SSL (HttpClient < 4.3)
         * Now that we have seen how to configure a raw HttpClient with SSL support, let’s take a look at a higher level client – the Spring RestTemplate.
         * With no SSL configured, the following testSoftReference fails as expected:
         */
        @Test(expected = ResourceAccessException.class)
        public void whenHttpsUrlIsConsumed_thenException1() {
            String urlOverHttps
                    = "https://localhost:8443/spring-security-rest-basic-auth/api/bars/1";
            ResponseEntity<String> response
                    = new RestTemplate().exchange(urlOverHttps, HttpMethod.GET, null, String.class);
            assertThat(response.getStatusCode().value(), equalTo(200));
        }

        /**
         * As you can see, this is very similar to the way we configured SSL for the raw HttpClient –
         * we configure the request factory with SSL support and then we instantiate the template passing
         * this preconfigured factory.
         * @throws GeneralSecurityException
         */
        @Test
        public void givenAcceptingAllCertificates_whenHttpsUrlIsConsumed_thenException2()
                throws GeneralSecurityException {
            HttpComponentsClientHttpRequestFactory requestFactory
                    = new HttpComponentsClientHttpRequestFactory();
            DefaultHttpClient httpClient
                    = (DefaultHttpClient) requestFactory.getHttpClient();
            TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
            SSLSocketFactory sf = new SSLSocketFactory(
                    acceptingTrustStrategy, ALLOW_ALL_HOSTNAME_VERIFIER);
            httpClient.getConnectionManager().getSchemeRegistry()
                    .register(new Scheme("https", 8443, sf));

            String urlOverHttps
                    = "https://localhost:8443/spring-security-rest-basic-auth/api/bars/1";
            ResponseEntity<String> response = new RestTemplate(requestFactory).
                    exchange(urlOverHttps, HttpMethod.GET, null, String.class);
            assertThat(response.getStatusCode().value(), equalTo(200));
        }

        /**
         * 5. Configure SSL – Accept All (HttpClient 4.4)
         * In HttpClient version 4.4, with SSLSocketFactory now deprecated, we can simply configure our HttpClient as follows:
         * @throws Exception
         */
        @Test
        public void givenIgnoringCertificates_whenHttpsUrlIsConsumed_thenCorrect()
                throws Exception {
            SSLContext sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, (certificate, authType) -> true).build();

            CloseableHttpClient client = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
            HttpGet httpGet = new HttpGet("https://url");
            httpGet.setHeader("Accept", "application/xml");

            HttpResponse response = client.execute(httpGet);
            assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        }

        /**
         * 6. The Spring RestTemplate with SSL (HttpClient 4.4)
         * And we can use the same way to configure our RestTemplate:
         * @throws ClientProtocolException
         * @throws IOException
         */
        @Test
        public void givenAcceptingAllCertificatesUsing4_4_whenUsingRestTemplate_thenCorrect()
                throws ClientProtocolException, IOException {
            CloseableHttpClient httpClient
                    = HttpClients.custom()
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
            HttpComponentsClientHttpRequestFactory requestFactory
                    = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);

            ResponseEntity<String> response
                    = new RestTemplate(requestFactory).exchange(
                    "", HttpMethod.GET, null, String.class);
            assertThat(response.getStatusCode().value(), equalTo(200));
        }
    }
}
