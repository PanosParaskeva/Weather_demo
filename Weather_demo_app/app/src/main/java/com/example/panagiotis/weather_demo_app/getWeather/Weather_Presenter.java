package com.example.panagiotis.weather_demo_app.getWeather;

import com.example.panagiotis.weather_demo_app.Constants.Constants;
import com.example.panagiotis.weather_demo_app.Services.IData_Weather;
import com.example.panagiotis.weather_demo_app.Services.Weather_connection;
import com.example.panagiotis.weather_demo_app.pojos.WeatherData;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Weather_Presenter implements IContract_Weather.IPresenter_DataWeather {

    IContract_Weather.IView_DataWeather iView_dataWeather;
    IData_Weather iData_weather;

    public Weather_Presenter(IContract_Weather.IView_DataWeather iView_dataWeather) {
        this.iView_dataWeather = iView_dataWeather;
    }


    @Override
    public void getData(String location) {
        iView_dataWeather.showProgressDialog();


        iData_weather= Weather_connection.getWeatherConnection();


        iData_weather.getWeatherData(location,
                Constants.Api_key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherData>() {
                    @Override
                    public void onCompleted() {
                        iView_dataWeather.dismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView_dataWeather.dismissProgressDialog();

                    }

                    @Override
                    public void onNext(WeatherData weatherData) {
                        iView_dataWeather.presentData(weatherData);

                    }
                });
    }

    @Override
    public void start() {
        iView_dataWeather.setPresenter(this);
    }
}
