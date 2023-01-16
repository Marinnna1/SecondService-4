package ru.itmo.SecondService.converter;

import ru.itmo.SecondService.dto.MessageDto;
import ru.itmo.SecondService.dto.RouteDto;
import soa.dtos.MessageDtoSchema;
import soa.dtos.RouteDtoSchema;

public class DtoValidator {

    public static RouteDto parseToRouteDto(RouteDtoSchema routeDtoSchema) {
        RouteDto routeDto = new RouteDto();
        routeDto.setName(routeDtoSchema.getName());
        routeDto.setCoordinatesId(routeDtoSchema.getCoordinatesId());
        routeDto.setFromId(routeDtoSchema.getFromId());
        routeDto.setToId(routeDtoSchema.getToId());
        routeDto.setDistance(routeDtoSchema.getDistance());
        return routeDto;
    }

    public static MessageDtoSchema parseToMessageDtoSchema(MessageDto messageDto) {
        MessageDtoSchema messageDtoSchema = new MessageDtoSchema();
        messageDtoSchema.setRouteSchema(RouteValidator.parseToRouteSchema(messageDto.getRoute()));
        messageDtoSchema.setMessage(messageDto.getMessage());
        return messageDtoSchema;
    }
}
