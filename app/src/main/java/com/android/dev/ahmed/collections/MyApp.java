package com.android.dev.ahmed.collections;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebView;

import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.helpers.TypefaceUtil;
import com.android.dev.ahmed.collections.ui.activties.start.StartActivity;

import java.util.Locale;

public class MyApp extends Application {

    private static final String TAG = "MyApp";
    private  static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        new WebView(this).destroy();
        context = this;
        TypefaceUtil.overrideFont(getApplicationContext(),
                "SERIF", "fonts/font_regular.ttf");



        }


    public static Context getContext() {
        return context;
    }
}

