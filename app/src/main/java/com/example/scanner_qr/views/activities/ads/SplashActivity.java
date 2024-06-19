package com.example.scanner_qr.views.activities.ads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.scanner_qr.R;
import com.example.scanner_qr.data.DataLocalManager;
import com.example.scanner_qr.databinding.ActivitySplashBinding;
import com.example.scanner_qr.views.activities.frames.FrameActivity;
import com.example.scanner_qr.views.activities.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.lottieAnimationView.playAnimation();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (DataLocalManager.getFirstInstalled()){
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }else {
                    intent = new Intent(SplashActivity.this, FrameActivity.class);

                }
                startActivity(intent);
                finish();
            }
        },2500);
    }
}