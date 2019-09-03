package es.us.myapplication;

import android.app.Application;
import android.content.Context;


public class GlobalApp extends Application {

    private static Context appContext;

    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static Context getAppContext() {
        return appContext.getApplicationContext();
    }
}