package ru.itmo.SecondService.converter;

import ru.itmo.SecondService.entity.Coordinates;
import ru.itmo.SecondService.entity.Location;
import ru.itmo.SecondService.entity.Route;
import soa.dtos.CoordinatesSchema;
import soa.dtos.LocationSchema;
import soa.dtos.RouteSchema;

public class RouteValidator {


    public static RouteSchema parseToRouteSchema(Route route) {
        RouteSchema routeSchema = new RouteSchema();
        routeSchema.setId(route.getId());
        routeSchema.setName(route.getName());
        routeSchema.setCoordinates(parseToCoordinatesSchema(route.getCoordinates()));
        routeSchema.setCreationDate(route.getCreationDate());
        routeSchema.setFrom(parseToLocationSchema(route.getFromLocation()));
        routeSchema.setTo(parseToLocationSchema(route.getTo()));
        routeSchema.setDistance(route.getDistance());
        return routeSchema;
    }


    private static CoordinatesSchema parseToCoordinatesSchema(Coordinates coordinates) {
        CoordinatesSchema coordinatesSchema = new CoordinatesSchema();
        coordinatesSchema.setId(coordinates.getId());
        coordinatesSchema.setX(coordinates.getX());
        coordinatesSchema.setY(coordinates.getY());
        return coordinatesSchema;
    }


    private static LocationSchema parseToLocationSchema(Location location) {
        LocationSchema locationSchema = new LocationSchema();
        locationSchema.setId(location.getId());
        location.setX(location.getX());
        location.setY(location.getY());
        location.setZ(location.getZ());
        location.setName(location.getName());
        return locationSchema;
    }

}
