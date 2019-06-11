package com.zs.androidappfw.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shoes on 2017/10/25.
 *
 */

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        final ViewPager viewPager = findViewById(R.id.main_view_pager);
        final BottomNavigationView navigationView = findViewById(R.id.main_navigation_view);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new BaseFrgm());
        fragmentList.add(new UiFrgm());
        fragmentList.add(new FunctionFrgm());
        fragmentList.add(new AdvancedFrgm());
        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(fragmentList.size() - 1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_bottom_tab_base:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.main_bottom_tab_ui:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.main_bottom_tab_function:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.main_bottom_tab_advanced:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}

class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;


    TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Fragment getItem(int arg0) {
        return mList.get(arg0);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
