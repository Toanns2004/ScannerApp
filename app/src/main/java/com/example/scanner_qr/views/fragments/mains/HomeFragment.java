package com.example.scanner_qr.views.fragments.mains;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanner_qr.R;
import com.example.scanner_qr.databinding.FragmentHomeBinding;
import com.example.scanner_qr.views.activities.generate.GenerateActivity;
import com.example.scanner_qr.views.activities.main.MainActivity;
import com.example.scanner_qr.views.activities.scaming.ScamingActivity;
import com.example.scanner_qr.views.activities.vip.VipActivity;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.iconScanQrHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScamingActivity.class);
                startActivity(intent);
            }
        });

        binding.iconCreateQrHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GenerateActivity.class);
                startActivity(intent);
            }
        });

        binding.iconMyQrHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        binding.bannerHomeFra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), VipActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }


}