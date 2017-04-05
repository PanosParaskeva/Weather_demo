package com.example.panagiotis.weather_demo_app.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panagiotis.weather_demo_app.R;
import com.example.panagiotis.weather_demo_app.pojos.WeatherData;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherAdaptor extends RecyclerView.Adapter<WeatherAdaptor.ViewHolder> {

    private WeatherData weatherData;
    private int row;
    private Context context;
    String d="";
    public WeatherAdaptor(WeatherData weatherData, int row, Context context) {
        this.weatherData = weatherData;
        this.row = row;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(row,parent,false);
        return new WeatherAdaptor.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String icon_main_url="http://openweathermap.org/img/w/";
        holder.date.setText("Date :"+weatherData.getList().get(position).getDtTxt());
        holder.desc.setText("Description :"+weatherData.getList().get(position).getWeather().get(0).getDescription());

        Picasso.with(context)
                .load(icon_main_url+weatherData.getList().get(position).getWeather().get(0).getIcon()+".png")
                .resize(164, 164)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return weatherData.getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_Date_textView)
        TextView date;
        @BindView(R.id.row_Time_textView)
        TextView time;
        @BindView(R.id.row_Desc_textView)
        TextView desc;
        @BindView(R.id.row_ImageView)
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
