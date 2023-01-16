package ru.itmo.SecondService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.SecondService.entity.Route;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable {

    @JsonProperty("route")
    private Route route;

    @JsonProperty("message")
    private String message;

    public MessageDto(String message) {
        this.message = message;
    }

    public MessageDto(Route route) {
        this.route = route;
    }

}
