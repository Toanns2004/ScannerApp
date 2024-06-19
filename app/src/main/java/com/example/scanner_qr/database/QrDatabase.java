package com.example.scanner_qr.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.scanner_qr.models.MyQR;
import com.example.scanner_qr.models.QR;

@Database(entities = {QR.class, MyQR.class},version = 1)
public abstract class QrDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "qr.database";
    private static QrDatabase instance;

    public static synchronized QrDatabase getInstance(Context context){
        if (instance ==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),QrDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract QrDAO qrDAO();
    public abstract MyQrDAO myQrDAO();
}
