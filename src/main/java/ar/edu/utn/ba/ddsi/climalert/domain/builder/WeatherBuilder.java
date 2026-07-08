package ar.edu.utn.ba.ddsi.climalert.domain.builder;

import ar.edu.utn.ba.ddsi.climalert.domain.Weather;
import lombok.Data;

// en vez de tener la logica de creacion en el dominio
// la establezco en un builder
public class WeatherBuilder
{
    Double temperature;
    Integer humidity;
    Double pressure;
    Double windSpeed;
    String windDirection;
    Double windGust;
    Double visibility;
    String condition;
    String city;
    String region;
    String country;
    String time;
    boolean isCritical;

    final StringBuilder locationMessage = new StringBuilder();
    final StringBuilder dataMessage = new StringBuilder();
    String message = "";

    Double maxTemperature;
    Integer maxHumidity;

    public WeatherBuilder(String maxTemperature, String maxHumidity)
    {
        this.maxTemperature = Double.parseDouble(maxTemperature);
        this.maxHumidity = Integer.parseInt(maxHumidity);
    }

    public WeatherBuilder withTemperature(Double temperature)
    {
        this.temperature = temperature;
        this.addToMessage(dataMessage, "Temperatura", temperature.toString(), "ºC");
        return this;
    }
    public WeatherBuilder withHumidity(Integer humidity)
    {
        this.humidity = humidity;
        this.addToMessage(dataMessage, "Humedad", humidity.toString(), "%");
        return this;
    }
    public WeatherBuilder withPressure(Double pressure)
    {
        this.pressure = pressure;
        this.addToMessage(dataMessage, "Presión", pressure.toString(), "hPa");
        return this;
    }
    public WeatherBuilder withWindSpeed(Double windSpeed)
    {
        this.windSpeed = windSpeed;
        this.addToMessage(dataMessage, "Velocidad del Viento", windSpeed.toString(), "km/h");
        return this;
    }
    public WeatherBuilder withWindDirection(String windDirection)
    {
        this.windDirection = windDirection;
        this.addToMessage(dataMessage, "Dirección del Viento", windDirection);
        return this;
    }
    public WeatherBuilder withWindGust(Double windGust)
    {
        this.windGust = windGust;
        this.addToMessage(dataMessage, "Ráfagas de Viento", windGust.toString(), "km/h");
        return this;
    }
    public WeatherBuilder withVisibility(Double visibility)
    {
        this.visibility = visibility;
        this.addToMessage(dataMessage, "Visibilidad", visibility.toString(), "km");
        return this;
    }
    public WeatherBuilder withCity(String city)
    {
        this.city = city;
        this.addToMessage(locationMessage, "Ciudad", city);
        return this;
    }
    public WeatherBuilder withRegion(String region)
    {
        this.region = region;
        this.addToMessage(locationMessage, "Región", region);
        return this;
    }
    public WeatherBuilder withCountry(String country)
    {
        this.country = country;
        this.addToMessage(locationMessage, "País", country);
        return this;
    }
    public WeatherBuilder withTime(String time)
    {
        this.time = time;
        this.addToMessage(locationMessage, "Hora Local", time);
        return this;
    }
    public WeatherBuilder withCondition(String condition)
    {
        this.condition = condition;
        this.addToMessage(locationMessage, "Condición climática", condition);
        return this;
    }
    private void addToMessage(StringBuilder messageType, String fieldName, String fieldValue)
    {
        this.addToMessage(messageType, fieldName, fieldValue, "");
    }
    private void addToMessage(StringBuilder messageType, String fieldName, String fieldValue, String unit)
    {
        String prefix = messageType.isEmpty() ? "" : " | ";
        String unit_fixed = unit.isEmpty()? "" : " " + unit;
        messageType.append(prefix).append(fieldName).append(": ").append(fieldValue).append(unit_fixed);
    }
    private void setIsCritical()
    {
        this.isCritical = this.temperature > this.maxTemperature && this.humidity > this.maxHumidity;
        if(this.isCritical) this.message += "---> ALERTA CRÍTICA <---\n";
    }
    public Weather build()
    {
        this.setIsCritical();
        this.message += locationMessage + "\n" + dataMessage;
        return new Weather( this.temperature,
                            this.humidity,
                            this.pressure,
                            this.windSpeed,
                            this.windDirection,
                            this.windGust,
                            this.visibility,
                            this.condition,
                            this.city,
                            this.region,
                            this.country,
                            this.time,
                            this.message,
                            this.isCritical
        );
    }
}
