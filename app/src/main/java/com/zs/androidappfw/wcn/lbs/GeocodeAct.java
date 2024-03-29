package com.zs.androidappfw.wcn.lbs;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;
import com.zs.androidappfw.utils.LUtil;
import com.zs.androidappfw.wcn.utils.LocationUtils;

import java.io.IOException;
import java.util.List;

public class GeocodeAct extends BaseTitleActivity {

    private EditText mLatEt, mLngEt, mLocationNameEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_wcn_lbs_geocoder);

        mLatEt = findViewById(R.id.geocode_lat);
        mLngEt = findViewById(R.id.geocode_lng);
        mLocationNameEt = findViewById(R.id.geocode_location_name);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_feature_geocoder;
    }

    @Override
    protected void onStart() {
        super.onStart();
        printSimpleMethod();
    }

    public void handleGeocoding(View view) {
        String locationName = mLocationNameEt.getText().toString().trim();
        if (TextUtils.isEmpty(locationName)) {
            Toast.makeText(this, "请输入地点名称", Toast.LENGTH_SHORT).show();
            LUtil.i(mTag, "invalid locationName:", locationName);
            return;
        }
        printGetFromLocationName(locationName);
    }


    public void handleReverseGeocoding(View view) {
        String latStr = mLatEt.getText().toString().trim();
        String lngStr = mLngEt.getText().toString().trim();
        if (TextUtils.isEmpty(latStr) || TextUtils.isEmpty(lngStr)) {
            Toast.makeText(this, "请输入经纬度", Toast.LENGTH_SHORT).show();
            LUtil.i(mTag, "invalid lat:", latStr, "or lng:", lngStr);
            return;
        }

        int lat = parseInteger(latStr);
        int lng = parseInteger(lngStr);

        printGetFromLocation(lat, lng);
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // nothing to do
        }
        return Integer.MAX_VALUE;
    }

    private void printGetFromLocationName(final String locationName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Geocoder geocoder = new Geocoder(GeocodeAct.this);
                    // 阻塞方法，应在非UI线程执行
                    List<Address> list = geocoder.getFromLocationName(locationName, 5);
                    // 可以设置经纬度矩形范围，在指定范围内搜寻指定的地点
                    //List<Address> list = geocoder.getFromLocationName(locationName, 5, 10, 100, 50, 160);
                    if (list != null && !list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            LUtil.d(mTag, "address", i, ":", LocationUtils.getContent(list.get(i)));
                        }
                    } else {
                        LUtil.w(mTag, "printGetFromLocationName result is empty:", list);
                    }
                } catch (IOException e) {
                    LUtil.e(mTag, "printGetFromLocationName", e);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void printGetFromLocation(final int lat, final int lng) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Geocoder geocoder = new Geocoder(GeocodeAct.this);
                    // 阻塞方法，应在非UI线程执行
                    List<Address> list = geocoder.getFromLocation(lat, lng, 5);
                    if (list != null && !list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            LUtil.d(mTag, "address", i, ":", LocationUtils.getContent(list.get(i)));
                        }
                    } else {
                        LUtil.w(mTag, "printGetFromLocation result is empty:", list);
                    }
                } catch (IOException e) {
                    LUtil.e(mTag, "printGetFromLocation", e);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void printSimpleMethod() {
        // 判断 Geocoder 服务是否可用，接口实现与否、网络状态
        LUtil.i(mTag, "Geocoder available:", Geocoder.isPresent());
    }
}
