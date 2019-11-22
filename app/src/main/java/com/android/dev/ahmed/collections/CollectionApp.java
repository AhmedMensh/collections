package com.android.dev.ahmed.collections;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebView;

import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.ui.activties.home.HomeActivity;
import com.google.firebase.FirebaseApp;

import java.util.Locale;

public class CollectionApp extends Application {

    private static final String TAG = "CollectionApp";
    private static Context context;
    private static String language;
    private static String macAddress;
    private static boolean isRegisterd = false;
    private static int userId;

    @Override
    public void onCreate() {
        super.onCreate();

        new WebView(this).destroy();
        context = this;

        language = SharedPreferencesManager.getStringValue(this, Constants.LANGUAGE);
        userId = SharedPreferencesManager.getIntValue(this, Constants.USER_ID);

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
         macAddress = wInfo.getMacAddress();

         isRegisterd =  SharedPreferencesManager.getBooleanValue(this , Constants.IS_REGISTERD);

        Log.e(TAG, "onCreate: "+macAddress );
    }

    public static boolean isIsRegisterd(){
        return isRegisterd;
    }
    public static Context getContext() {
        return context;
    }

    public static String getUserId() {
        return userId+"";
    }

    public static String getLanguage(){

        if (language.equals(""))
            return "en";
        return language;
    }

    public static String getMacAddress() {
        return macAddress;
    }
    //    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        language = SharedPreferencesManager.getStringValue(this, Constants.LANGUAGE);
//        if (language != null) {
//            if (language.equals(Constants.ENGLISH)) {
//                setLanguage(Constants.ENGLISH);
////                Constants.WEBSERVICE_LANGUAGE = Constants.ENGLISH;
//            } else {
//                setLanguage(Constants.ARABIC);
////                Constants.WEBSERVICE_LANGUAGE = Constants.ARABIC;
//            }
//        }
//    }

//    public void setLanguage(String language) {
//        Log.e(TAG, "setLanguage:  "+language );
//        Locale locale = new Locale(language);
//        locale.setDefault(locale);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.setLocale(locale);
//        res.updateConfiguration(conf, dm);
//    }
}

