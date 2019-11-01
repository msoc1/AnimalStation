package com.fixed4fun.android.animalstation.utilities;

import android.app.Application;
import android.content.Context;

public class AnimalStation extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        AnimalStation.context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
