package DatabaseAndConnectors;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


// The purpose of this class is to check if the internet is connected.
// If the internet connection needs to be checeked in other activities, then this java class will be called there.
public class CheckInternetConnection {

    public boolean isConnected(Context context)
    {
        Log.d("Login.java", "isConnected: Entered isConnected method");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifiConnection != null && wifiConnection.isConnected()) || (mobileConnection != null && mobileConnection.isConnected()))
        {
            return true;
        }
        else {
            return false;
        }
    }
}
