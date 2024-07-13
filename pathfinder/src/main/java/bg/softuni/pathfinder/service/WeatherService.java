package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.dto.CurrentWeatherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface WeatherService {

    Map<String, CurrentWeatherDTO> getTemps() throws JsonProcessingException;
}
