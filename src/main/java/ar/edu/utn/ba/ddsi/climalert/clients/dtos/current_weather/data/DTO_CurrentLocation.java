package ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*

En la docu de Weather API

"location": {
    "name": "Boston",
    "region": "Lincolnshire",
    "country": "United Kingdom",
    "lat": 53.0,
    "lon": -0.12,
    "tz_id": "Europe/London",
    "localtime_epoch": 1673620218,
    "localtime": "2023-01-13 14:30"
}
Bueno aca no hay mucha mas vuelta me parece, con la ciudad, la region, el pais y la hora local estamos
name | string | Location name
region | string | Region or state of the location, if availa
country	| string | Location country
localtime | string | Local date and time

En este caso, todos strings
Misma estrtegia para JsonIgnoreProperties y las JsonProperty de current weather
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public record DTO_CurrentLocation(
        @JsonProperty("name") String city,
        @JsonProperty("region") String region,
        @JsonProperty("country") String country,
        @JsonProperty("localtime") String time
) {}
