package com.example.scanner_qr.views.fragments.mains;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanner_qr.R;
import com.example.scanner_qr.databinding.FragmentHistoryScanBinding;
import com.example.scanner_qr.views.fragments.history.GenerateHistoryFragment;
import com.example.scanner_qr.views.fragments.history.ScanHistoryFragment;


public class HistoryScanFragment extends Fragment {


    private FragmentHistoryScanBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHistoryScanBinding.inflate(inflater, container, false);
        replaceFragment(new ScanHistoryFragment());
        customBtnScan();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnScanHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customBtnScan();

                replaceFragment(new ScanHistoryFragment());
            }
        });

        binding.btnGenerateHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customBtnGenerate();
                replaceFragment(new GenerateHistoryFragment());
            }
        });
    }

    private void customBtnScan() {
        binding.btnScanHistory.setBackgroundResource(R.drawable.custom_btn_continue);
        binding.btnGenerateHistory.setBackgroundResource(R.drawable.custom_btn_continue_unclick);

        binding.btnScanHistory.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        binding.btnGenerateHistory.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));

    }

    private void customBtnGenerate() {
        binding.btnGenerateHistory.setBackgroundResource(R.drawable.custom_btn_continue);
        binding.btnScanHistory.setBackgroundResource(R.drawable.custom_btn_continue_unclick);

        binding.btnGenerateHistory.setTextColor(ContextCompat.getColor(requireContext(), R.color.white));
        binding.btnScanHistory.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));

    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_history_layout,fragment);
        fragmentTransaction.commit();
    }

}