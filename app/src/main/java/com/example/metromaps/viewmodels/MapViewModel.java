package com.example.metromaps.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.metromaps.models.Row;
import com.example.metromaps.models.RowsModel;
import com.example.metromaps.networking.ApiClient;
import com.example.metromaps.networking.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapViewModel extends ViewModel {

    private MutableLiveData<List<Row>> mRowsLiveData = new MutableLiveData<>();

    public void getRoutesFromDb(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getMetroRoutes().enqueue(new Callback<RowsModel>() {
            @Override
            public void onResponse(Call<RowsModel> call, Response<RowsModel> response) {
                List<Row> rowsList = response.body().getRows();
                mRowsLiveData.setValue(rowsList);
                Log.d("MapViewModel", "onResponse: Metro routes fetched successfully!");
            }

            @Override
            public void onFailure(Call<RowsModel> call, Throwable t) {
                Log.d("MapViewModel", "onFailure: Something went wrong!");
            }
        });
    }

    public LiveData<List<Row>> getRowsLiveData(){
        return mRowsLiveData;
    }
}
