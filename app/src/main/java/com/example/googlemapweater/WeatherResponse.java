package com.example.googlemapweater;

import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

public class WeatherResponse {

    @SerializedName("weather")
    private List<Weather> main;

    @SerializedName("main")
    private Main temp;

    @SerializedName("name")
    private String city;

    public Double getTemp() {
        Double temperature = null;
        try {
            temperature = (new DecimalFormat("###.##").parse(String.valueOf(temp.temp-273.15)).doubleValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return temperature;
    }

    public String getWeather() { return main.get(0).main; }

    public String getCity() { return city; }

}
class Weather {
    public String main;
}

class Main {
    public double temp;
}



//class WeatherResp {
//    private Coord coord,
//    private Weather weather,
//
//  ...,
//    і так далі
//
//}