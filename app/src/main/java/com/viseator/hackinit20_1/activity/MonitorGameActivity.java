package com.viseator.hackinit20_1.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.viseator.hackinit20_1.BaseActivity;
import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.adapters.FragmentAdapter;
import com.viseator.hackinit20_1.fragments.ThisWeekFragment;
import com.viseator.hackinit20_1.fragments.TodayDataFragment;

public class MonitorGameActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_monitor_game;
    }

    @Override
    protected void baseInit() {

    }

    @Override
    protected void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.monitor_tab);
        mViewPager = (ViewPager) findViewById(R.id.monitor_viewpager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new TodayDataFragment(),"今日");
        fragmentAdapter.addFragment(new ThisWeekFragment(), "本周");
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
