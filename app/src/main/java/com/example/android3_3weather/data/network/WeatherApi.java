package com.example.android3_3weather.data.network;

import com.example.android3_3weather.data.models.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather")
    Call<Example> getCurrentWeather(
            @Query("q") String cityName,
            @Query("appid") String apiKey
    );
}
