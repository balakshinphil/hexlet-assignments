package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.HttpClient;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Weather;
import liquibase.pro.packaged.O;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    public Weather getWeatherForCity(String cityName) {
        try {
            String response = client.get("http://weather/api/v2/cities/" + cityName);
            ObjectMapper om = new ObjectMapper();
            return om.readValue(response, Weather.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
