package com.example.DailyWeatherAPI.model;

import java.util.ArrayList;

public class DailyForecast {
	
	private ArrayList<DailyForecastDaily> daily;
	
	private ErrorMessage errorMessage;

	public ArrayList<DailyForecastDaily> getDaily() {
		return daily;
	}

	public void setDaily(ArrayList<DailyForecastDaily> daily) {
		this.daily = daily;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
	    
	
}
