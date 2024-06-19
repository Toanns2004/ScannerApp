package com.example.scanner_qr.views.activities.vip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.scanner_qr.R;
import com.example.scanner_qr.databinding.ActivityVipBinding;

public class VipActivity extends AppCompatActivity {

    private ActivityVipBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBackVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.cardVip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCard1();
            }
        });
        binding.cardVip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCard2();
            }
        });
        binding.cardVip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCard3();
            }
        });
        binding.buttonTrialNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipActivity.this,VipBuyedActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clickCard1() {
        binding.cardVip1.setBackgroundResource(R.drawable.custom_rectangle_vip_click);
        binding.cardVip1.setAlpha(1);
        binding.cardVip2.setBackgroundResource(R.drawable.custom_rectangle_vip);
        binding.cardVip2.setAlpha(0.5f);
        binding.cardVip3.setBackgroundResource(R.drawable.custom_rectangle_vip);
        binding.cardVip3.setAlpha(0.5f);
        binding.linearTrialNow.setVisibility(View.VISIBLE);
    }
    private void clickCard2() {
        binding.cardVip1.setBackgroundResource(R.drawable.custom_rectangle_vip);
        binding.cardVip1.setAlpha(0.5f);
        binding.cardVip2.setBackgroundResource(R.drawable.custom_rectangle_vip_click);
        binding.cardVip2.setAlpha(1);
        binding.cardVip3.setBackgroundResource(R.drawable.custom_rectangle_vip);
        binding.cardVip3.setAlpha(0.5f);
        binding.linearTrialNow.setVisibility(View.VISIBLE);
    }
    private void clickCard3() {
        binding.cardVip1.setBackgroundResource(R.drawable.custom_rectangle_vip);
        binding.cardVip1.setAlpha(0.5f);
        binding.cardVip2.setBackgroundResource(R.drawable.custom_rectangle_vip);
        binding.cardVip2.setAlpha(0.5f);
        binding.cardVip3.setBackgroundResource(R.drawable.custom_rectangle_vip_click);
        binding.cardVip3.setAlpha(1);
        binding.linearTrialNow.setVisibility(View.VISIBLE);
    }
}