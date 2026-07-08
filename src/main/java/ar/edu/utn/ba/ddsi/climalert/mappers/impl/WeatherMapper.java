package ar.edu.utn.ba.ddsi.climalert.mappers.impl;

import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.DTO_CurrentWeather;
import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import ar.edu.utn.ba.ddsi.climalert.domain.builder.WeatherBuilder;
import ar.edu.utn.ba.ddsi.climalert.mappers.interfaces.IWeatherMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper implements IWeatherMapper
{
    @Value("${climalert.critical-conditions.temperature}")
    private String criticalTemperature;
    @Value("${climalert.critical-conditions.humidity}")
    private String criticalHumidity;

    @Override
    public Weather map(DTO_CurrentWeather currentWeather)
    {
        return new WeatherBuilder(criticalTemperature, criticalHumidity)
        .withCity(currentWeather.currentLocation().city())
        .withRegion(currentWeather.currentLocation().region())
        .withCountry(currentWeather.currentLocation().country())
        .withTime(currentWeather.currentLocation().time())
        .withCondition(currentWeather.currentData().currentCondition().condition())

        .withTemperature(currentWeather.currentData().temperature())
        .withHumidity(currentWeather.currentData().humidity())
        .withPressure(currentWeather.currentData().pressure())
        .withWindSpeed(currentWeather.currentData().windSpeed())
        .withWindDirection(currentWeather.currentData().windDirection())
        .withWindGust(currentWeather.currentData().windGust())
        .withVisibility(currentWeather.currentData().visibility())

        .build();
    }
}
