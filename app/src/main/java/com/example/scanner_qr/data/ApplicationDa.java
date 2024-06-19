package com.example.scanner_qr.data;

import android.app.Application;

public class ApplicationDa extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
