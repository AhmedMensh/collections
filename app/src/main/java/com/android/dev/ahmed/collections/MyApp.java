package com.android.dev.ahmed.collections;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.helpers.TypefaceUtil;
import com.android.dev.ahmed.collections.ui.activties.start.StartActivity;

import java.util.Locale;

public class MyApp extends Application {

    private static final String TAG = "MyApp";
    private String language;

    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(),
                "SERIF", "fonts/font_regular.ttf");


        language = SharedPreferencesManager.getStringValue(this, Constants.LANGUAGE);




            if (language.equals(Constants.ENGLISH)) {
                setLanguage(Constants.ENGLISH);
//                Constants.WEBSERVICE_LANGUAGE = Constants.ENGLISH;
            } else {
                setLanguage(Constants.ARABIC);
//                Constants.WEBSERVICE_LANGUAGE = Constants.ARABIC;
            }
        }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        language = SharedPreferencesManager.getStringValue(this , Constants.LANGUAGE );

            if (language.equals(Constants.ENGLISH)) {
                setLanguage(Constants.ARABIC);
//                Constants.WEBSERVICE_LANGUAGE = Constants.ENGLISH;
            } else {
                setLanguage(Constants.ENGLISH);
//                Constants.WEBSERVICE_LANGUAGE = Constants.ARABIC;
            }
        }


    /**
     * setLanguage method changes the locale to user selected language
     * @param language the language to be set for the app
     */
    public void setLanguage(String language){
        Log.e(TAG, "setLanguage: "+language );
        Locale myLocale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        res.updateConfiguration(conf, dm);
    }
}

