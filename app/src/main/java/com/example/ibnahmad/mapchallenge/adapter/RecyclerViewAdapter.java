package com.example.ibnahmad.mapchallenge.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ibnahmad.mapchallenge.R;
import com.example.ibnahmad.mapchallenge.pojo.Location;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public static final String TAG = RecyclerViewAdapter.class.getSimpleName();
    private List<Location> locationList;
    private RecyclerViewAdapter.ClickListener clickListener;

    public RecyclerViewAdapter(ClickListener clickListener){
        this.clickListener = clickListener;
        locationList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Location location = locationList.get(position);
        viewHolder.txtCity.setText(location.getTitle());
        viewHolder.txtTypeDetail.setText(location.getLocationType());
        viewHolder.txtIdDetail.setText(String.valueOf(location.getWoeid()));
        viewHolder.txtLongLatDetail.setText(location.getLattLong());
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public void setData(List<Location> locations){
        this.locationList.addAll(locations);
        Log.d(TAG, String.valueOf(locations.size()));
        notifyDataSetChanged();
    }

    public interface ClickListener{
        void launchIntent(int id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtCity, txtTypeDetail, txtIdDetail, txtLongLatDetail;
        public CardView cardView;

        public ViewHolder(View view){
            super(view);

            txtCity = view.findViewById(R.id.txtName);
            txtTypeDetail = view.findViewById(R.id.txtTypeDetail);
            txtIdDetail = view.findViewById(R.id.txtIdDetail);
            txtLongLatDetail = view.findViewById(R.id.txtLongLatDetail);
            cardView = view.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.launchIntent(locationList.get(getAdapterPosition()).getWoeid());
                }
            });
        }
    }
}
