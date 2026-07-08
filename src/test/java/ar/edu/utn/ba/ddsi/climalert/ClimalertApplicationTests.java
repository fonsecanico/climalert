package ar.edu.utn.ba.ddsi.climalert;

import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.DTO_CurrentWeather;
import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.data.DTO_CurrentCondition;
import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.data.DTO_CurrentData;
import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.location.DTO_CurrentLocation;
import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import ar.edu.utn.ba.ddsi.climalert.mappers.impl.WeatherMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Slf4j
@SpringBootTest
class ClimalertApplicationTests {
	DTO_CurrentLocation mock_currentLocation = new DTO_CurrentLocation( "CABA",
																		"Buenos Aires",
																		"Argentina",
																		"2022-12-18 14:55");
	DTO_CurrentCondition mock_currentCondition_safe = new DTO_CurrentCondition("Soleado");
	DTO_CurrentCondition mock_currentCondition_unsafe = new DTO_CurrentCondition("Huracan");
	DTO_CurrentData mock_currentData_safe = new DTO_CurrentData(15.0,
																20,
																1013.25,
																2.0,
																"E",
																1.0,
																10.0,
																mock_currentCondition_safe);
	DTO_CurrentData mock_currentData_unsafe = new DTO_CurrentData(50.0,
																90,
																2026.50,
																4.0,
																"O",
																2.0,
																20.0,
																mock_currentCondition_unsafe);
	DTO_CurrentWeather mock_currentWeather_safe = new DTO_CurrentWeather(mock_currentLocation, mock_currentData_safe);
	DTO_CurrentWeather mock_currentWeather_unsafe = new DTO_CurrentWeather(mock_currentLocation, mock_currentData_unsafe);
	@Autowired
	private WeatherMapper mapper = new WeatherMapper();


	@Test
	void mapeaOK()
	{
		Weather weather = mapper.map(mock_currentWeather_safe);
		assertEquals(15, weather.getTemperature());
		assertEquals(20, weather.getHumidity());
		assertEquals(1013.25, weather.getPressure());
		assertEquals(2.0, weather.getWindSpeed());
		assertEquals("E", weather.getWindDirection());
		assertEquals(1.0, weather.getWindGust());
		assertEquals(10.0, weather.getVisibility());
		assertEquals("Soleado", weather.getCondition());
		assertEquals("CABA", weather.getCity());
		assertEquals("Buenos Aires", weather.getRegion());
		assertEquals("Argentina", weather.getCountry());
		assertEquals("2022-12-18 14:55", weather.getTime());
		log.info(weather.getMessage());
		//assertEquals(weather.getMessage());
	}

}
