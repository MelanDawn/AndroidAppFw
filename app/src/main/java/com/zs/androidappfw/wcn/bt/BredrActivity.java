package com.zs.androidappfw.wcn.bt;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleFragmentActivity;
import com.zs.androidappfw.utils.LUtil;
import com.zs.androidappfw.utils.PermissionUtil;
import com.zs.androidappfw.wcn.utils.BtAdapter;

import java.util.ArrayList;
import java.util.List;

public class BredrActivity extends BaseTitleFragmentActivity {

    private final BluetoothReceiver mReceiver = new BluetoothReceiver();

    private BredrScanFgm bredrScanFgm = new BredrScanFgm();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_wcn);

        final ViewPager2 viewPager = findViewById(R.id.wcn_view_pager);
        final BottomNavigationView navigationView = findViewById(R.id.wcn_navigation_view);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(bredrScanFgm);
        fragmentList.add(new BredrScanFgm());
        TabFragmentStateAdapter adapter = new TabFragmentStateAdapter(this, fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(fragmentList.size() - 1);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                navigationView.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        navigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
//            if (id == R.id.wcn_bottom_tab_wifi) {
                viewPager.setCurrentItem(0);
//            } else if (id == R.id.wcn_bottom_tab_bt) {
//                viewPager.setCurrentItem(1);
//            } else if (id == R.id.wcn_bottom_tab_lbs) {
//                viewPager.setCurrentItem(2);
//            } else if (id == R.id.wcn_bottom_tab_nfc) {
//                viewPager.setCurrentItem(3);
//            } else if (id == R.id.wcn_bottom_tab_uwb) {
//                viewPager.setCurrentItem(4);
//            }
            return true;
        });

        if (PermissionUtil.checkBluetoothPermission(this)) {
            LUtil.i(mTag, "discovery start:", BtAdapter.getInstance(this).startDiscovery());
        } else {
            LUtil.i(mTag, "NO permission");
        }
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_bt_classic;
    }

    @Override
    protected void onStart() {
        super.onStart();

        registerReceiverDelegate(mReceiver, BluetoothReceiver.getIntentFilter());
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(BredrActivity.this, "Has Location Permission", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BredrActivity.this, "No Location Permission", Toast.LENGTH_SHORT).show();
        }
    }

    private static class TabFragmentStateAdapter extends FragmentStateAdapter {
        private final List<Fragment> mList;

        TabFragmentStateAdapter(FragmentActivity activity, List<Fragment> list) {
            super(activity);
            this.mList = list;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return mList.get(position);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}