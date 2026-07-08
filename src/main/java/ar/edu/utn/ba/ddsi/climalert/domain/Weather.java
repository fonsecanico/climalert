package ar.edu.utn.ba.ddsi.climalert.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Weather
{
    Long id;
    final Double temperature;
    final Integer humidity;
    final Double pressure;
    final Double windSpeed;
    final String windDirection;
    final Double windGust;
    final Double visibility;
    final String condition;
    final String city;
    final String region;
    final String country;
    final String time; // lo dejo como string en el dominio porque no tiene otro uso, ad+ no me complico con los formatos de fecha
    final String message;
    final boolean isCritical;
}
