package ar.edu.utn.ba.ddsi.climalert.repositories.interfaces;

import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import ar.edu.utn.ba.ddsi.climalert.repositories.IRepository;

import java.util.Optional;

public interface IWeatherRepository extends IRepository<Weather>
{
    Optional<Weather> findLatestReport();
}
