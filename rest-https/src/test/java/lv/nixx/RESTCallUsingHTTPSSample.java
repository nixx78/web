package lv.nixx;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.File;


public class RESTCallUsingHTTPSSample {

    public static void main(String[] args) throws Exception {
        RestTemplate restTemplate = restTemplate();

        System.out.println("GET response:" + restTemplate.getForObject("https://localhost:8080/info", String.class));

        ResponseEntity<String> pr = restTemplate.postForEntity("https://localhost:8080/process", "Request.Body", String.class);
        System.out.println("POST response:" + pr.getBody());
    }

    private static RestTemplate restTemplate() throws Exception {

        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(new File("src/main/resources/keystore/client/client.p12"), "123456".toCharArray()).build();

        SSLConnectionSocketFactory sslConFactory = new SSLConnectionSocketFactory(sslContext);

        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslConFactory)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return new RestTemplate(requestFactory);
    }
}
