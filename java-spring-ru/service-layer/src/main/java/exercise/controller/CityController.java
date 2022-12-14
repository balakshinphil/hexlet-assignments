package exercise.controller;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.model.Weather;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import liquibase.pro.packaged.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/cities/{id}")
    public Weather getWeatherForCity(@PathVariable Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(String.format("City with id: %s not found", id)));
        return weatherService.getWeatherForCity(city.getName());
    }

    @GetMapping("/search")
    public List<Weather> searchWeather(@RequestParam(value = "name", required = false) String name) {
        List<City> cities;
        if (name != null) {
            cities = cityRepository.findByNameStartsWithIgnoreCase(name);
        } else {
            cities = cityRepository.findAllByOrderByNameAsc();
        }

        return cities.stream().map(city -> weatherService.getWeatherForCity(city.getName())).toList();
    }
}

