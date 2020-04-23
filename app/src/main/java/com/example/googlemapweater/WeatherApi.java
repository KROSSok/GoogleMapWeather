package com.example.googlemapweater;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class WeatherApi {

    public static String APP_ID = "aa9129410495346cb551bf6e9e3aa5fe";
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static Retrofit retrofit = null;

    public interface WeatherInterface {
        @GET("weather?")
        Call<WeatherResponse> getToday(
                @Query("lon") double lon,
                @Query("lat") double lat,
                @Query("appid") String appId
        );
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
