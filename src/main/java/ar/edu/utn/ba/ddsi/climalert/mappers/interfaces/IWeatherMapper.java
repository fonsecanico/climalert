package ar.edu.utn.ba.ddsi.climalert.mappers.interfaces;

import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.DTO_CurrentWeather;
import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import ar.edu.utn.ba.ddsi.climalert.mappers.IMapper;
// esta es la interfaz concreta que va a mapear
// la respuesta de la api con el objeto de dominio del sistema
public interface IWeatherMapper extends IMapper<Weather, DTO_CurrentWeather> {}
