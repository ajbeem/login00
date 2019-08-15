package com.pcentaury.pcentaurylogin00;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Services {
    static boolean NetworkConnection(Context context) {
        boolean isConnected;
        ConnectivityManager cm =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
            isConnected = true;
        }else {
            isConnected = false;
        }
        return isConnected;
    }
}
