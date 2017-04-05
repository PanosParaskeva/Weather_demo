package com.example.panagiotis.weather_demo_app.Services;

import com.example.panagiotis.weather_demo_app.pojos.WeatherData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Panagiotis on 04/04/2017.
 */

public interface IData_Weather {

    @GET("forecast")
    Observable<WeatherData> getWeatherData
            (@Query("id") String id,
             @Query("appid") String appid);
}
