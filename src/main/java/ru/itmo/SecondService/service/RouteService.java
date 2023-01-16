package ru.itmo.SecondService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.entity.StringEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.itmo.SecondService.dto.FilterDto;
import ru.itmo.SecondService.dto.MessageDto;
import ru.itmo.SecondService.dto.PaginationDto;
import ru.itmo.SecondService.dto.RouteDto;
import ru.itmo.SecondService.entity.Route;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RequestService requestService;



     public Route getRouteById(long id) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
         final String uri = "https://localhost:8086/routes/" + id;
         return requestService.sendRequest(uri, HttpMethod.GET, null, Route.class);
     }

     public Integer deleteRouteById(long id) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
         final String uri = "https://localhost:8086/routes/" + id;
         if(getRouteById(id) == null) {
             return -1;
         }
         return requestService.sendRequest(uri, HttpMethod.DELETE, null, Integer.class);
     }


     public Long getDistancesSum() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
         final String uri = "https://localhost:8086/routes/distances/sum";
         return requestService.sendRequest(uri, HttpMethod.POST, null, Long.class);
     }

     public Long getDistanceNumber(int value) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
         final String uri = "https://localhost:8086/routes/distances/less/" + value;
         return requestService.sendRequest(uri, HttpMethod.POST, null, Long.class);
     }


     public List getUniqueDistances(int pageNumber, int pageSize) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
         final String uri = "https://localhost:8086/routes/distances/unique?pageSize=" + pageSize + "&pageNumber=" + pageNumber;
         return requestService.sendRequest(uri, HttpMethod.POST, null, List.class);
     }

    public Integer getUniqueDistancesCount() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = "https://localhost:8086/routes/distances/unique/count";
        return requestService.sendRequest(uri, HttpMethod.POST, null, Integer.class);
    }

    public MessageDto createRoute(RouteDto routeDto) throws IOException, CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        final String uri = "https://localhost:8086/routes/";
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(routeDto);
        StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        return requestService.sendRequest(uri, HttpMethod.POST, requestEntity, MessageDto.class);
    }


    public List getRoutes(PaginationDto paginationDto) throws IOException, CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        final String uri = "https://localhost:8086/routes/filter";
        for (Map.Entry<String, String> entry : paginationDto.getFilters().entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(paginationDto);
        StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        return requestService.sendRequest(uri, HttpMethod.POST, requestEntity, List.class);
    }


    public Long getRoutesCount(FilterDto filterDto) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = "https://localhost:8086/routes/count";
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(filterDto);
        StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        return requestService.sendRequest(uri, HttpMethod.POST, requestEntity, Long.class);
    }


    public String getTest() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = "https://localhost:8086/routes/test";
        return requestService.sendRequest(uri, HttpMethod.GET, null, String.class);
    }


    public MessageDto updateRoute(long id, RouteDto routeDto) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = "https://localhost:8086/routes/" + id;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(routeDto);
        StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        return requestService.sendRequest(uri, HttpMethod.PUT, requestEntity, MessageDto.class);
    }


}
