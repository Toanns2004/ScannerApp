package com.example.scanner_qr.views.fragments.mains;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanner_qr.databinding.FragmentGenerateBinding;


public class GenerateFragment extends Fragment {
    private FragmentGenerateBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentGenerateBinding.inflate(inflater, container, false);

//        GenerateAdapter adapter = new GenerateAdapter(this);
//        binding.viewPageGenerate.setAdapter(adapter);
        return binding.getRoot();
    }
}