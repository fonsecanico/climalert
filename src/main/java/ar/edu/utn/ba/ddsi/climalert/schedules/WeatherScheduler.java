package ar.edu.utn.ba.ddsi.climalert.schedules;

import ar.edu.utn.ba.ddsi.climalert.services.reports.interfaces.IWeatherReportService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// misma estrategia para tener a disposicion el logger
@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherScheduler {
    final IWeatherReportService weatherService;
    @Scheduled(fixedRate = 300000) // 1000 ms = 1m -> 60s * 5min = 300s => 300s * 1000ms = 3000ms
    public void getWeather()
    {
        log.info("Obteniendo análisis del clima");
        weatherService.getWeather();
        log.info("Clima registrado");
    }
    @Scheduled(fixedRate = 60000) // 60s = 1m & 1000ms = 1m => 60000ms = 1m
    public void checkWeather()
    {
        log.info("Analizando reporte del clima");
        weatherService.checkWeather();
        log.info("Reporte del clima analizado");
    }
}