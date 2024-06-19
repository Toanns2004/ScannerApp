package com.example.scanner_qr.data;

import android.content.Context;

import com.example.scanner_qr.models.QR;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

public class DataLocalManager {

    private static final String FIRST_INSTALLED = "FIRST_INSTALLED";

    private static final String KEY_BUTTON = "KEY_BUTTON";
    private static DataLocalManager dataLocalManager;
    private SharedPreferencesData preferencesData;

    public static void init(Context context){
        dataLocalManager = new DataLocalManager();
        dataLocalManager.preferencesData = new SharedPreferencesData(context);
    }

    public static DataLocalManager getDataLocalManager(){
        if (dataLocalManager == null){
            dataLocalManager = new DataLocalManager();
        }
        return dataLocalManager;
    }

    public static void setFirstInstalled(boolean value){
        DataLocalManager.getDataLocalManager().preferencesData.setBoolean(FIRST_INSTALLED,value);
    }

    public static boolean getFirstInstalled(){
        return DataLocalManager.getDataLocalManager().preferencesData.getBoolean(FIRST_INSTALLED);
    }

    public static void setButtonType(String value){
            DataLocalManager.getDataLocalManager().preferencesData.setString(KEY_BUTTON,value);
        }

    public static String getButtonType(){
        return DataLocalManager.getDataLocalManager().preferencesData.getString(KEY_BUTTON);
    }


}
