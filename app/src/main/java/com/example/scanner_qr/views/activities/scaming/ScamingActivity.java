package com.example.scanner_qr.views.activities.scaming;

import static com.example.scanner_qr.views.activities.after.AfterScanActivity.KEY_ACTION_AFTER;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.scanner_qr.data.DataLocalManager;
import com.example.scanner_qr.database.QrDatabase;
import com.example.scanner_qr.databinding.ActivityScamingBinding;
import com.example.scanner_qr.models.QR;
import com.example.scanner_qr.views.activities.after.AfterScanActivity;
import com.example.scanner_qr.views.activities.main.MainActivity;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ScamingActivity extends AppCompatActivity {

    public static final String KEY_SCANNER = "ScamingActivity";
    private ActivityScamingBinding binding;

    private int cameraFacing = CameraSelector.LENS_FACING_BACK;

    private String type;
    private String time;
    private Uri imageUri;
    private boolean isFlashOn = false;

    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean o) {
            startCamera(cameraFacing);
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
        Intent intent = new Intent(ScamingActivity.this, AfterScanActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("1",qr);
        bundle.putString("activityName",KEY_SCANNER);
        intent.putExtra("bundleAfter",bundle);
        startActivity(intent);
//        if (isQrCheck(qr)){
//            return;
//        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScamingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (ContextCompat.checkSelfPermission(ScamingActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            activityResultLauncher.launch(Manifest.permission.CAMERA);
        }else {
            startCamera(cameraFacing);
        }

        binding.btnBackScaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnScanScam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQr();
            }
        });

        binding.btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickIntent = new Intent(Intent.ACTION_PICK);
                pickIntent.setDataAndType( android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                startActivityForResult(pickIntent, 111);
            }
        });

        previousAfterActivity();
    }

    private void previousAfterActivity() {
        Intent intent = getIntent();
        boolean after = intent.getBooleanExtra(KEY_ACTION_AFTER,false);
        if (after){
            scanQr();
        }
    }
    private void scanQr() {
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        options.setPrompt("Scanner QR");
        options.setCameraId(0);
        options.setBeepEnabled(false);
        options.setBarcodeImageEnabled(true);
        options.setOrientationLocked(false);
        qrCodeLauncher.launch(options);
    }


    private void startCamera(int cameraFacing) {
        int aspectRatio = aspectRatio(binding.preview.getWidth(), binding.preview.getHeight());
        ListenableFuture<ProcessCameraProvider> listenableFuture = ProcessCameraProvider.getInstance(this);

        listenableFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = listenableFuture.get();

                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(cameraFacing)
                        .build();



                // Tạo preview
                Preview preview = new Preview.Builder()
                        .setTargetAspectRatio(aspectRatio)
                        .build();

                preview.setSurfaceProvider(binding.preview.getSurfaceProvider());

                cameraProvider.bindToLifecycle(this, cameraSelector, preview);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }



    private int aspectRatio(int width, int height) {
        double previewRatio =(double) Math.max(width,height) / Math.min(width, height);
        if (Math.abs(previewRatio - 4.0/3.0)<= Math.abs(previewRatio - 16.0/9.0)){
            return AspectRatio.RATIO_4_3;
        }
        return AspectRatio.RATIO_16_9;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       if (requestCode == 111 && resultCode == RESULT_OK && data != null){
                Uri uri = data.getData();
                try
                {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    if (bitmap == null)
                    {
                        Toast.makeText(this, "Not found QR", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int width = bitmap.getWidth(), height = bitmap.getHeight();
                    int[] pixels = new int[width * height];
                    bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

                    RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
                    BinaryBitmap bBitmap = new BinaryBitmap(new HybridBinarizer(source));
                    MultiFormatReader reader = new MultiFormatReader();
                    try
                    {
                        Result result = reader.decode(bBitmap);
                        Log.e("resultQR",result.getText().toString());
                        checkType(result.getText());
                        setTimeNow();
                        setResult(type,result.getText(),null,time);
                    }
                    catch (NotFoundException e)
                    {
                        Log.e("TAG", "decode exception", e);
                    }
                }
                catch (FileNotFoundException e)
                {
                    Log.e("TAG", "can not open file" + uri.toString(), e);
                }
       }
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
        return text.matches("^tel:0\\d{8,10}$");
    }

    private boolean isQrCheck(QR qr){
        List<QR> list = QrDatabase.getInstance(this).qrDAO().checkQr(qr.getTitle());
        return list != null && !list.isEmpty();
    }
}