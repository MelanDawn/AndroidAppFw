package com.zs.androidappfw.wcn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseClickFragment;
import com.zs.androidappfw.base.BaseFragmentActivity;
import com.zs.androidappfw.base.BaseTitleFragment;
import com.zs.androidappfw.base.CommonFgmActivity;
import com.zs.androidappfw.wcn.wifi.WifiNanFgm;
import com.zs.androidappfw.wcn.wifi.WifiP2pFgm;
import com.zs.androidappfw.wcn.wifi.WifiRttFgm;
import com.zs.androidappfw.wcn.wifi.WifiSapFgm;
import com.zs.androidappfw.wcn.wifi.WifiStaFgm;

public class WifiFgm extends BaseClickFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fgm_wcn_wifi, container, false);
        initAndSetClickListener(view, new int[] {
                R.id.fg_wcn_wifi_sta,
                R.id.fg_wcn_wifi_sap,
                R.id.fg_wcn_wifi_p2p,
                R.id.fg_wcn_wifi_nan,
                R.id.fg_wcn_wifi_rtt,
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent(mContext, CommonFgmActivity.class);
        if (id == R.id.fg_wcn_wifi_sta) {
            intent.putExtra(BaseFragmentActivity.KEY_FRAGMENT_CLASS, WifiStaFgm.class.getName());
            intent.putExtra(BaseTitleFragment.FRAGMENT_TITLE_RES_ID, R.string.to_wifi_sta);
        } else if (id == R.id.fg_wcn_wifi_sap) {
            intent.putExtra(BaseFragmentActivity.KEY_FRAGMENT_CLASS, WifiSapFgm.class.getName());
            intent.putExtra(BaseTitleFragment.FRAGMENT_TITLE_RES_ID, R.string.to_wifi_sap);
        } else if (id == R.id.fg_wcn_wifi_p2p) {
            intent.putExtra(BaseFragmentActivity.KEY_FRAGMENT_CLASS, WifiP2pFgm.class.getName());
            intent.putExtra(BaseTitleFragment.FRAGMENT_TITLE_RES_ID, R.string.to_wifi_p2p);
        } else if (id == R.id.fg_wcn_wifi_nan) {
            intent.putExtra(BaseFragmentActivity.KEY_FRAGMENT_CLASS, WifiNanFgm.class.getName());
            intent.putExtra(BaseTitleFragment.FRAGMENT_TITLE_RES_ID, R.string.to_wifi_nan);
        } else if (id == R.id.fg_wcn_wifi_rtt) {
            intent.putExtra(BaseFragmentActivity.KEY_FRAGMENT_CLASS, WifiRttFgm.class.getName());
            intent.putExtra(BaseTitleFragment.FRAGMENT_TITLE_RES_ID, R.string.to_wifi_rtt);
        }
        startActivity(intent);
    }
}
