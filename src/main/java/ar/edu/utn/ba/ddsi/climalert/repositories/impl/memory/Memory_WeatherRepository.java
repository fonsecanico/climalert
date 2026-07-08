package ar.edu.utn.ba.ddsi.climalert.repositories.impl.memory;

import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import ar.edu.utn.ba.ddsi.climalert.exceptions.ResourceNotFoundException;
import ar.edu.utn.ba.ddsi.climalert.repositories.interfaces.IWeatherRepository;
import ar.edu.utn.ba.ddsi.climalert.utils.IDGenerator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class Memory_WeatherRepository implements IWeatherRepository
{
    List<Weather> reports = new ArrayList<>();
    IDGenerator idGenerator = new IDGenerator();

    @Override
    public List<Weather> findAll() {
        return new ArrayList<>(reports);
    }

    @Override
    public Weather findByID(Long id) {
        return this.reports.stream().filter(report -> report.getId().equals(id))
                                    .findFirst()
                                    .orElseThrow(() -> new ResourceNotFoundException("No se encontró el ID " + id + " en el repositorio de reportes de clima"));
    }

    @Override
    public void delete(Long id) {
        reports.removeIf(report -> report.getId().equals(id));
    }

    @Override
    public Weather save(Weather weatherReport)
    {
        if(weatherReport.getId() == null)
        {
            weatherReport.setId(idGenerator.next());
        }
        reports.removeIf(report -> report.getId().equals(weatherReport.getId()));
        reports.add(weatherReport);
        return weatherReport;
    }

    @Override
    public Optional<Weather> findLatestReport()
    {
        if (this.reports.isEmpty()) return Optional.empty();
        return Optional.of(reports.getLast());
    }
}
