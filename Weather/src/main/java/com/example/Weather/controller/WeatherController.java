package com.example.Weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final String API_URL = "https://api.open-meteo.com/v1/forecast";

    @GetMapping("/weather")
    public String getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + "?latitude=" + latitude + "&longitude=" + longitude;
        return restTemplate.getForObject(url, String.class);

    }


}
