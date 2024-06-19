package com.example.scanner_qr.views.fragments.mains;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanner_qr.R;
import com.example.scanner_qr.databinding.FragmentPersonalBinding;
import com.example.scanner_qr.views.activities.vip.VipActivity;

public class PersonalFragment extends Fragment {


    private FragmentPersonalBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentPersonalBinding.inflate(inflater, container, false);

        binding.banerTryNowVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), VipActivity.class);
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
}