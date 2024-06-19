package com.example.scanner_qr.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.scanner_qr.models.QR;

import java.util.List;

@Dao
public interface QrDAO {
    @Insert
     void insertQr(QR qr);

    @Query("SELECT * FROM qr")
    List<QR> getListQr();

    @Query("SELECT * FROM qr WHERE title = :t")
    List<QR> checkQr(String t);

    @Delete
    void deleteQr(QR qr);


}
