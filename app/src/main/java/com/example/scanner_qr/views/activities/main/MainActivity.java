package com.example.scanner_qr.views.activities.main;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.scanner_qr.R;
import com.example.scanner_qr.data.DataLocalManager;
import com.example.scanner_qr.database.QrDatabase;
import com.example.scanner_qr.databinding.ActivityMain2Binding;
import com.example.scanner_qr.models.QR;
import com.example.scanner_qr.views.activities.after.AfterScanActivity;
import com.example.scanner_qr.views.activities.generate.GenerateActivity;
import com.example.scanner_qr.views.activities.scaming.ScamingActivity;
import com.example.scanner_qr.views.fragments.history.GenerateHistoryFragment;
import com.example.scanner_qr.views.fragments.history.ScanHistoryFragment;
import com.example.scanner_qr.views.fragments.mains.GenerateFragment;
import com.example.scanner_qr.views.fragments.mains.HistoryScanFragment;
import com.example.scanner_qr.views.fragments.mains.HomeFragment;
import com.example.scanner_qr.views.fragments.mains.PersonalFragment;
import com.google.android.material.navigation.NavigationBarView;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMain2Binding binding;

    private String type;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (!DataLocalManager.getFirstInstalled()){
            DataLocalManager.setFirstInstalled(true);
        }
        replaceFragment(new HomeFragment());
        clickHome();
        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickHome();
                replaceFragment(new HomeFragment());

            }
        });

        binding.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickHistory();
                replaceFragment(new HistoryScanFragment());
            }
        });

        binding.btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGenerate();
//                replaceFragment(new GenerateFragment());
            }
        });

        binding.btnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPersonal();
                replaceFragment(new PersonalFragment());
            }
        });

        binding.btnSacnnerQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ScamingActivity.class);
                startActivity(intent);
            }
        });

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main,fragment);
        fragmentTransaction.commit();
    }

    private void clickHome(){
        binding.iconHome.setImageResource(R.drawable.icon_home_click);
        binding.iconHistory.setImageResource(R.drawable.history);
        binding.iconPersonal.setImageResource(R.drawable.person);
        binding.textHome.setTextColor(getResources().getColor(R.color.blue));
        binding.textHistory.setTextColor(getResources().getColor(R.color.icon));
        binding.textPersonal.setTextColor(getResources().getColor(R.color.icon));

    }
    private void clickHistory(){
        binding.iconHome.setImageResource(R.drawable.home);
        binding.iconHistory.setImageResource(R.drawable.icon_history_click);
        binding.iconPersonal.setImageResource(R.drawable.person);
        binding.textHome.setTextColor(getResources().getColor(R.color.icon));
        binding.textHistory.setTextColor(getResources().getColor(R.color.blue));
        binding.textPersonal.setTextColor(getResources().getColor(R.color.icon));

    }
    private void clickPersonal(){
        binding.iconHome.setImageResource(R.drawable.home);
        binding.iconHistory.setImageResource(R.drawable.history);
        binding.iconPersonal.setImageResource(R.drawable.icon_personal_click);
        binding.textHome.setTextColor(getResources().getColor(R.color.icon));
        binding.textHistory.setTextColor(getResources().getColor(R.color.icon));
        binding.textPersonal.setTextColor(getResources().getColor(R.color.blue));

    }

    private void clickGenerate(){
        Intent intent = new Intent(MainActivity.this, GenerateActivity.class);
        startActivity(intent);

    }

    private ActivityResultLauncher<String> resultPermission =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted ->{
                if (isGranted){
                    showCamera();
                }else {

                }
            });

    private ActivityResultLauncher<ScanOptions> qrCodeLauncher = registerForActivityResult(new ScanContract(), result->{
        if (result.getContents() != null){
            setTimeNow();


            Bitmap bitmap = BitmapFactory.decodeByteArray(result.getRawBytes(),0,result.getRawBytes().length);
            Log.e("bitmap",bitmap+"");
            checkType(result.getContents());
            Log.e("type",type);
            setResult(type,result.getContents(),bitmap,time);
        }
    });

    private void setTimeNow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy| HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);
        time = formattedDateTime;
        Log.e("formattedDateTime",formattedDateTime);
    }

    private void checkType(String contents) {
        if (isEmail(contents)){
            type = "Email";
        }else if (isUrl(contents)){
            type = "URL";
        }else if (isWiFiInfo(contents)){
            type = "WIFI";
        }else if (isPhoneNumber(contents)){
            type = "Phone";
        }else {
            type="Text";
        }
    }

    private void setResult(String type,String contents,Bitmap bitmap,String time) {
        QR qr = new QR(type,time,bitmap,contents);
        QrDatabase.getInstance(this).qrDAO().insertQr(qr);
        Intent intent = new Intent(MainActivity.this, AfterScanActivity.class);
        intent.putExtra("AfterActivity", qr);
        startActivity(intent);
//        if (isQrCheck(qr)){
//            return;
//        }

    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED){
            showCamera();
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show();
        }else {
            resultPermission.launch(Manifest.permission.CAMERA);
        }
    }

    private void showCamera() {
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        options.setPrompt("Scanner QR");
        options.setCameraId(0);
        options.setBeepEnabled(false);
        options.setBarcodeImageEnabled(true);
        options.setOrientationLocked(false);

        qrCodeLauncher.launch(options);
    }


    private boolean isUrl(String text) {
        return text.matches("^https?://.*$");
    }
    private boolean isEmail(String text) {
        return  text.startsWith("mailto:") && text.contains("@");
    }
    private boolean isWiFiInfo(String text) {
        return text.startsWith("WIFI:");
    }
    private boolean isPhoneNumber(String text) {
        return text.matches("^0\\d{9,11}$");
    }

    private boolean isQrCheck(QR qr){
        List<QR> list = QrDatabase.getInstance(this).qrDAO().checkQr(qr.getTitle());
        return list != null && !list.isEmpty();
    }

}