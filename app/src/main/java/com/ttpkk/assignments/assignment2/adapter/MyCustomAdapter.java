package com.ttpkk.assignments.assignment2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ttpkk.assignments.R;
import com.ttpkk.assignments.assignment2.ResultOneCheckActivity;
import com.ttpkk.assignments.assignment2.entity.Item;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {
    private List<Item> itemList;
    private Context context;

    public MyCustomAdapter(Context context) {
        this.context = context;
    }

    public MyCustomAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    private String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return format.format(date);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_list_view,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = itemList.get(position);

        holder.scan1.setText(item.getBarcodeNumber1());
        holder.scan2.setText(item.getBarcodeNumber2());
        holder.timestamp.setText(convertTime(item.getTimestamp()));
        if (item.isMatched()) {
            holder.resultImg.setImageResource(R.drawable.ic_check_144);
        } else {
            holder.resultImg.setImageResource(R.drawable.ic_cancel_144);
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView scan1;
        TextView scan2;
        TextView timestamp;
        ImageView resultImg;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            scan1 = itemView.findViewById(R.id.scan_1Txt);
            scan2= itemView.findViewById(R.id.scan_2Txt);
            timestamp = itemView.findViewById(R.id.dateTimeStamp);
            resultImg = itemView.findViewById(R.id.iconView);
        }
    }

}
