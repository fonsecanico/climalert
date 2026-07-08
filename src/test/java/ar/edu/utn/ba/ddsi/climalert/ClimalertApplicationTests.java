package ar.edu.utn.ba.ddsi.climalert;

import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.DTO_CurrentWeather;
import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.data.DTO_CurrentCondition;
import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.data.DTO_CurrentData;
import ar.edu.utn.ba.ddsi.climalert.clients.dtos.current_weather.location.DTO_CurrentLocation;
import ar.edu.utn.ba.ddsi.climalert.clients.interfaces.IWeatherApiClient;
import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import ar.edu.utn.ba.ddsi.climalert.mappers.impl.WeatherMapper;
import ar.edu.utn.ba.ddsi.climalert.mappers.interfaces.IWeatherMapper;
import ar.edu.utn.ba.ddsi.climalert.providers.impl.WeatherProvider;
import ar.edu.utn.ba.ddsi.climalert.services.notifications.adapters.interfaces.IEmailSender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

	@MockitoBean
	private IWeatherApiClient mock_weatherApiClient;
	@MockitoBean
	private IEmailSender mock_emailSender;
	@Autowired
	private WeatherProvider weatherProvider;
	private final IWeatherMapper mock_mapper = mock(IWeatherMapper.class);

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
		assertEquals("Ciudad: CABA | Región: Buenos Aires | País: Argentina | Hora Local: 2022-12-18 14:55 | Condición climática: Soleado" + "\n" + "Temperatura: 15.0 ºC | Humedad: 20 % | Presión: 1013.25 hPa | Velocidad del Viento: 2.0 km/h | Dirección del Viento: E | Ráfagas de Viento: 1.0 km/h | Visibilidad: 10.0 km",weather.getMessage());
	}

	@Test
	void probarLlamadaApiMockeada()
	{
		when(mock_weatherApiClient.getCurrentWeather(anyString(), anyString()))
				.thenReturn(mock_currentWeather_safe);
		Optional<Weather> result = weatherProvider.provide();
		assertTrue(result.isPresent());
		assertEquals("CABA", result.get().getCity());
	}
	@Test
	void providerDevuelveOptionalEmptyCuandoLaApiFalla()
	{
		when(mock_weatherApiClient.getCurrentWeather(anyString(), anyString()))
				.thenThrow(new RuntimeException("WeatherAPI Server Error - Connection Timeout"));
		Optional<Weather> result = weatherProvider.provide();
		assertTrue(result.isEmpty(), "El resultado debería ser un Optional.empty() si la API falla");
	}
	@Test
	void debeEnviarMailCorrectamente()
	{
		doNothing().when(mock_emailSender).send(anyList(), anyString(), anyString());
		assertDoesNotThrow(() -> mock_emailSender.send(List.of("admin@clima.com"), "Alerta", "Huracan"));
		verify(mock_emailSender, times(1)).send(anyList(), anyString(), anyString());
	}

	@Test
	void debeFallarElEnvioDelMailCuandoLanzaExcepcion()
	{
		doThrow(new RuntimeException("Error de red al conectarse con SendGrid"))
				.when(mock_emailSender).send(anyList(), anyString(), anyString());
		assertThrows(RuntimeException.class, () -> {
			mock_emailSender.send(List.of("admin@clima.com"), "Alerta", "Huracan");
		});
		verify(mock_emailSender, times(1)).send(anyList(), anyString(), anyString());
	}
	@Test
	void debeValidarQueElClimaNoEsCritico()
	{
		Weather weather = mapper.map(mock_currentWeather_safe);
		assertFalse(weather.isCritical(), "El clima seguro no debería marcarse como crítico");
	}

	@Test
	void debeValidarQueElClimaSiEsCritico()
	{
		Weather weather = mapper.map(mock_currentWeather_unsafe);
		assertTrue(weather.isCritical(), "El clima peligroso debería marcarse como crítico");
	}
}
