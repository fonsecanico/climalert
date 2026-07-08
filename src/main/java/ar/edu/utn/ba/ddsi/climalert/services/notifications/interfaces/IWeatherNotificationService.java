package ar.edu.utn.ba.ddsi.climalert.services.notifications.interfaces;

import ar.edu.utn.ba.ddsi.climalert.domain.Weather;

public interface IWeatherNotificationService
{
    void notify(Weather weather);
}
