package com.example.metromaps.utilities;

public class MapUtils {

    public static String[] splitLatLngString(String latLngString) {
        return latLngString.split(",");
    }

    public static int calculatePadding(int height) {
        return  (int) (height * 0.1);
    }
}

