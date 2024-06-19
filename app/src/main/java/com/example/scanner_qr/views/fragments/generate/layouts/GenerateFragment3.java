package com.example.scanner_qr.views.fragments.generate.layouts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanner_qr.R;
import com.example.scanner_qr.databinding.FragmentGenerateSavedBinding;
import com.example.scanner_qr.models.MyQR;
import com.example.scanner_qr.models.QR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class GenerateFragment3 extends Fragment {

    private FragmentGenerateSavedBinding binding;

    private MyQR qr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentGenerateSavedBinding.inflate(inflater, container, false);


        Bundle bundle = getArguments();

        if (bundle!= null){
            qr= (MyQR) bundle.getSerializable("save_qr");
        }
        binding.imageQr.setImageBitmap(qr.getImage());

        binding.btnBackSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        binding.btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("image/*");


            }
        });
        return binding.getRoot();
    }


}