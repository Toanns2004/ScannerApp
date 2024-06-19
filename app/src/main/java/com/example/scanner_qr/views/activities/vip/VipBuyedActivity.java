package com.example.scanner_qr.views.activities.vip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.scanner_qr.R;
import com.example.scanner_qr.databinding.ActivityVipBuyedBinding;
import com.example.scanner_qr.views.activities.generate.GenerateActivity;
import com.example.scanner_qr.views.activities.scaming.ScamingActivity;

public class VipBuyedActivity extends AppCompatActivity {

    private ActivityVipBuyedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVipBuyedBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnNewQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipBuyedActivity.this, GenerateActivity.class);
                startActivity(intent);
            }
        });

        binding.btnScanQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipBuyedActivity.this, ScamingActivity.class);
                startActivity(intent);
            }
        });
    }
}