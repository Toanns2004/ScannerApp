package com.example.scanner_qr.views.activities.generate.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scanner_qr.R;
import com.example.scanner_qr.data.DataLocalManager;
import com.example.scanner_qr.models.App_Type;
import com.example.scanner_qr.views.activities.generate.inface.IGetAppType;

import java.util.List;

public class GenerateAdapter extends RecyclerView.Adapter<GenerateAdapter.GenerateViewHolder>{

    List<App_Type> list;
    IGetAppType iGetAppType;

    private int blue = R.color.blue;
    private int black = R.color.black;


    public GenerateAdapter(List<App_Type> list, IGetAppType iGetAppType) {
        this.list = list;
        this.iGetAppType = iGetAppType;
    }

    @NonNull
    @Override
    public GenerateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_generate,parent,false);
        return new GenerateViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull GenerateViewHolder holder, int position) {
        App_Type app = list.get(position);

        holder.name.setText(app.getText());
        holder.icon.setImageResource(app.getIcon());
        holder.rlt.setBackgroundResource(app.getBackground());

        if (!app.isCheck()){

        }else {

        }

        if (DataLocalManager.getButtonType().equals(app.getText())){
            holder.circle.setBackgroundResource(R.drawable.custom_circle_button_type_click);
            holder.name.setTextColor(blue);
        }else {
            holder.circle.setBackgroundResource(R.drawable.custom_circle_button_type);
            holder.name.setTextColor(black);
        }

        holder.rlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataLocalManager.setButtonType(app.getText());
                iGetAppType.getClickAppType(app);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        if (list !=null){
            return list.size();
        }
        return 0;
    }

    public class GenerateViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout rlt,circle;
        ImageView icon;
        TextView name;
        public GenerateViewHolder(@NonNull View itemView) {

            super(itemView);
            rlt =itemView.findViewById(R.id.btn_icon);
            icon = itemView.findViewById(R.id.icon_app);
            name = itemView.findViewById(R.id.text_app);
            circle = itemView.findViewById(R.id.circle_item);
        }
    }

}
