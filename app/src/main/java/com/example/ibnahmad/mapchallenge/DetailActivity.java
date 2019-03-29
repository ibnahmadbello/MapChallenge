package com.example.ibnahmad.mapchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ibnahmad.mapchallenge.adapter.DetailAdapter;
import com.example.ibnahmad.mapchallenge.pojo.ConsolidatedWeather;
import com.example.ibnahmad.mapchallenge.pojo.DetailResponse;
import com.example.ibnahmad.mapchallenge.retrofit.MapApi;
import com.example.ibnahmad.mapchallenge.retrofit.MapService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private MapService mapService;
    private RecyclerView recyclerView;
    private DetailAdapter detailAdapter;
    private ProgressBar detailProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = findViewById(R.id.detail_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailAdapter = new DetailAdapter();
        recyclerView.setAdapter(detailAdapter);

        detailProgressBar = findViewById(R.id.detail_progress_bar);

        mapService = MapApi.getRetrofit(this).create(MapService.class);

        int woeid = getIntent().getIntExtra("woeid", 0);

        makeCityRequest(woeid);
    }

    private void makeCityRequest(final int cityId) {
//        Toast.makeText(DetailActivity.this, String.valueOf(cityId), Toast.LENGTH_SHORT).show();
        mapService.getCityDetail(cityId).enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                detailProgressBar.setVisibility(View.GONE);
                List<ConsolidatedWeather> weathers = fetchResults(response);
                detailAdapter.setData(weathers);
            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "Error loading response");
            }
        });
    }

    private List<ConsolidatedWeather> fetchResults(Response<DetailResponse> response) {
        DetailResponse detailResponse = response.body();
        return detailResponse.getConsolidatedWeather();
    }


}
