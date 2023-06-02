package com.zs.androidappfw.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shoes on 2017/10/25.
 *
 */

public class MainActivity extends BaseFragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        final ViewPager2 viewPager = findViewById(R.id.main_view_pager);
        final BottomNavigationView navigationView = findViewById(R.id.main_navigation_view);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new BaseFgm());
        fragmentList.add(new NativeViewFgm());
        fragmentList.add(new AdvancedFgm());
        fragmentList.add(new FunctionFgm());
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(this, fragmentList);
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
            if (id == R.id.main_bottom_tab_base) {
                viewPager.setCurrentItem(0);
            } else if (id == R.id.main_bottom_tab_ui) {
                viewPager.setCurrentItem(1);
            } else if (id == R.id.main_bottom_tab_advanced) {
                viewPager.setCurrentItem(2);
            } else if (id == R.id.main_bottom_tab_function) {
                viewPager.setCurrentItem(3);
            }
            return true;
        });
    }
}

class TabFragmentPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> mList;

    TabFragmentPagerAdapter(FragmentActivity activity, List<Fragment> list) {
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
