package com.example.metromaps.Networking;

import com.example.metromaps.Models.RowsModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("metro.json")
    Call<RowsModel> getMetroRoutes();
}