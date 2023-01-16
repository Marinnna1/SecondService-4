package ru.itmo.SecondService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private int id;
    private int x;
    private float y;
    private Long z; //Поле не может быть null
    private String name; //Поле не может быть null
}
