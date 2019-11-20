package com.android.dev.ahmed.collections;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebView;

import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.ui.activties.home.HomeActivity;
import com.google.firebase.FirebaseApp;

import java.util.Locale;

public class MyApp extends Application {

    private static final String TAG = "MyApp";
    private static Context context;
    private String language;

    @Override
    public void onCreate() {
        super.onCreate();

        new WebView(this).destroy();
        context = this;
//        FirebaseApp.initializeApp(getApplicationContext());

        language = SharedPreferencesManager.getStringValue(this, Constants.LANGUAGE);



//        if( Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            //Wrapping the configuration to avoid Activity endless loop
//            Configuration config = new Configuration(configuration);
//            config.locale = sLocale;
//            Resources res = app.getBaseContext().getResources();
//            res.updateConfiguration(config, res.getDisplayMetrics());
//        }


//        if (language.equals(Constants.ENGLISH)) {
//            setLanguage(Constants.ENGLISH);
////                Constants.WEBSERVICE_LANGUAGE = Constants.ENGLISH;
//        } else {
//            setLanguage(Constants.ARABIC);
////                Constants.WEBSERVICE_LANGUAGE = Constants.ARABIC;
//        }

    }

    public static Context getContext() {
        return context;
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

