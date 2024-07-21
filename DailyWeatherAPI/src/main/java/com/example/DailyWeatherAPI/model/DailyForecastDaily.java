package com.example.DailyWeatherAPI.model;

public class DailyForecastDaily {
	

    private String dayName;
    private String tempHighCelsius;
    private String forecastBlurb;
    
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public String getTempHighCelsius() {
		return tempHighCelsius;
	}
	public void setTempHighCelsius(String tempHighCelsius) {
		this.tempHighCelsius = tempHighCelsius;
	}
	public String getForecastBlurb() {
		return forecastBlurb;
	}
	public void setForecastBlurb(String forecastBlurb) {
		this.forecastBlurb = forecastBlurb;
	}


}
