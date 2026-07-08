package ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/*

En la docu de Weather API

"current": {
    "last_updated_epoch": 1673620200,
    "last_updated": "2023-01-13 14:30",
    "temp_c": 8.7,
    "temp_f": 47.7,
    "is_day": 1,
    "condition": {
        "text": "Partly cloudy",
        "icon": "//cdn.weatherapi.com/weather/64x64/day/116.png",
        "code": 1003
    },
    "wind_mph": 24.2,
    "wind_kph": 38.9,
    "wind_degree": 260,
    "wind_dir": "W",
    "pressure_mb": 1005.0,
    "pressure_in": 29.68,
    "precip_mm": 0.0,
    "precip_in": 0.0,
    "humidity": 74,
    "cloud": 75,
    "feelslike_c": 4.4,
    "feelslike_f": 39.9,
    "vis_km": 10.0,
    "vis_miles": 6.0,
    "uv": 2.0,
    "gust_mph": 33.1,
    "gust_kph": 53.3
}

Como no tengo idea qué es lo que importa además de la temperatura y la humedad para informar el clima,
Me fijo qué onda en el SMN https://ws2.smn.gob.ar/

Al entrar veo
Humedad: 70 %
Presión: 1012.8 hPa
Viento: Noroeste a 12 km/h
Visibilidad: 10 km

                       | Mañana               | Tarde                | Noche
-----------------------------------------------------------------------------------------
Tiempo                 | Parcialmente nublado | Parcialmente nublado | Mayormente nublado
Prob. Precipitación    | 0%                   | 0%                   | 0%
Temperatura (°C)       | 8                    | 20                   | 17
Viento (km/h)          | 13 - 22              | 13 - 22              | 7 - 12
Dirección Predominante | NO                   | N                    | N
Ráfagas (km/h)         | -                    | -                    | -



Luego, para los tipos de dato:

temp_c | decimal | Temperature in celsius <- lo manejo con Double

"To read JSON decimal numbers as Double using Jackson,
you do not need any special configuration because Jackson
deserializes JSON floating-point decimals into
Java Double types by default"

humidity | int | Humidity as percentage <- lo manejo con Integer

pressure_mb | decimal | Pressure in millibars <- 1 mb = 1 hPa, Double

wind_kph | decimal | Wind speed in kilometer per hour <- Double

wind_dir | string | Wind direction as 16 point compass. e.g.: NSW <- String

gust_kph | decimal | Wind gust in kilometer per hour <- Double

vis_km | decimal | Visibility in kilometer <- Double

Misma estrtegia para JsonIgnoreProperties y las JsonProperty de current weather
*/

@JsonIgnoreProperties(ignoreUnknown = true)
public record DTO_CurrentData(
        @JsonProperty("temp_c") Double temperature,
        @JsonProperty("humidity") Integer humidity,
        @JsonProperty("pressure_mb") Double pressure,
        @JsonProperty("wind_kph") Double windSpeed,
        @JsonProperty("wind_dir") String windDirection,
        @JsonProperty("gust_kph") Double windGust,
        @JsonProperty("vis_km") Double visibility,
        @JsonProperty("condition") DTO_CurrentCondition currentCondition
) {}
