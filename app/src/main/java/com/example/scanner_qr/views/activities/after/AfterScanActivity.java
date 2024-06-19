package com.example.scanner_qr.views.activities.after;

import static com.example.scanner_qr.views.activities.scaming.ScamingActivity.KEY_SCANNER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.scanner_qr.R;
import com.example.scanner_qr.data.DataLocalManager;
import com.example.scanner_qr.database.QrDatabase;
import com.example.scanner_qr.databinding.ActivityAfterScanBinding;
import com.example.scanner_qr.models.QR;
import com.example.scanner_qr.views.activities.main.MainActivity;
import com.example.scanner_qr.views.activities.scaming.ScamingActivity;

import java.util.ArrayList;
import java.util.List;

public class AfterScanActivity extends AppCompatActivity {

    private ActivityAfterScanBinding binding;
    private String title;
    private String time;
    private String type;
    public static final String KEY_ACTION_AFTER= "KEY_ACTION_AFTER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAfterScanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getDataScanner();

//        getDataScanHistoryFragment();

        binding.btnBackAfterScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterScanActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.iconHomeAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterScanActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        binding.btnContinueScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterScanActivity.this, ScamingActivity.class);
                intent.putExtra(KEY_ACTION_AFTER,true);
                startActivity(intent);
            }
        });

        binding.btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Copy",binding.textTitleAfter.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(AfterScanActivity.this, "Copy ", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnBackAfterScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareSub = "Your subject here";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, title);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, title);
                startActivity(intent);
            }
        });

        binding.btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.textTitleAfter.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });

    }

    private void getDataScanner(){
        QR qr ;
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundleAfter");
        String activityPrevious = bundle.getString("activityName");
        if (activityPrevious.equals(KEY_SCANNER)){
            qr = (QR) bundle.getSerializable("1");
        }else {
            qr = (QR) bundle.getSerializable("2");

        }

        if (qr!= null){
            title = qr.getTitle();
            time = qr.getTime();
            type = qr.getType();
        }

        binding.textTimeScanner.setText(time);
        binding.textTitleAfter.setText(title);
        if (type.equals("WIFI")) {
            String[] wifiInfo = parseWiFiInfo(title);
            String wifiName = wifiInfo[0];
            String securityType = wifiInfo[1];
            binding.textTitleAfter.setText(wifiName);
            binding.textWifiSe.setVisibility(View.VISIBLE);
            binding.typeSecurityWifi.setVisibility(View.VISIBLE);
            binding.textWifiSe.setText(securityType);

        } else if (type.equals("Email")) {
            String[] emailInfo = parseEmailInfo(title);

            String recipient = emailInfo[0];
            String subject = emailInfo[1];
            String body = emailInfo[2];
            binding.textContent.setText("Address: ");
            binding.textTitleAfter.setText(recipient);

            binding.typeSecurityWifi.setVisibility(View.VISIBLE);
            binding.typeSecurityWifi.setText("Subject:");
            binding.textWifiSe.setVisibility(View.VISIBLE);
            binding.textWifiSe.setText(subject);
            binding.textMessage.setVisibility(View.VISIBLE);
            binding.textMessage.setText("Message: ");
            binding.textMessageTitle.setVisibility(View.VISIBLE);
            binding.textMessageTitle.setText(body);

        }else if (type.equals("Phone")){
            String phone= parsePhoneInfo(title);
            binding.textTitleAfter.setText(phone);
            binding.btnPhone.setVisibility(View.VISIBLE);

        }
        binding.textTypeQrAfter.setText(type);

    }



    private String[] parseWiFiInfo(String text) {
        String wifiInfo = text.substring(5);


        String[] parts = wifiInfo.split(";");
        String wifiName = parts[0].substring(2);
        String securityType = parts[1].substring(2);

        return new String[]{wifiName, securityType};
    }

    private String[] parseEmailInfo(String text) {
        String emailInfo = text.substring(7);
        String[] parts = emailInfo.split("\\?");

        String email = parts[0];
        String subject = "";
        String body = "";

        if (parts.length > 1) {
            String[] params = parts[1].split("&");
            for (String param : params) {
                String[] keyValue = param.split("=");

                if (keyValue.length == 2) {
                    if (keyValue[0].equals("subject")) {
                        subject = keyValue[1];
                    } else if (keyValue[0].equals("body")) {
                        body = keyValue[1];
                    }
                }
            }
        }

        return new String[]{email, subject, body};
    }

    private String parsePhoneInfo(String text) {
        return text.substring(4);
    }


}