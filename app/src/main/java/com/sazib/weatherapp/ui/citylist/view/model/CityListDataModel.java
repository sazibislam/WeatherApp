package com.sazib.weatherapp.ui.citylist.view.model;

public class CityListDataModel {

    private String cityName;
    private String weatherType;
    private String temperature;

    public CityListDataModel(String cityName, String weatherType, String temperature) {
        this.cityName = cityName;
        this.weatherType = weatherType;
        this.temperature = temperature;
    }

    public String getName() {
        return cityName;
    }

    public void setName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

}
