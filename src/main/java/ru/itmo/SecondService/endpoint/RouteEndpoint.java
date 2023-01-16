package ru.itmo.SecondService.endpoint;


import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.itmo.SecondService.converter.DtoValidator;
import ru.itmo.SecondService.converter.RouteValidator;
import ru.itmo.SecondService.dto.MessageDto;
import ru.itmo.SecondService.entity.Route;
import ru.itmo.SecondService.service.RouteService;
import soa.dtos.*;


import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;


@Endpoint
@RequiredArgsConstructor
public class RouteEndpoint {

    private static final String NAMESPACE_URI = "http://soa/dtos";

    private final RouteService routeService;




    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRouteByIdRequest")
    @ResponsePayload
    public GetRouteByIdResponse getRouteById(@RequestPayload GetRouteByIdRequest request) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        GetRouteByIdResponse response = new GetRouteByIdResponse();
        Route route = routeService.getRouteById(request.getId());
        RouteSchema routeSchema = RouteValidator.parseToRouteSchema(route);
        response.setRouteSchema(routeSchema);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteRouteByIdRequest")
    @ResponsePayload
    public DeleteRouteByIdResponse deleteRouteById(@RequestPayload DeleteRouteByIdRequest request) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        DeleteRouteByIdResponse response = new DeleteRouteByIdResponse();
        Integer status = routeService.deleteRouteById(request.getId());
        String message;
        if(status == -1) {
            message = "Route not found";
        }
        else {
            message = "Route deleted";
        }
        response.setMessage(message);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createRouteRequest")
    @ResponsePayload
    public CreateRouteResponse createRoute(@RequestPayload CreateRouteRequest request) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        CreateRouteResponse response = new CreateRouteResponse();
        MessageDto messageDto = routeService.createRoute(DtoValidator.parseToRouteDto(request.getRouteDtoSchema()));
        response.setMessageDtoSchema(DtoValidator.parseToMessageDtoSchema(messageDto));
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "routeSumDistancesRequest")
    @ResponsePayload
    public RouteSumDistancesResponse getRouteSumDistance() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyManagementException, KeyStoreException {
        RouteSumDistancesResponse response = new RouteSumDistancesResponse();
        Long sum = routeService.getDistancesSum();
        String message;
        if(sum == null) {
            message = "There are no distances";
        }
        else {
            message = String.valueOf(sum);
        }
        response.setMessage(message);
        return response;
    }




}
