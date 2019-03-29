package com.example.ibnahmad.mapchallenge.retrofit;

import com.example.ibnahmad.mapchallenge.pojo.ConsolidatedWeather;
import com.example.ibnahmad.mapchallenge.pojo.Location;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MapService {

    @GET("api/location/search/?")
    Call<List<Location>> getCityList(@Query("query") String query);

    @GET("api/location/{woeid}")
    Call<ConsolidatedWeather> getCityDetail(@Path("woeid") int woeid);

}
