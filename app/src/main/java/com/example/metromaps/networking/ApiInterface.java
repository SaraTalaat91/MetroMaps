package com.example.metromaps.networking;

import com.example.metromaps.models.RowsModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("metro.json")
    Call<RowsModel> getMetroRoutes();
}