package com.example.scanner_qr.views.activities.main.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scanner_qr.R;
import com.example.scanner_qr.models.QR;
import com.example.scanner_qr.views.activities.main.inface.IGetItemHistoryScan;

import java.io.File;
import java.util.List;

public class ScanHistoryAdapter extends RecyclerView.Adapter<ScanHistoryAdapter.ScanViewHolder>{

    private List<QR> list;
    private IGetItemHistoryScan itemHistoryScan;


    public void setData(List<QR> list, IGetItemHistoryScan itemHistoryScan) {
        this.list = list;
        this.itemHistoryScan = itemHistoryScan;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_history,parent,false);

        return new ScanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScanViewHolder holder, int position) {

        QR qrCode = list.get(position);
        Log.e("list",list+"");
        holder.textType.setText(qrCode.getType());
        holder.textTitle.setText(qrCode.getTitle());

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemHistoryScan.getItemRemove(qrCode);
                Log.e("list",qrCode+"");

            }
        });

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemHistoryScan.getItem(qrCode);
            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemHistoryScan.getItemShare(qrCode);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    protected class ScanViewHolder extends RecyclerView.ViewHolder{
        TextView textType ,textTitle;
        ImageView image;
        RelativeLayout item;
        ImageButton btnShare,btnRemove;
        public ScanViewHolder(@NonNull View itemView) {
            super(itemView);
            textType = itemView.findViewById(R.id.text1_item_history);
            textTitle = itemView.findViewById(R.id.text2_item_history);
            image = itemView.findViewById(R.id.image_item);
            btnRemove = itemView.findViewById(R.id.icon_remove_item);
            item = itemView.findViewById(R.id.item_qr);
            btnShare = itemView.findViewById(R.id.icon_share_item_history);
        }
    }
}
