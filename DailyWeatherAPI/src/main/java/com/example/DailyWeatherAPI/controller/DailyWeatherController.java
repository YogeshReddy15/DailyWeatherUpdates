package com.example.DailyWeatherAPI.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DailyWeatherAPI.model.DailyForecast;
import com.example.DailyWeatherAPI.service.WeatherService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/getWeather")
@Api(tags = "Daily Weather API")
public class DailyWeatherController {
	
	@Autowired
    private WeatherService weatherService;
	
	private static final Logger logger = LogManager.getLogger(DailyWeatherController.class);
	
	@GetMapping("/dailyForecast")
    public ResponseEntity<Object> getDailyForecast(@RequestParam double latitude, @RequestParam double longitude) {
		
		logger.info("Started Daily Forcast Weather Controller call");
		
        DailyForecast dailyForecast = weatherService.getDailyForecast(latitude,longitude);

        if(dailyForecast.getDaily() !=null) {
        	
            return new ResponseEntity<>(dailyForecast, HttpStatus.OK);
         }
         
         else {
         	
            return new ResponseEntity<>(dailyForecast.getErrorMessage(), HttpStatus.BAD_REQUEST);
         }
    }

}
