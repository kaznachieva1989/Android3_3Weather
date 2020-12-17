package com.example.android3_3weather.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android3_3weather.data.models.Example;
import com.example.android3_3weather.data.network.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    MutableLiveData<Example> mutableLiveData = new MutableLiveData<>();

        public void getWeatherData(){
            WeatherService.getInstance().getCurrentWeather("Bishkek", "4bbf5a1ed98cd9f688ebb3651474cdd2").enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    Log.d("mmm", "OnResponse" + response.body());
                    mutableLiveData.setValue(response.body());
                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    Log.d("mmm", "OnFailure" + t.getLocalizedMessage());
                }
            });

        }
}
