package com.example.metromaps.utilities;

import android.content.Context;
import android.net.ConnectivityManager;

public class Connection {
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
