package ar.edu.utn.ba.ddsi.climalert.providers.impl;

import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.DTO_CurrentWeather;
import ar.edu.utn.ba.ddsi.climalert.clients.interfaces.IWeatherApiClient;
import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import ar.edu.utn.ba.ddsi.climalert.mappers.interfaces.IWeatherMapper;
import ar.edu.utn.ba.ddsi.climalert.providers.interfaces.IWeatherProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

// este es el provider que conecta el sistema con la api
// le pongo Slf4j para que me loguee

@Slf4j
@Component
public class WeatherProvider implements IWeatherProvider {

    private final IWeatherApiClient client;
    private final IWeatherMapper mapper;

    private final String apiKey;
    private final String location;

    public WeatherProvider(IWeatherApiClient client, IWeatherMapper mapper,
                           @Value("${climalert.weatherapi.api-key") String apiKey,
                           @Value("${climalert.weatherapi.location}") String location)
    {
        this.client = client;
        this.mapper = mapper;
        this.apiKey = apiKey;
        this.location = location;
    }
    @Override
    public Optional<Weather> provide()
    {
        try
        {
            DTO_CurrentWeather currentWeather = this.client.getCurrentWeather(this.apiKey, this.location);
            return Optional.of(mapper.map(currentWeather));
        }
        catch (Exception exception)
        {
            log.error("El clima de WeatherAPI no se ha podido obtener. Motivo: {}", exception.getMessage(), exception);
            return Optional.empty();
        }
    }
}
