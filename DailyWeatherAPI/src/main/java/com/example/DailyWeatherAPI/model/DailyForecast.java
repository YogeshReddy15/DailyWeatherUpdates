package com.example.DailyWeatherAPI.model;

import java.util.ArrayList;

public class DailyForecast {
	
	private ArrayList<DailyForecastDaily> daily;

	public ArrayList<DailyForecastDaily> getDaily() {
		return daily;
	}

	public void setDaily(ArrayList<DailyForecastDaily> daily) {
		this.daily = daily;
	}
	    
}
