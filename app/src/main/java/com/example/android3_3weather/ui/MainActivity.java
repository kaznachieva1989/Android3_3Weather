package com.example.android3_3weather.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android3_3weather.R;
import com.example.android3_3weather.data.models.Example;
import com.example.android3_3weather.data.models.Weather;
import com.example.android3_3weather.databinding.ActivityMainBinding;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getWeatherData();

        Date currentDate = new Date(System.currentTimeMillis());
        DateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy");
        binding.currentDate.setText(dfDate.format(currentDate));
        Date currentTime = new Date(System.currentTimeMillis());
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        binding.currentTime.setText(dfTime.format(currentTime));

        mainViewModel.mutableLiveData.observe(this, new Observer<Example>() {
            @Override
            public void onChanged(Example example) {
                binding.cityName.setText(example.getSys().getCountry() + ", "+ example.getName());
                binding.textSunny.setText((CharSequence) example.getWeather().get(0).getMain());

                Double a = Double.parseDouble(String.valueOf(example.getMain().getTemp()));
                int b = (int) ((a - 75) * 5 / 9);
                binding.gradus.setText(String.valueOf(b + "°C"));
                binding.maxMean.setText(example.getMain().getTempMax().toString() + "°C");
                binding.minMean.setText(example.getMain().getTempMin().toString() + "°C");
                binding.textHumidity.setText(example.getMain().getHumidity().toString() + "%");
                binding.textUnderSpeed.setText(example.getMain().getPressure().toString() + "mBar");
                binding.textUnderWind.setText(example.getWind().getSpeed().toString() + "km/h");

                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                Date dateSet = new Date(example.getSys().getSunset());
                binding.textSunset.setText(df.format(dateSet) + "AM");
                Date dateSun = new Date(example.getSys().getSunrise());
                binding.textUnderSunrise.setText(df.format(dateSun) + "PM");

                SimpleDateFormat dfForDay = new SimpleDateFormat("HH'h' mm'm'");
                Date date = new Date(example.getDt());
                binding.textUnderClock.setText(dfForDay.format(date));
            }
        });
    }
}