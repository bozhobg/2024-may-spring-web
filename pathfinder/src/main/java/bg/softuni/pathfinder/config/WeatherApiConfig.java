package bg.softuni.pathfinder.config;

import bg.softuni.pathfinder.model.dto.CoordinatesRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "open-meteo.api") // needs getters and setters to fill in fields
public class WeatherApiConfig {

//    TODO: introduce (how) separate config .yaml for external settings

    private final String REQUEST_FORMATTER = "%s?latitude=%s&longitude=%s&current=%s,%s";


    private String url;
    private String query;
    private String forecast;
    private String weatherCode;

//    TODO: test with CoordinatesRequestDTO eg private DTO varna; private DTO sofia? mapping props to objs

    @Value("${open-meteo.varna.latitude}")
    private String varnaLat;
    @Value("${open-meteo.varna.longitude}")
    private String varnaLong;

    @Value("${open-meteo.sofia.latitude}")
    private String sofiaLat;
    @Value("${open-meteo.sofia.longitude}")
    private String sofiaLong;

//    https://api.open-meteo.com/v1/forecast?latitude=42.6975&longitude=23.3241&current=temperature_2m,weather_code


    public String getUrl() {
        return url;
    }

    public WeatherApiConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public WeatherApiConfig setQuery(String query) {
        this.query = query;
        return this;
    }

    public String getForecast() {
        return forecast;
    }

    public WeatherApiConfig setForecast(String forecast) {
        this.forecast = forecast;
        return this;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public WeatherApiConfig setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
        return this;
    }

    public String getVarnaLat() {
        return varnaLat;
    }

    public String getVarnaLong() {
        return varnaLong;
    }

    public String getSofiaLat() {
        return sofiaLat;
    }

    public String getSofiaLong() {
        return sofiaLong;
    }

    public String getRequestCurrentVarna() {

        return String.format(REQUEST_FORMATTER, url, varnaLat, varnaLong, forecast, weatherCode);
    }

    public String getRequestCurrentSofia() {

        return String.format(REQUEST_FORMATTER, url, sofiaLat, sofiaLong, forecast, weatherCode);
    }

    public CoordinatesRequestDTO getCoordinatesSofia() {
        return new CoordinatesRequestDTO(Double.parseDouble(getSofiaLat()), Double.parseDouble(getSofiaLong()));
    }

    public CoordinatesRequestDTO getCoordinatesVarna() {
        return new CoordinatesRequestDTO(Double.parseDouble(getVarnaLat()), Double.parseDouble(getVarnaLong()));
    }
}
