package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.config.WeatherApiConfig;
import bg.softuni.pathfinder.model.dto.CoordinatesRequestDTO;
import bg.softuni.pathfinder.model.dto.CurrentWeatherDTO;
import bg.softuni.pathfinder.service.WeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalTime;
import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final String COND_IMG_BASE_URL = "/images/weather-icons/";

    private final RestClient restClient;
    private final WeatherApiConfig weatherApiConfig;

    @Autowired
    public WeatherServiceImpl(
            Gson gson,
            RestClient restClient, WeatherApiConfig weatherApiConfig
    ) {
        this.restClient = restClient;
        this.weatherApiConfig = weatherApiConfig;
    }

//    TODO: impl caching

    @Override
    public Map<String, CurrentWeatherDTO> getTemps() throws JsonProcessingException {
        Map<String, CurrentWeatherDTO> weatherData = Map.of(
                "varna", getCurrentWeather(weatherApiConfig.getCoordinatesVarna()),
                "sofia", this.getCurrentWeather(weatherApiConfig.getCoordinatesSofia())
        );

        return weatherData;
    }

    private CurrentWeatherDTO getCurrentWeather(CoordinatesRequestDTO coord) throws JsonProcessingException {

        String jsonRaw = this.restClient
                .get()
                .uri(
                        UriComponentsBuilder
                                .fromHttpUrl(weatherApiConfig.getUrl())
                                .queryParam("latitude", coord.latitude())
                                .queryParam("longitude", coord.longitude())
                                .queryParam(
                                        weatherApiConfig.getQuery(),
                                        String.format("%s,%s",
                                                weatherApiConfig.getForecast(), weatherApiConfig.getWeatherCode()))
                                .build()
                                .toUri()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);

        ObjectMapper objMapper = new ObjectMapper();
        JsonNode jsonNode = objMapper.readTree(jsonRaw);
        JsonNode current = jsonNode.get("current");
        Double temp = current.get("temperature_2m").asDouble();
        Integer cond = current.get("weather_code").asInt();

        return new CurrentWeatherDTO(temp, this.mapCondToImage(cond));
    }

    private String mapCondToImage(Integer cond) {

        String imageName = switch (cond) {
            case 0 -> "01";
            case 1 -> "02";
            case 2 -> "03";
            case 3 -> "04";
            case 45, 48 -> "50";
            case 51, 53, 55, 56, 57, 80, 81, 82 -> "09";
            case 62, 63, 65, 66, 67 -> "10";
            case 71, 73, 75, 77, 85, 86 -> "13";
            case 95, 96, 99 -> "11";

            default -> null;
        };

        if (imageName == null) throw new IllegalArgumentException();

        LocalTime now = LocalTime.now();
        String timeOfDaySuffix =
                now.isAfter(LocalTime.of(8, 0)) && now.isBefore(LocalTime.of(20, 0))
                        ? "d"
                        : "n";

        String fileExtSuffix = ".png";

        return String.format("%s%s%s%s", COND_IMG_BASE_URL, imageName, timeOfDaySuffix, fileExtSuffix);
    }
}
