package ru.itmo.SecondService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Coordinates {
    private int id;
    private float x;
    private Integer y; //Поле не может быть null

    public Coordinates(float x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
