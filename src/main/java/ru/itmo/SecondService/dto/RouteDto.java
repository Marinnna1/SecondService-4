package ru.itmo.SecondService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RouteDto implements Serializable {

    private String name;

    private Integer coordinatesId;

    private Integer fromId;

    private Integer toId;

    private Integer distance;
}
