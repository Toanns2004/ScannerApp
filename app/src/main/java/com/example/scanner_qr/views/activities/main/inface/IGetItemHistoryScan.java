package com.example.scanner_qr.views.activities.main.inface;

import com.example.scanner_qr.models.QR;

public interface IGetItemHistoryScan {
    void getItemRemove(QR qr);
    void getItem(QR qr);

    void getItemShare(QR qr);
}
