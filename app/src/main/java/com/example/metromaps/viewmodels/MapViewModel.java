package com.example.metromaps.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.metromaps.models.Route;
import com.example.metromaps.models.RoutesModel;
import com.example.metromaps.networking.ApiClient;
import com.example.metromaps.networking.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapViewModel extends ViewModel {

    private MutableLiveData<List<Route>> mRoutesLiveData = new MutableLiveData<>();

    public void getRoutesFromDb(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getMetroRoutes().enqueue(new Callback<RoutesModel>() {
            @Override
            public void onResponse(Call<RoutesModel> call, Response<RoutesModel> response) {
                List<Route> routes = response.body().getRoutes();
                mRoutesLiveData.setValue(routes);
                Log.d("MapViewModel", "onResponse: Metro routes fetched successfully!");
            }

            @Override
            public void onFailure(Call<RoutesModel> call, Throwable t) {
                Log.d("MapViewModel", "onFailure: Something went wrong!");
            }
        });
    }

    public LiveData<List<Route>> getRoutesLiveData(){
        return mRoutesLiveData;
    }
}
