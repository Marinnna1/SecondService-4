package ru.itmo.SecondService.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@AllArgsConstructor
@Data
@NoArgsConstructor
//@XmlRootElement(name = "route")
//@XmlAccessorType(FIELD)
public class Route {

    private long id; // The value of the field must be greater than 0, The value of this field must be unique, The value of this field must be generated automatically
    private String name; // Field cannot be null, String cannot be empty
    private Coordinates coordinates; // Field cannot be null
    private String creationDate; // The field cannot be null, the value of this field must be generated automatically
    private Location fromLocation; // Field can be null
    private Location to; // Field can be null
    private Integer distance; // The field can be null, the field value must be greater than 1

    public Route(String name, Coordinates coordinates, Location fromLocation, Location to, Integer distance) {
        this.name = name;
        this.coordinates = coordinates;
        this.fromLocation = fromLocation;
        this.to = to;
        this.distance = distance;
        this.creationDate = java.time.LocalDateTime.now().toString();
    }

}