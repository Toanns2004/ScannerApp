package com.example.scanner_qr.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.scanner_qr.models.MyQR;

import java.util.List;

@Dao
public interface MyQrDAO {
    @Insert
    void insertQr(MyQR myQR);

    @Query("SELECT * FROM myQR")
    List<MyQR> getListQr();

    @Query("SELECT * FROM myQR WHERE title = :t")
    List<MyQR> checkQr(String t);

    @Delete
    void deleteQr(MyQR qr);
}
