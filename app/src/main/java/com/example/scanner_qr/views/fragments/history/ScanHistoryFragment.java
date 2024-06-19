package com.example.scanner_qr.views.fragments.history;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.scanner_qr.data.DataLocalManager;
import com.example.scanner_qr.database.QrDatabase;
import com.example.scanner_qr.databinding.FragmentScanHistoryBinding;
import com.example.scanner_qr.models.QR;
import com.example.scanner_qr.views.activities.after.AfterScanActivity;
import com.example.scanner_qr.views.activities.main.adapter.ScanHistoryAdapter;
import com.example.scanner_qr.views.activities.main.inface.IGetItemHistoryScan;

import java.util.ArrayList;
import java.util.List;


public class ScanHistoryFragment extends Fragment {

    private FragmentScanHistoryBinding binding;
    private ScanHistoryAdapter adapter;

    private List<QR> qrList;

    private IGetItemHistoryScan itemHistoryScan = new IGetItemHistoryScan() {
        @Override
        public void getItemRemove(QR qr) {
            removeQr(qr);
        }

        @Override
        public void getItem(QR qr) {
            senDataAfterScan(qr);
        }

        @Override
        public void getItemShare(QR qr) {
            shareQR(qr);
        }
    };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScanHistoryBinding.inflate(inflater, container, false);
        qrList = new ArrayList<>();

        adapter = new ScanHistoryAdapter();
        getDataAdapter();

        binding.recyclerViewScannerHistory.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewScannerHistory.setAdapter(adapter);


        return binding.getRoot();
    }

    private List<QR> reverseList(List<QR> l){
        List<QR> list = new ArrayList<>();
        for (int i = l.size()-1; i >=0; i--) {
            list.add(l.get(i));
        }
        return list;
    }


    private void senDataAfterScan(QR qr) {
        Intent intent = new Intent(getActivity(), AfterScanActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("2",qr);
        bundle.putString("activityName","1111111");
        intent.putExtra("bundleAfter",bundle);
        startActivity(intent);
    }

    private void getDataAdapter(){
        qrList = QrDatabase.getInstance(requireContext()).qrDAO().getListQr();
        if (qrList.size()==0){
            binding.imageListNull.setVisibility(View.VISIBLE);
        }else {
            binding.imageListNull.setVisibility(View.INVISIBLE);
        }
        adapter.setData(reverseList(qrList),itemHistoryScan);
    }

    private void removeQr(QR qr) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirm delete QR")
                .setMessage("Are you sure")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QrDatabase.getInstance(requireContext()).qrDAO().deleteQr(qr);
                        Toast.makeText(requireContext(), "Delete successfully", Toast.LENGTH_SHORT).show();

                        getDataAdapter();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    public void shareQR(QR qr) {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareSub = "Your subject here";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, qr.getTitle());
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }
    @Override
    public void onResume() {
        super.onResume();
        getDataAdapter();
    }
}