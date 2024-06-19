package com.example.scanner_qr.views.activities.frames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.scanner_qr.R;
import com.example.scanner_qr.databinding.ActivityMainBinding;
import com.example.scanner_qr.views.activities.frames.adapter.FrameAdapter;
import com.example.scanner_qr.views.activities.main.MainActivity;

public class FrameActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        FrameAdapter frameAdapter= new FrameAdapter(this);
        mainBinding.viewPageFrame.setAdapter(frameAdapter);
        mainBinding.circleIndicator.setViewPager(mainBinding.viewPageFrame);

        mainBinding.buttonContinueFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_frame = mainBinding.viewPageFrame.getCurrentItem();
                switch (id_frame){
                    case 0:
                        mainBinding.viewPageFrame.setCurrentItem(1);
                        break;
                    case 1:
                        mainBinding.viewPageFrame.setCurrentItem(2);
                        break;

                    case 2:
                        mainBinding.viewPageFrame.setCurrentItem(3);
                        break;

                    case 3:
                        Intent intent = new Intent(FrameActivity.this, MainActivity.class);
                        startActivity(intent);

                        break;

                }
            }
        });

        mainBinding.viewPageFrame.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                applyView(position);

            }
        });


    }


    private void applyView(int position) {
        switch (position) {
            case 0:
                mainBinding.text1Frame.setText(R.string.text1_frame1);
                mainBinding.text2Frame.setText(R.string.text2_frame1);
                mainBinding.buttonContinueFrame.setText(R.string.Continue);

                break;
            case 1:
                mainBinding.text1Frame.setText(R.string.text1_frame2);
                mainBinding.text2Frame.setText(R.string.text2_frame2);
                mainBinding.buttonContinueFrame.setText(R.string.Continue);

                break;
            case 2:
                mainBinding.text1Frame.setText(R.string.text1_frame3);
                mainBinding.text2Frame.setText(R.string.text2_frame3);
                mainBinding.buttonContinueFrame.setText(R.string.Continue);

                break;
            case 3:
                mainBinding.text1Frame.setText(R.string.text1_frame4);
                mainBinding.text2Frame.setText(R.string.text2_frame4);
                mainBinding.buttonContinueFrame.setText(R.string.get_start);
                break;


        }
    }
}