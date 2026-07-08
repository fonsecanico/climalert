package ar.edu.utn.ba.ddsi.climalert.services.reports.impl;

import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import ar.edu.utn.ba.ddsi.climalert.providers.interfaces.IWeatherProvider;
import ar.edu.utn.ba.ddsi.climalert.repositories.interfaces.IWeatherRepository;
import ar.edu.utn.ba.ddsi.climalert.services.notifications.interfaces.IWeatherNotificationService;
import ar.edu.utn.ba.ddsi.climalert.services.reports.interfaces.IWeatherReportService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherReportService implements IWeatherReportService
{
    private final IWeatherProvider weatherProvider;
    private final IWeatherNotificationService weatherNotificationService;
    private final IWeatherRepository weatherRepository;

    @Override
    public void getWeather()
    {
        Optional<Weather> weatherReport = weatherProvider.provide();
        weatherReport.ifPresent(weatherRepository::save);
    }
    @Override
    public void checkWeather()
    {
        Optional<Weather> latestReport = weatherRepository.findLatestReport();
        if(latestReport.isPresent() && latestReport.get().isCritical()) weatherNotificationService.notify(latestReport.get());
    }
}
