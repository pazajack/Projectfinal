package com.example.jack_nb.projectfinal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jack_nb.projectfinal.Models.Data;
import com.example.jack_nb.projectfinal.R;

import java.util.List;

public class DataStatistic extends RecyclerView.Adapter<DataStatistic.MyViewHolder> {
    private Context context;
    private List<Data> dataStatisticList;

    public DataStatistic(Context context, List<Data> dataStatisticList) {
        this.context = context;
        this.dataStatisticList = dataStatisticList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_statistics, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data data = dataStatisticList.get(position);

        holder.statisticTv.setText(String.valueOf(data.getNum()));
        holder.dateTv.setText(data.getDate());
    }

    @Override
    public int getItemCount() {
        return dataStatisticList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView statisticTv, dateTv;

        MyViewHolder(View itemView) {
            super(itemView);

            statisticTv = itemView.findViewById(R.id.statisticTv);
            dateTv = itemView.findViewById(R.id.dateTv);
        }
    }
}
