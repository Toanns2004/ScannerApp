package com.example.scanner_qr.views.fragments.history;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.scanner_qr.R;
import com.example.scanner_qr.database.QrDatabase;
import com.example.scanner_qr.databinding.FragmentGenerateHistoryBinding;
import com.example.scanner_qr.models.MyQR;
import com.example.scanner_qr.models.QR;
import com.example.scanner_qr.views.activities.after.AfterScanActivity;
import com.example.scanner_qr.views.activities.main.adapter.GenerateHistoryAdapter;
import com.example.scanner_qr.views.activities.main.adapter.ScanHistoryAdapter;
import com.example.scanner_qr.views.activities.main.inface.IGetItemGenerateHistory;
import com.example.scanner_qr.views.activities.myqr.CheckQrActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class GenerateHistoryFragment extends Fragment {
    private FragmentGenerateHistoryBinding binding;

    private List<MyQR> list;
    private GenerateHistoryAdapter adapter;

    private IGetItemGenerateHistory iGetItemGenerateHistory = new IGetItemGenerateHistory() {
        @Override
        public void getItemRemove(MyQR qr) {
            removeQr(qr);
        }

        @Override
        public void getItem(MyQR qr) {
            sendDataMyQr(qr);
        }

        @Override
        public void getItemShare(MyQR qr) {
            shareQR(qr);
        }
    };

    private void sendDataMyQr(MyQR qr) {
        Intent intent = new Intent(requireContext(), CheckQrActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Type", qr.getType());
        bundle.putString("Title", qr.getTitle());
        Uri image = getImageUri(requireContext(),qr.getImage());
        bundle.putString("Image",image.toString());
        intent.putExtra("MY_QR", bundle);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentGenerateHistoryBinding.inflate(inflater, container, false);
        list = new ArrayList<>();
        adapter = new GenerateHistoryAdapter();
        getDataAdapter();

        binding.recyclerViewGenerateHistory.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewGenerateHistory.setAdapter(adapter);

        return binding.getRoot();
    }

    private List<MyQR> reverseList(List<MyQR> l){
        List<MyQR> list = new ArrayList<>();
        for (int i = l.size()-1; i >=0; i--) {
            list.add(l.get(i));
        }
        return list;
    }



    private void getDataAdapter(){
        list = QrDatabase.getInstance(requireContext()).myQrDAO().getListQr();
        if (list.size()==0){
            binding.imageListNull.setVisibility(View.VISIBLE);
        }else {
            binding.imageListNull.setVisibility(View.INVISIBLE);
        }
        adapter.setDataGenerate(reverseList(list),iGetItemGenerateHistory);
    }

    private void removeQr(MyQR qr) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirm delete QR")
                .setMessage("Are you sure")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QrDatabase.getInstance(requireContext()).myQrDAO().deleteQr(qr);
                        Toast.makeText(requireContext(), "Delete successfully", Toast.LENGTH_SHORT).show();

                        getDataAdapter();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    public void shareQR(MyQR qr) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareSub = "Your subject here";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, qr.getTitle());
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    @Override
    public void onResume() {
        super.onResume();
        getDataAdapter();
    }
}