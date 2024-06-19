package com.example.scanner_qr.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesData {
    private static final String SHARED_PREFERENCES_DATA ="SHARED_PREFERENCES_DATA";
    private Context context;

    public SharedPreferencesData(Context context){
        this.context = context;
    }

    public void setBoolean(String key, boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }


    public boolean getBoolean(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_DATA,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }

    public void setString(String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public String getString(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_DATA,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

    public void setInt(String key, int value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.apply();
    }

    public int getInt(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_DATA,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,15000);
    }


}
