package com.zs.androidappfw.wcn.bt;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.wcn.utils.BtDevice;

import java.util.List;

public class BredrScanAdapter extends RecyclerView.Adapter<BredrScanAdapter.ViewHolder> {

    private List<BtDevice> mResultList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View resultView;
        TextView indexTv, nameTv, addressTv, rssiTv, typeTv, bondStateTv;
        Button connectBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            resultView = itemView;
            indexTv = itemView.findViewById(R.id.item_scan_result_index_tv);
            nameTv = itemView.findViewById(R.id.item_scan_result_name);
            addressTv = itemView.findViewById(R.id.item_scan_result_address);
            rssiTv = itemView.findViewById(R.id.item_scan_result_rssi);
            typeTv = itemView.findViewById(R.id.item_scan_result_type);
            bondStateTv = itemView.findViewById(R.id.item_scan_result_bond);
            connectBtn = itemView.findViewById(R.id.item_scan_result_connect_btn);
        }
    }

    public BredrScanAdapter(Context context, List<BtDevice> resultList) {
        mContext = context;
        mResultList = resultList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scan_result, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BtDevice result = mResultList.get(position);
        holder.indexTv.setText(String.valueOf(position + 1));
        holder.nameTv.setText(result.getName(mContext));
        holder.addressTv.setText(result.getAddress());
        holder.rssiTv.setText(result.getRssiStr());
        holder.typeTv.setText(result.getTypeName());
        holder.bondStateTv.setText(result.getBondStateName());
        if (result.getBondState() == BluetoothDevice.BOND_BONDED) {
            holder.bondStateTv.setTextColor(
                    mContext.getResources().getColor(android.R.color.holo_green_dark, mContext.getTheme()));
        } else {
            holder.bondStateTv.setTextColor(
                    mContext.getResources().getColor(android.R.color.black, mContext.getTheme()));
        }
        holder.connectBtn.setText("connect");
    }


    @Override
    public int getItemCount() {
        return mResultList.size();
    }
}
