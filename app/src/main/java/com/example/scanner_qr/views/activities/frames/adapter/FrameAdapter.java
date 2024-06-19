package com.example.scanner_qr.views.activities.frames.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.scanner_qr.views.fragments.frame.Frame_1;
import com.example.scanner_qr.views.fragments.frame.Frame_2;
import com.example.scanner_qr.views.fragments.frame.Frame_3;
import com.example.scanner_qr.views.fragments.frame.Frame_4;

public class FrameAdapter extends FragmentStateAdapter {


    public FrameAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Frame_1();
            case 1:
                return new Frame_2();
            case 2:
                return new Frame_3();
            case 3:
                return new Frame_4();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
