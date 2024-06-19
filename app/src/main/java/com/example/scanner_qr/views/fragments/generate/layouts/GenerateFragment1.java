package com.example.scanner_qr.views.fragments.generate.layouts;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scanner_qr.R;
import com.example.scanner_qr.data.DataLocalManager;
import com.example.scanner_qr.database.QrDatabase;
import com.example.scanner_qr.databinding.FragmentCreateGenerateBinding;
import com.example.scanner_qr.models.App_Type;
import com.example.scanner_qr.models.MyQR;
import com.example.scanner_qr.models.QR;
import com.example.scanner_qr.views.activities.generate.GenerateActivity;

import com.example.scanner_qr.views.activities.generate.adapter.GenerateAdapter;
import com.example.scanner_qr.views.activities.generate.inface.IGetAppType;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class GenerateFragment1 extends Fragment {
   private FragmentCreateGenerateBinding binding;

   private GenerateAdapter adapter;
   private List<App_Type> list;
   private String netWorkType = "WPA";



   private IGetAppType iGetAppType = new IGetAppType() {

       @Override
       public void getClickAppType(App_Type appType) {
            checkType(appType);
       }
   };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentCreateGenerateBinding.inflate(inflater, container, false);
        init();

        DataLocalManager.setButtonType("Text");
        adapter = new GenerateAdapter(list,iGetAppType);


        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        binding.rclAppType.setLayoutManager(layoutManager);
        binding.rclAppType.setAdapter(adapter);

        setLayoutTypeText();

        binding.qrtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        binding.seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        binding.btnBackAfterScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return binding.getRoot();

    }

    private void init(){

        list = new ArrayList<>();
        list.add(new App_Type(R.drawable.custom_circle_yellow,R.drawable.icon_text,"Text"));
        list.add(new App_Type(R.drawable.custom_circle_orage,R.drawable.ixon_website,"Website"));
        list.add(new App_Type(R.drawable.custom_circle_blue,R.drawable.icon_contact,"Contact"));
        list.add(new App_Type(R.drawable.custom_circle_pink,R.drawable.icon_wifi,"Wifi"));
        list.add(new App_Type(R.drawable.custom_circle_green,R.drawable.icon_phone,"Phone"));
        list.add(new App_Type(R.drawable.custom_circle_sms,R.drawable.icon_sms,"SMS"));
        list.add(new App_Type(R.drawable.custom_circle_pink,R.drawable.icon_email,"Email"));
        list.add(new App_Type(R.drawable.custom_circle_yellow,R.drawable.icon_text,"Document"));
        list.add(new App_Type(R.drawable.custom_circle_orage,R.drawable.icon_insta,"Insta"));
        list.add(new App_Type(R.drawable.custom_circle_blue,R.drawable.icon_fb,"Fb"));
        list.add(new App_Type(R.drawable.custom_circle_pink,R.drawable.icon_tiktok,"Tiktok"));
        list.add(new App_Type(R.drawable.custom_circle_blue,R.drawable.icon_twitter,"Twitter"));
        list.add(new App_Type(R.drawable.custom_circle_green,R.drawable.icon_whatapp,"What's app"));
        list.add(new App_Type(R.drawable.custom_circle_blue,R.drawable.icon_spotify,"Spotify"));
        list.add(new App_Type(R.drawable.custom_circle_red,R.drawable.icon_youtube,"Youtube"));
        list.add(new App_Type(R.drawable.custom_circle_sms,R.drawable.icon_paypal,"Paypal"));
        list.add(new App_Type(R.drawable.custom_circle_sms,R.drawable.icon_viber,"Viber"));
        list.add(new App_Type(R.drawable.custom_circle_green,R.drawable.icon_line,"Line"));
        list.add(new App_Type(R.drawable.custom_circle_blue,R.drawable.icon_linkin,"Linkin"));
        list.add(new App_Type(R.drawable.custom_circle_green,R.drawable.icon_wechat,"Wechat"));
        list.add(new App_Type(R.drawable.custom_circle_red,R.drawable.icon_pinterest,"Pinterest"));
        list.add(new App_Type(R.drawable.custom_circle_yellow,R.drawable.icon_snapchat,"Snapchat"));
        list.add(new App_Type(R.drawable.custom_circle_blue,R.drawable.icon_skype,"Skype"));
        list.add(new App_Type(R.drawable.custom_circle_yellow,R.drawable.icon_snapchat,"Ads"));

    }
    private void checkType(App_Type appType){
        String name = appType.getText().toString();
        switch (name){
            case "Text":
                binding.textTypeQrAfter.setText("Text");
                setLayoutTypeText();
                break;
            case "Website":
                binding.textTypeQrAfter.setText("Website");
                setLayoutTypeWeb();
                break;
            case "Contact":
                binding.textTypeQrAfter.setText("Contact");
                setLayoutTypeText();

                break;
            case "Wifi":
                binding.textTypeQrAfter.setText("Wifi");
                setLayoutTypeWifi();
                break;
            case "Phone":
                binding.textTypeQrAfter.setText("Phone");
                setLayoutTypePhone();
                break;
            case "SMS":
                binding.textTypeQrAfter.setText("SMS");
                setLayoutTypeSMS();
                break;
            case "Email":
                binding.textTypeQrAfter.setText("Email");
                setLayoutTypeEmail();
                break;
            case "Document":
                binding.textTypeQrAfter.setText("Document");
                setLayoutTypeUrl();

                break;
            case "Insta":
                binding.textTypeQrAfter.setText("Instagram");
                setLayoutTypeUrl();
                break;
            case "Fb":
                binding.textTypeQrAfter.setText("Facebook");
                setLayoutTypeUrl();
                break;
            case "Tiktok":
                binding.textTypeQrAfter.setText("Tiktok");
                setLayoutTypeUrl();

                break;
            case "Twitter":
                binding.textTypeQrAfter.setText("Twitter");
                setLayoutTypeUrl();

                break;
            case "What's app":
                binding.textTypeQrAfter.setText("What's app");
                setLayoutTypeUrl();

                break;
            case "Spotify":
                binding.textTypeQrAfter.setText("Spotify");
                setLayoutTypeUrl();

                break;
            case "Youtube":
                binding.textTypeQrAfter.setText("Youtube");
                setLayoutTypeUrl();

                break;
            case "Paypal":
                binding.textTypeQrAfter.setText("Paypal");
                setLayoutTypeUrl();

                break;
            case "Viber":
                binding.textTypeQrAfter.setText("Viber");
                setLayoutTypeUrl();
                break;
            case "Line":
                binding.textTypeQrAfter.setText("Line");
                setLayoutTypeUrl();

                break;
            case "Linkin":
                binding.textTypeQrAfter.setText("Linkin");
                setLayoutTypeUrl();

                break;
            case "Wechat":
                binding.textTypeQrAfter.setText("Wechat");
                setLayoutTypeUrl();

                break;
            case "Pinterest":
                binding.textTypeQrAfter.setText("Pinterest");
                setLayoutTypeUrl();

                break;
            case "Snapchat":
                binding.textTypeQrAfter.setText("Snapchat");
                setLayoutTypeUrl();

                break;
            case "Skype":
                binding.textTypeQrAfter.setText("Skype");
                setLayoutTypeUrl();

                break;
            case "Ads":
                binding.textTypeQrAfter.setText("Ads");
                setLayoutTypeUrl();

                break;

        }


    }

    private void replaceFragment(Fragment fragment, MyQR qr){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("create_qr",qr);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_generate_create,fragment);
        fragmentTransaction.addToBackStack(GenerateFragment1.class.getName());
        fragmentTransaction.commit();
    }

    private void createQr(String title){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
                if (title.equals("")){
                    Toast.makeText(requireContext(), "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                }else {
                    BitMatrix bitMatrix = multiFormatWriter.encode(title, BarcodeFormat.QR_CODE,300,300);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    String type = binding.textTypeQrAfter.getText().toString();
                    String time =setTimeNow();
                    MyQR qr = new MyQR(type,time,bitmap,title);

                    replaceFragment(new GenerateFragment2(),qr);
                }

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

    }
    private String setTimeNow() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy| HH:mm");
        return  currentDateTime.format(formatter);
    }

    private boolean isQrCheck(QR qr){
        List<QR> list = QrDatabase.getInstance(requireContext()).qrDAO().checkQr(qr.getTitle());
        return list != null && !list.isEmpty();
    }

    private void setLayoutTypeWeb(){
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.layout_web_generate,null);
        Button btnHttp = view.findViewById(R.id.btn_http);
        Button btnHttps = view.findViewById(R.id.btn_https);
        Button btnWww = view.findViewById(R.id.btn_www);
        Button btnCom = view.findViewById(R.id.btn_com);
        EditText edt = view.findViewById(R.id.text_title_web);
        btnHttp.setOnClickListener(v -> {
            String currentText = edt.getText().toString();
            String newText = "http://" + currentText;
            edt.setText(newText);
        });
        btnHttps.setOnClickListener(v -> {
            String currentText = edt.getText().toString();
            String newText = "https://" + currentText;
            edt.setText(newText);
        });
        btnWww.setOnClickListener(v -> {
            String currentText = edt.getText().toString();
            String newText =  currentText+"www.";
            edt.setText(newText);
        });
        btnCom.setOnClickListener(v -> {
            String currentText = edt.getText().toString();
            String newText =  currentText+ ".com" ;
            edt.setText(newText);
        });
        binding.layoutCreateQr.removeAllViews();
        binding.layoutCreateQr.addView(view);
    }
    private void setLayoutTypeWifi(){
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.layout_wifi_generate,null);
        EditText textSsid = view.findViewById(R.id.edit_ssid);
        EditText textPass = view.findViewById(R.id.edit_pass);
        Button btnWap = view.findViewById(R.id.btn_wpa);
        Button btnWep = view.findViewById(R.id.btn_wep);
        Button btnNone = view.findViewById(R.id.btn_none);


        btnWap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnWap.setBackgroundResource(R.drawable.custom_btn_wifi_click);
                btnWep.setBackgroundResource(R.drawable.custom_btn_wifi_uncheck);
                btnNone.setBackgroundResource(R.drawable.custom_btn_wifi_uncheck);

                netWorkType = "WPA";
            }
        });

        btnWep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnWap.setBackgroundResource(R.drawable.custom_btn_wifi_uncheck);
                btnWep.setBackgroundResource(R.drawable.custom_btn_wifi_click);
                btnNone.setBackgroundResource(R.drawable.custom_btn_wifi_uncheck);
                netWorkType = "WEP";
            }
        });

        btnNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnWap.setBackgroundResource(R.drawable.custom_btn_wifi_uncheck);
                btnWep.setBackgroundResource(R.drawable.custom_btn_wifi_uncheck);
                btnNone.setBackgroundResource(R.drawable.custom_btn_wifi_click);
                netWorkType = "";
            }
        });
        binding.layoutCreateQr.removeAllViews();
        binding.layoutCreateQr.addView(view);



        binding.btnCreate.setOnClickListener(v -> createQrWifi(textSsid,textPass,netWorkType));
    }



    private void setLayoutTypeText(){
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.layout_text_generate,null);
        EditText editText = view.findViewById(R.id.text_title_generate);
        binding.layoutCreateQr.removeAllViews();
        binding.layoutCreateQr.addView(view);
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText.getText().toString().trim();
                createQr(title);
            }
        });
    }

    private void setLayoutTypePhone(){
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.layout_phone_generate,null);
        EditText editText = view.findViewById(R.id.text_phone_layout);
        binding.layoutCreateQr.removeAllViews();
        binding.layoutCreateQr.addView(view);
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText.getText().toString().trim();
                createQrPhone(title);
            }
        });
    }
    private void setLayoutTypeSMS(){
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.layout_sms_generate,null);
        EditText phone = view.findViewById(R.id.text_phone);
        EditText content = view.findViewById(R.id.text_content_sms);
        binding.layoutCreateQr.removeAllViews();
        binding.layoutCreateQr.addView(view);
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQrSMS(phone,content);
            }
        });
    }

    private void setLayoutTypeEmail(){
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.layout_email_generate,null);
        EditText gmail = view.findViewById(R.id.text_gmail_address);
        EditText subject = view.findViewById(R.id.text_subject_email);
        EditText mess = view.findViewById(R.id.text_message_email);
        binding.layoutCreateQr.removeAllViews();
        binding.layoutCreateQr.addView(view);
        binding.btnCreate.setOnClickListener(v -> createQrEmail(gmail,subject,mess));

    }
    private void setLayoutTypeUrl(){
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.layout_url_generate,null);
        EditText editText = view.findViewById(R.id.text_title_generate);
        binding.layoutCreateQr.removeAllViews();
        binding.layoutCreateQr.addView(view);

        binding.btnCreate.setOnClickListener(v -> createQrUrl(editText));
    }



    private void createQrUrl(EditText editText){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        String title =editText.getText().toString().trim();
        try {
            if (title.isEmpty()){
                Toast.makeText(requireContext(), "Nhap du lieu", Toast.LENGTH_SHORT).show();
            }else {
                    BitMatrix bitMatrix = multiFormatWriter.encode(title, BarcodeFormat.QR_CODE,300,300);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    String type = binding.textTypeQrAfter.getText().toString();
                    String time =setTimeNow();
                    MyQR qr = new MyQR(type,time,bitmap,title);

                    replaceFragment(new GenerateFragment2(),qr);
            }

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

    }

    private void createQrWifi(EditText ssi,EditText pass,String networkType){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        String ssid = ssi.getText().toString().trim();
        String password =pass.getText().toString().trim();

        String wifiInfo = "WIFI:S:" + ssid + ";P:" + networkType + ";T:" + password + ";";
        try {
            if (ssid.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "Nhap du lieu", Toast.LENGTH_SHORT).show();
            }else {
                BitMatrix bitMatrix = multiFormatWriter.encode(wifiInfo, BarcodeFormat.QR_CODE,300,300);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                String type = binding.textTypeQrAfter.getText().toString();
                String time =setTimeNow();
                MyQR qr = new MyQR(type,time,bitmap,wifiInfo);

                replaceFragment(new GenerateFragment2(),qr);
            }

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

    }
    private void createQrEmail(EditText mail,EditText sub,EditText mes){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        String email = mail.getText().toString().trim();
        String subject =sub.getText().toString().trim();
        String body = mes.getText().toString().trim();
        String emailInfo = "mailto:" + email+ "?subject=" + subject+"&body=" + body;

        try {
            if (email.isEmpty() || subject.isEmpty() || body.isEmpty() ){
                Toast.makeText(requireContext(), "Nhap du lieu", Toast.LENGTH_SHORT).show();
            }else {
                BitMatrix bitMatrix = multiFormatWriter.encode(emailInfo, BarcodeFormat.QR_CODE,300,300);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                String type = binding.textTypeQrAfter.getText().toString();
                String time =setTimeNow();
                MyQR qr = new MyQR(type,time,bitmap,emailInfo);

                replaceFragment(new GenerateFragment2(),qr);
            }

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

    }

    private void createQrSMS(EditText phone,EditText content){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        String nPhone = phone.getText().toString().trim();
        String mContent = content.getText().toString().trim();

        String title = "sms:"+nPhone+"&body:"+mContent;
        try {
            if (nPhone.isEmpty()){
                Toast.makeText(requireContext(), "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
            }else {
                BitMatrix bitMatrix = multiFormatWriter.encode(title, BarcodeFormat.QR_CODE,300,300);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                String type = binding.textTypeQrAfter.getText().toString();
                String time =setTimeNow();
                MyQR qr = new MyQR(type,time,bitmap,title);

                replaceFragment(new GenerateFragment2(),qr);
            }

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

    }

    private void createQrPhone(String title){
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();


        try {
            if (title.isEmpty()){
                Toast.makeText(requireContext(), "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
            }else {
                BitMatrix bitMatrix = multiFormatWriter.encode(title, BarcodeFormat.QR_CODE,300,300);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                String type = binding.textTypeQrAfter.getText().toString();
                String time =setTimeNow();
                MyQR qr = new MyQR(type,time,bitmap,title);

                replaceFragment(new GenerateFragment2(),qr);
            }

        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

    }

}