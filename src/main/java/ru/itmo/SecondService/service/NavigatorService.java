package ru.itmo.SecondService.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.itmo.SecondService.entity.Route;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Service
@RequiredArgsConstructor
public class NavigatorService {

    private final RequestService requestService;


    public Route findLongestOrShortestRoute(int idFrom, int idTo, int shortest) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = "https://localhost:8086/navigator/routes/" + idFrom + "/" + idTo + "/" + shortest;
        return requestService.sendRequest(uri, HttpMethod.POST, null, Route.class);
    }


    public Route createRouteBetweenLocations(int idFrom, int idTo) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        final String uri = "https://localhost:8086/navigator/routes/" + idFrom + "/" + idTo;
        return requestService.sendRequest(uri, HttpMethod.POST, null, Route.class);
    }
}
