package com.example.scanner_qr.views.activities.myqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.scanner_qr.R;
import com.example.scanner_qr.databinding.ActivityCheckQrBinding;
import com.example.scanner_qr.models.MyQR;
import com.example.scanner_qr.models.QR;

public class CheckQrActivity extends AppCompatActivity {

    private ActivityCheckQrBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckQrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDataMyQr();

        binding.btnBackCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getDataMyQr() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("MY_QR");
        if (bundle!= null){
            String type = bundle.getString("Type");
            String title = bundle.getString("Title");
            Uri image = Uri.parse(bundle.getString("Image"));

            binding.textType.setText(type);
            binding.textView.setText(title);
            binding.imageQr.setImageURI(image);
        }

    }
}