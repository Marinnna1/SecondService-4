package ru.itmo.SecondService.service;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import ru.itmo.SecondService.dto.PaginationDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class RequestService {

    public <T> T sendRequest(String url, HttpMethod method, HttpEntity body, Class<T> dtoClass) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException { // returns any dto (t extends object)

        /*SSL*/
        KeyStore keyStore;
        keyStore = KeyStore.getInstance("PKCS12");
        //ClassPathResource classPathResource = new ClassPathResource("C:/Users/marus/Desktop/keystore.p12");
        File file = new File("C:/Users/marus/Desktop/keystore.p12");
       // File file = new File("/home/studs/s284200/key/keystore.p12");
        FileInputStream fileInputStream = new FileInputStream(file);
        //InputStream inputStream = classPathResource.getInputStream();
        keyStore.load(fileInputStream, "changeit".toCharArray());

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder()
                .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                .loadKeyMaterial(keyStore, "changeit".toCharArray()).build(),
                NoopHostnameVerifier.INSTANCE);


        HttpUriRequest req = getRequest(method, url, body);

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLSocketFactory(socketFactory).build()) { // here include our prepared ssl
            try (CloseableHttpResponse response = httpClient.execute(req)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

                return getEntityFromJson(responseBody, dtoClass);
            }
        }

    }


    private HttpUriRequest getRequest(HttpMethod method, String url,  HttpEntity body) {
        switch (method){
            case GET:{
                return new HttpGet(url);
            }
            case POST:{
                HttpPost req = new HttpPost(url);
                if(body != null) {
                    req.setEntity(body);
                }
                return req;
            }
            case PUT:{
                HttpPut req = new HttpPut(url);
                if(body != null) {
                    req.setEntity(body);
                }
                return req;
            }
            case DELETE:{
                return new HttpDelete(url);
            }
            default:{
                return null;
            }
        }
    }

    private <T> T getEntityFromJson(String jsonEntity, Class<T> entityClass){
        return new Gson().fromJson(jsonEntity, entityClass);
    }


}