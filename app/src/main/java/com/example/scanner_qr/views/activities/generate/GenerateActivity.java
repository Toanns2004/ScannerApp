package com.example.scanner_qr.views.activities.generate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.scanner_qr.R;
import com.example.scanner_qr.databinding.ActivityGenerateBinding;
import com.example.scanner_qr.views.fragments.generate.layouts.GenerateFragment1;

public class GenerateActivity extends AppCompatActivity {


    private ActivityGenerateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityGenerateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_generate_create,new GenerateFragment1());
        fragmentTransaction.commit();

    }



}