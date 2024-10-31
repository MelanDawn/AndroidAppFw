package com.zs.androidappfw.wcn.bt;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zs.androidappfw.R;

import java.util.List;

public class BredrBaseAdapter extends RecyclerView.Adapter<BredrBaseAdapter.ViewHolder> {

    private final List<Pair<String, String>> mDataList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView keyTv, valueTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            keyTv = itemView.findViewById(R.id.item_common_info_key_tv);
            valueTv = itemView.findViewById(R.id.item_common_info_value_tv);
        }
    }

    public BredrBaseAdapter(List<Pair<String, String>> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<String, String> data = mDataList.get(position);
        holder.keyTv.setText(data.first);
        holder.valueTv.setText(data.second);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
