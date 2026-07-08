package ar.edu.utn.ba.ddsi.climalert.clients.interfaces;

import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.DTO_CurrentWeather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
// en este caso no abstraigo tanto aun
// ya que es una primera iteracion y no se tiene del todo definido
// como se va a trabajar con el resto de los clientes, si es que hay
// ni como se va a trabajar con este cliente, si es que cambiará
@FeignClient(name = "weatherAPI", url = "${climalert.weatherapi.url}")
public interface IWeatherApiClient
{
    // mirando la docu de weather api, tiene como Request Parameters la "key", que es la api key
    // y tiene "q", que es el query param para que la API se base en lo que me va a mandar
    // le mando un get a "/current.json" porque es lo que me expone la api para preguntarle por el clima
    @GetMapping("/current.json")
    DTO_CurrentWeather getCurrentWeather(@RequestParam("key") String apiKey, @RequestParam("q") String location);
}
