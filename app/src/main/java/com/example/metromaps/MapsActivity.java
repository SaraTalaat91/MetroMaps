package com.example.metromaps;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.metromaps.models.Row;
import com.example.metromaps.viewmodels.MapViewModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapViewModel mapViewModel;
    private LiveData<List<Row>> mRowsLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setupViewModel();
        setupObservers();
        mapViewModel.getRoutesFromDb();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setupObservers() {
        mRowsLiveData.observe(this, new Observer<List<Row>>() {
            @Override
            public void onChanged(List<Row> rows) {
                if (rows != null) {
                    for (Row row : rows) {

                    }
                }
            }
        });
    }

    private void setupViewModel(){
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        mRowsLiveData = mapViewModel.getRowsLiveData();
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}
