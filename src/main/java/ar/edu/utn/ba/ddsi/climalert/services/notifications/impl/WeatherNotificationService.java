package ar.edu.utn.ba.ddsi.climalert.services.notifications.impl;

import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import ar.edu.utn.ba.ddsi.climalert.services.notifications.adapters.interfaces.IEmailSender;
import ar.edu.utn.ba.ddsi.climalert.services.notifications.interfaces.IWeatherNotificationService;
import ar.edu.utn.ba.ddsi.climalert.utils.SubjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherNotificationService implements IWeatherNotificationService
{
    private final IEmailSender sender;
    @Value("${notify}")
    private List<String> addresses;
    @Value("${subject}")
    private String subjectPrefix;

    @Override
    public void notify(Weather weather)
    {
        try
        {
            log.info("Enviando correo alerta");
            sender.send(addresses, SubjectUtil.getSubject(this.subjectPrefix, weather), weather.getMessage());
            log.info("Correo enviado satisfactoriamente");
        }
        catch (Exception exception)
        {
            log.error("No se pudo enviar el correo de alerta: {}", exception.getMessage(), exception);
        }
    }
}
