package com.example.panagiotis.weather_demo_app.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.panagiotis.weather_demo_app.Adapters.WeatherAdaptor;
import com.example.panagiotis.weather_demo_app.R;
import com.example.panagiotis.weather_demo_app.getWeather.IContract_Weather;
import com.example.panagiotis.weather_demo_app.getWeather.Weather_Presenter;
import com.example.panagiotis.weather_demo_app.pojos.WeatherData;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class Weather_view extends Fragment implements IContract_Weather.IView_DataWeather {

    private ProgressDialog pDialog;
    private IContract_Weather.IPresenter_DataWeather iPresenter_dataWeather;
    private WeatherAdaptor weatherAdaptor;

    @BindView(R.id.current_temperature_textView)
    TextView temperature;
    @BindView(R.id.Location_name_textView)
    TextView location;
    @BindView(R.id.RecyclerView)
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iPresenter_dataWeather=new Weather_Presenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_weather_view, container, false);
        ButterKnife.bind(this,view);
        iPresenter_dataWeather.getData("6058560");
        return view;
    }

    @Override
    public void setPresenter(IContract_Weather.IPresenter_DataWeather presenter) {
        this.iPresenter_dataWeather=checkNotNull(presenter);
    }

    @Override
    public void presentData(WeatherData weatherData) {
        location.setText("Town"+weatherData.getCity().getName());
        temperature.setText("Temperature :"+weatherData.getList().get(0).getMain().getTemp().toString());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        weatherAdaptor=new WeatherAdaptor(weatherData,R.layout.row_weather,getActivity());
        recyclerView.setAdapter(weatherAdaptor);
    }


    @Override
    public void onResume() {
        super.onResume();
        iPresenter_dataWeather.start();
    }

    @Override
    public void showProgressDialog() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if(pDialog.isShowing() || pDialog!=null){
            pDialog.dismiss();
        }
    }
}
