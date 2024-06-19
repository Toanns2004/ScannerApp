package com.example.scanner_qr.views.fragments.generate.layouts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scanner_qr.R;
import com.example.scanner_qr.database.QrDatabase;

import com.example.scanner_qr.databinding.FragmentGenerateSaveBinding;
import com.example.scanner_qr.models.MyQR;
import com.example.scanner_qr.models.QR;


public class GenerateFragment2 extends Fragment {

    private FragmentGenerateSaveBinding binding;
    private MyQR qr ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGenerateSaveBinding.inflate(inflater, container, false);

        Bundle bundle = getArguments();

        if (bundle!= null){
             qr= (MyQR) bundle.getSerializable("create_qr");
            binding.imageQr.setImageBitmap(qr.getImage());
        }




        binding.btnBackSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getParentFragmentManager().popBackStack();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QrDatabase.getInstance(requireContext()).myQrDAO().insertQr(qr);
                replaceFragment(new GenerateFragment3(),qr);

            }
        });

        return binding.getRoot();
    }

    private void replaceFragment(Fragment fragment,MyQR qr){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("save_qr",qr);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_generate_create,fragment);
        fragmentTransaction.addToBackStack(GenerateFragment2.class.getName());
        fragmentTransaction.commit();
    }
}