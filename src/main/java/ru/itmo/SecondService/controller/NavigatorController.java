package ru.itmo.SecondService.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.itmo.SecondService.entity.Route;
import ru.itmo.SecondService.service.NavigatorService;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;


@RestController
@RequestMapping(value = "/navigator")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class NavigatorController {

    private final NavigatorService navigatorService;


    @PostMapping("/routes/{id-from}/{id-to}")
    public ResponseEntity createRouteBetweenLocations(@PathVariable("id-from") int idFrom,
                                                      @PathVariable("id-to") int idTo) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        Route route = navigatorService.createRouteBetweenLocations(idFrom, idTo);
        if(route == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no route between them");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(route);
    }


    @PostMapping("/routes/{id-from}/{id-to}/{shortest}")
    public ResponseEntity findLongestOrShortestRoute(@PathVariable("id-from") int idFrom,
                                                     @PathVariable("id-to") int idTo,
                                                     @PathVariable("shortest") int shortest) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        if(shortest != 1 && shortest != 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid parameter shortest");
        }
        Route route = navigatorService.findLongestOrShortestRoute(idFrom, idTo, shortest);
        if(route == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(route);
    }


    @ExceptionHandler({ MethodArgumentTypeMismatchException.class})
    public ResponseEntity handleBaseExceptions() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
    }




}
