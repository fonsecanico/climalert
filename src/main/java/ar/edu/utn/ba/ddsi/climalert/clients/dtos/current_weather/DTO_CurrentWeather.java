package ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather;

import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.data.DTO_CurrentData;
import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.data.DTO_CurrentLocation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // esto es para traerme sólo location y current
public record DTO_CurrentWeather(
        // los manejo con json properties para diferenciar un poco
        // lo que es el punto de mi sistema y lo que es de afuera
        @JsonProperty("location") DTO_CurrentLocation currentLocation,
        @JsonProperty("current") DTO_CurrentData currentData
) {}
