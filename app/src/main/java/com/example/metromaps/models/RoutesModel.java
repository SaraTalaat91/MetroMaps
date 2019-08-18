package com.example.metromaps.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoutesModel {

@SerializedName("rows")
@Expose
private List<Route> routes = null;

public List<Route> getRoutes() {
return routes;
}

public void setRoutes(List<Route> routes) {
this.routes = routes;
}

}