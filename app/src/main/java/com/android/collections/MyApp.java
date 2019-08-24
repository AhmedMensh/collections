package com.android.collections;

import android.app.Application;

import com.android.collections.helpers.TypefaceUtil;

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(),
                "SERIF", "fonts/font_regular.ttf");
    }
}
