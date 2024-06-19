package com.example.scanner_qr.views.activities.main.inface;

import com.example.scanner_qr.models.MyQR;
import com.example.scanner_qr.models.QR;

public interface IGetItemGenerateHistory {
    void getItemRemove(MyQR qr);
    void getItem(MyQR qr);

    void getItemShare(MyQR qr);
}
