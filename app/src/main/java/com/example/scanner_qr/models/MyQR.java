package com.example.scanner_qr.models;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
@Entity(tableName = "myQR")
@TypeConverters(BitmapConverter.class)

public class MyQR implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String type;
    private String time;
    private Bitmap image;
    private String title;

    public MyQR(String type, String time, Bitmap image, String title) {
        this.type = type;
        this.time = time;
        this.image = image;
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
