package com.example.android3_3weather.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherService {
    private static WeatherApi weatherApi;

    private WeatherService() {
    }

    public static WeatherApi getInstance() {
        if (weatherApi == null) {
            weatherApi = buildRetrofit();
        }
        return weatherApi;
    }

    private static WeatherApi buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WeatherApi.class);
    }
}

