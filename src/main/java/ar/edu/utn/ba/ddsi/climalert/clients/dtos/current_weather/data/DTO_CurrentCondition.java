package ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.data;

import com.fasterxml.jackson.annotation.JsonProperty;

// esto es porque jackson no puede leer condition.text, asi que lo tengo que desarmar
public record DTO_CurrentCondition(
        @JsonProperty("text") String condition
) {}
