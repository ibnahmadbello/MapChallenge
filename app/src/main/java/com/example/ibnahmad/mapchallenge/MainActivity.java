package com.example.ibnahmad.mapchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ibnahmad.mapchallenge.adapter.RecyclerViewAdapter;
import com.example.ibnahmad.mapchallenge.pojo.Location;
import com.example.ibnahmad.mapchallenge.retrofit.MapApi;
import com.example.ibnahmad.mapchallenge.retrofit.MapService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    public RecyclerView recyclerView;
    public RecyclerViewAdapter recyclerViewAdapter;
    private MapService mapService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        mapService = MapApi.getRetrofit(this).create(MapService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint("Enter your location search here.");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mapService.getCityList(s).enqueue(new Callback<List<Location>>() {
                    @Override
                    public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                        List<Location> locations = response.body();
                        recyclerViewAdapter.setData(locations);
                    }

                    @Override
                    public void onFailure(Call<List<Location>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error loading your search. Try again!", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.d(TAG, "First check = " + s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        return true;
    }


    @Override
    public void launchIntent(int id) {
        startActivity(new Intent(this, DetailActivity.class).putExtra("woeid", id));
    }
}
