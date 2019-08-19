package com.example.metromaps;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import com.example.metromaps.models.Route;
import com.example.metromaps.utilities.MapUtils;
import com.example.metromaps.viewmodels.MapViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int CAMERA_PADDING = 150;
    private GoogleMap mMap;
    private MapViewModel mapViewModel;
    private LiveData<List<Route>> mRoutesLiveData;
    private PolylineOptions mPolylineOptions;
    private LatLngBounds.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setupViewModel();
        setupObservers();
        mapViewModel.getRoutesFromDb();
    }

    private void setupObservers() {
        mRoutesLiveData.observe(this, new Observer<List<Route>>() {
            @Override
            public void onChanged(List<Route> routes) {
                if (routes != null) {
                    mPolylineOptions = new PolylineOptions().color(Color.RED);
                    mBuilder = new LatLngBounds.Builder();
                    for (Route route : routes) {
                        drawRouteOnMap(route);
                    }
                    mMap.addPolyline(mPolylineOptions);
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(mBuilder.build(), CAMERA_PADDING);
                    mMap.animateCamera(cameraUpdate);
                }
            }
        });
    }

    private void drawRouteOnMap(Route route) {
        String[] latLngStringArray = MapUtils.splitLatLngString(route.getDestinationLongLat().get(0));
        double latitude = Double.parseDouble(latLngStringArray[0]);
        double longitude = Double.parseDouble(latLngStringArray[1]);
        LatLng routeLatLng = new LatLng(latitude, longitude);
        mPolylineOptions.add(routeLatLng);
        mBuilder.include(routeLatLng);
        mMap.addMarker(new MarkerOptions().position(routeLatLng).title(route.getTitle()));
    }

    private void setupViewModel(){
        mapViewModel = ViewModelProviders.of(this).get(MapViewModel.class);
        mRoutesLiveData = mapViewModel.getRoutesLiveData();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}
