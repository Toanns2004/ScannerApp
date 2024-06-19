package com.example.scanner_qr.views.activities.main.adapter;

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
import com.example.scanner_qr.models.MyQR;
import com.example.scanner_qr.models.QR;
import com.example.scanner_qr.views.activities.main.inface.IGetItemGenerateHistory;
import com.example.scanner_qr.views.activities.main.inface.IGetItemHistoryScan;

import java.util.List;

public class GenerateHistoryAdapter extends RecyclerView.Adapter<GenerateHistoryAdapter.GenerateHistoryViewHolder> {
    private List<MyQR> list;

    IGetItemGenerateHistory iGetItemGenerateHistory;

    public void setDataGenerate(List<MyQR> list, IGetItemGenerateHistory iGetItemGenerateHistory) {
        this.list = list;
        this.iGetItemGenerateHistory = iGetItemGenerateHistory;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public GenerateHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_history,parent,false);
        return new GenerateHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenerateHistoryViewHolder holder, int position) {
        MyQR qrCode = list.get(position);
        holder.textType.setText(qrCode.getType());
        holder.textTitle.setText(qrCode.getTitle());
        holder.image.setImageBitmap(qrCode.getImage());

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iGetItemGenerateHistory.getItemRemove(qrCode);

            }
        });

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iGetItemGenerateHistory.getItem(qrCode);
            }
        });

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iGetItemGenerateHistory.getItemShare(qrCode);
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

    public class GenerateHistoryViewHolder extends RecyclerView.ViewHolder{

        TextView textType ,textTitle;
        ImageView image;
        RelativeLayout item;
        ImageButton btnShare,btnRemove;
        public GenerateHistoryViewHolder(@NonNull View itemView) {
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
