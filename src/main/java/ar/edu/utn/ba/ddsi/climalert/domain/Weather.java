package ar.edu.utn.ba.ddsi.climalert.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weather
{
    Double temperature;
    Integer humidity;
    Double pressure;
    Double windSpeed;
    String windDirection;
    Double windGust;
    Double visibility;
    String condition;
    String city;
    String region;
    String country;
    String time; // lo dejo como string en el dominio porque no tiene otro uso, ad+ no me complico con los formatos de fecha
    String message;
    boolean isCritical;
}
