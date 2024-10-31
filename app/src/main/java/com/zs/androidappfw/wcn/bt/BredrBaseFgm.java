package com.zs.androidappfw.wcn.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseFragment;
import com.zs.androidappfw.utils.LUtil;
import com.zs.androidappfw.wcn.utils.BtAdapter;
import com.zs.androidappfw.wcn.utils.BtDevice;

import java.util.ArrayList;
import java.util.List;

public class BredrBaseFgm extends BaseFragment {

    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_DISCOVERABLE_BT = 2;

    private final List<BtDevice> mResultList = new ArrayList<>();
    private final BredrScanAdapter mAdapter = new BredrScanAdapter(mContext, mResultList);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_bredr_base, container, false);
        initView(view);
        return view;
    }
    
    private void initView(View view) {
        RecyclerView bredrScanRv = view.findViewById(R.id.fgm_bredr_base_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        bredrScanRv.setLayoutManager(linearLayoutManager);
        bredrScanRv.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));
        bredrScanRv.setAdapter(mAdapter);
    }

    public void enableByBroadcast(View v) {
        if (!BtAdapter.getInstance(mContext).isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    public void setDiscoverable(View v) {
        if (BtAdapter.getInstance(mContext).isEnabled()) {
            Intent discoverIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 240);
            startActivityForResult(discoverIntent, REQUEST_DISCOVERABLE_BT);
        }
    }

    public void setName(View v) {
        BtAdapter.setName(mContext.getApplicationContext(), "");
    }

    public void getProfileProxy(View v) {
        BtAdapter.getInstance(mContext).getProfileProxy(mContext, new BluetoothProfile.ServiceListener() {
            @Override
            public void onServiceConnected(int profile, BluetoothProfile proxy) {
                LUtil.d(TAG, profile, proxy);
//                (BluetoothA2dp) proxy;
//                (BluetoothHeadset) profile;
            }
            @Override
            public void onServiceDisconnected(int profile) {
                LUtil.d(TAG, profile);
            }
            // only for a2dp and headset
//        }, BluetoothProfile.A2DP);
//        }, BluetoothProfile.A2DP_SINK);
        }, BluetoothProfile.HEADSET);
//        }, BluetoothProfile.HEADSET_CLIENT);
    }

    public void closeProfileProxy(View v) {
        //adapter.closeProfileProxy();
    }

    public void listenUsingL2capChannel(View v) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            BluetoothServerSocket socket = null;
//            try {
//                socket = adapter.listenUsingL2capChannel();
//            } catch (IOException e) {
//                LUtil.e(TAG, "socket:", socket);
//            } finally {
//                if (socket != null) {
//                    try {
//                        socket.close();
//                    } catch (IOException e) {
//                        LUtil.e(TAG, "socket:", socket);
//                    }
//                }
//            }
//        }
    }

    public void listenUsingInsecureL2capChannel(View v) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//            BluetoothServerSocket socket = null;
//            try {
//                socket = adapter.listenUsingInsecureL2capChannel();
//            } catch (IOException e) {
//                LUtil.e(TAG, "socket:", socket);
//            } finally {
//                if (socket != null) {
//                    try {
//                        socket.close();
//                    } catch (IOException e) {
//                        LUtil.e(TAG, "socket:", socket);
//                    }
//                }
//            }
//        }
    }

    public void listenUsingInsecureRfcommWithServiceRecord(View v) {
//        adapter.listenUsingInsecureRfcommWithServiceRecord();
    }

    public void listenUsingRfcommWithServiceRecord(View v) {
//        adapter.listenUsingRfcommWithServiceRecord();
    }



}
