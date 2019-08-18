package com.example.metromaps.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Row {

@SerializedName("title")
@Expose
private String title;
@SerializedName("destination_Long_Lat")
@Expose
private List<String> destinationLongLat = null;

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public List<String> getDestinationLongLat() {
return destinationLongLat;
}

public void setDestinationLongLat(List<String> destinationLongLat) {
this.destinationLongLat = destinationLongLat;
}

}