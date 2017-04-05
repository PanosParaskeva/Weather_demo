package com.example.panagiotis.weather_demo_app.getWeather;

import com.example.panagiotis.weather_demo_app.MVP.BasedPresenter;
import com.example.panagiotis.weather_demo_app.MVP.BasedView;
import com.example.panagiotis.weather_demo_app.pojos.WeatherData;


public interface IContract_Weather {
    public interface IPresenter_DataWeather extends BasedPresenter {
        public void getData(String location);
    }

    public interface IView_DataWeather extends BasedView<IPresenter_DataWeather> {
        public void presentData(WeatherData weatherData);
        public void showProgressDialog();
        public void dismissProgressDialog();

    }
}
