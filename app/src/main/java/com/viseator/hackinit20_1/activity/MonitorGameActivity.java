package com.viseator.hackinit20_1.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.viseator.hackinit20_1.BaseActivity;
import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.adapters.FragmentAdapter;
import com.viseator.hackinit20_1.fragments.ThisWeekFragment;
import com.viseator.hackinit20_1.fragments.TodayDataFragment;
import com.viseator.hackinit20_1.util.ActivityUtil;

public class MonitorGameActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mImageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
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
        mImageView = (ImageView) findViewById(R.id.back_image);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.addFragment(new TodayDataFragment(),"今日");
        fragmentAdapter.addFragment(new ThisWeekFragment(), "本周");
        mViewPager.setAdapter(fragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initEvent() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.finishActivity(MonitorGameActivity.this);
            }

        });
        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.finishActivity(MonitorGameActivity.this);
            }
        });
    }
}
