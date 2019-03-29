package com.example.ibnahmad.mapchallenge.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibnahmad.mapchallenge.R;
import com.example.ibnahmad.mapchallenge.pojo.ConsolidatedWeather;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    public static final String TAG = DetailAdapter.class.getSimpleName();
    private List<ConsolidatedWeather> detailList;

    public DetailAdapter(){
        detailList = new ArrayList<>();
    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_recycler_view_item,
                parent, false);
        DetailAdapter.ViewHolder viewHolder = new DetailAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder viewHolder, int position) {
        ConsolidatedWeather weather = detailList.get(position);
        viewHolder.dateDetail.setText(weather.getApplicableDate());
        viewHolder.stateDetail.setText(weather.getWeatherStateName());
        viewHolder.directionDetail.setText(weather.getWindDirectionCompass());
        viewHolder.humidityDetail.setText(String.valueOf(weather.getHumidity()));
        viewHolder.predictabilityDetail.setText(String.valueOf(weather.getPredictability()));
        viewHolder.minTempDetail.setText(String.valueOf(weather.getMinTemp()));
        viewHolder.maxTempDetail.setText(String.valueOf(weather.getMaxTemp()));
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public void setData(List<ConsolidatedWeather> weatherList){
        this.detailList.addAll(weatherList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView dateDetail, stateDetail, directionDetail,
        humidityDetail, predictabilityDetail, minTempDetail,
        maxTempDetail;

        public ViewHolder(View view){
            super(view);

            dateDetail = view.findViewById(R.id.dateDetail);
            stateDetail = view.findViewById(R.id.weatherStateDetail);
            directionDetail = view.findViewById(R.id.windDirectionDetail);
            humidityDetail = view.findViewById(R.id.humidityDetail);
            predictabilityDetail = view.findViewById(R.id.predictabilityDetail);
            minTempDetail = view.findViewById(R.id.minTempDetail);
            maxTempDetail = view.findViewById(R.id.maxTempDetail);
        }
    }
}
