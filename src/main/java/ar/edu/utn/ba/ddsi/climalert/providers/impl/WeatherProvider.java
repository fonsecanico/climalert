package ar.edu.utn.ba.ddsi.climalert.providers.impl;

import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.DTO_CurrentWeather;
import ar.edu.utn.ba.ddsi.climalert.clients.interfaces.IWeatherApiClient;
import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import ar.edu.utn.ba.ddsi.climalert.mappers.interfaces.IWeatherMapper;
import ar.edu.utn.ba.ddsi.climalert.providers.interfaces.IWeatherProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

// este es el provider que conecta el sistema con la api
// le pongo Slf4j para que me loguee
// update: resulta q requiredargsconstructor me ahorra hacer el constructor cuando vengo por value

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherProvider implements IWeatherProvider {

    private final IWeatherApiClient client;
    private final IWeatherMapper mapper;
    @Value("${climalert.weatherapi.api-key}")
    private String apiKey;
    @Value("${climalert.weatherapi.location}")
    private String location;

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
