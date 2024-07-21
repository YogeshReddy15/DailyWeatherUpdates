package com.example.DailyWeatherAPI.service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.DailyWeatherAPI.controller.DailyWeatherController;
import com.example.DailyWeatherAPI.model.DailyForecast;
import com.example.DailyWeatherAPI.model.DailyForecastDaily;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Value("${weather.api.url}")
	private String weatherApiUrl;

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	ObjectMapper objectMapper;

	private static final Logger logger = LogManager.getLogger(DailyWeatherController.class);

	@Override
	public DailyForecast getDailyForecast() {
		// TODO Auto-generated method stub

		DailyForecast response = new DailyForecast();

		DailyForecastDaily dailyForecastDaily = new DailyForecastDaily();

		JsonNode weatherApiResponseJsonObject;

		try {

			logger.info("Fetching Daily Forcast weather details using API -" + weatherApiUrl);

			String weatherApiResponse = restTemplate.getForObject(weatherApiUrl, String.class);

			if (weatherApiResponse != null) {
				
				logger.info("Response Fetched from API -" + weatherApiResponse);
				
				weatherApiResponseJsonObject = objectMapper.readTree(weatherApiResponse);

				if (weatherApiResponseJsonObject != null) {

					logger.info("Response Fetched from API -" + weatherApiResponseJsonObject.toString());

					JsonNode properties = weatherApiResponseJsonObject.get("properties");

					if (properties != null) {
						JsonNode periods = properties.get("periods");

						if (periods != null && periods.isArray() && periods.size() > 0) {
							JsonNode currentDayForecast = periods.get(0); // Assuming the first entry is for current day
							/*current day name check 2024-07-21T06:00:00-04:00*/
							
							String dateString = currentDayForecast.get("startTime").asText();
							ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString);
							String dayName = zonedDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
						 
							//String dayName = currentDayForecast.get("name").asText();
							double tempHighCelsiusindouble = fahrenheitToCelsius(
									currentDayForecast.get("temperature").asDouble());
							String tempHighCelsius = String.format("%.2f", tempHighCelsiusindouble);
							String forecastBlurb = currentDayForecast.get("shortForecast").asText();

							if (dayName != null && !dayName.isEmpty()) {
								dailyForecastDaily.setDayName(dayName);
							}
							if (tempHighCelsius != null && !tempHighCelsius.isEmpty()) {
								dailyForecastDaily.setTempHighCelsius(tempHighCelsius);
							}
							if (forecastBlurb != null && !forecastBlurb.isEmpty()) {
								dailyForecastDaily.setForecastBlurb(forecastBlurb);
							}
						}
					}

					ArrayList<DailyForecastDaily> dailyForecastDailyList = new ArrayList<DailyForecastDaily>();
					dailyForecastDailyList.add(dailyForecastDaily);
					response.setDaily(dailyForecastDailyList);
				} else {
					logger.info("No data Fetched from parcing the object received from API -"
							+ weatherApiResponseJsonObject);
				}
			} else {

				logger.info("No data Fetched from API -" + weatherApiResponse);
			}

		} catch (Exception exception) {

			logger.info("Exception while Fetching from API -" + exception);

			return response;
		}

		return response;
	}

	private double fahrenheitToCelsius(double fahrenheit) {

		logger.info("Converting temperature from fahrenheit to celsius -" + fahrenheit + "F");

		return (fahrenheit - 32) * 5 / 9;
	}

}
