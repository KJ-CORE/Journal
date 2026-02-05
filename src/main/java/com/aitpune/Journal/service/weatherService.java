package com.aitpune.Journal.service;

import com.aitpune.Journal.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class weatherService {

    private static final  String APIKey ="65d86a6fc02e13cef3e8a2788fe5add7";

    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String City){
        String finalAPI = API.replace("CITY",City).replace("API_KEY",APIKey);
        ResponseEntity<> response = restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
