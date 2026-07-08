package ar.edu.utn.ba.ddsi.climalert.providers.interfaces;

import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import ar.edu.utn.ba.ddsi.climalert.providers.IProvider;

// este provider vincula la entidad de dominio Weather con el Provider
public interface IWeatherProvider extends IProvider<Weather> {}
