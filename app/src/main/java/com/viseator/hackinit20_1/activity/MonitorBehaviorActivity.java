package com.viseator.hackinit20_1.activity;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.viseator.hackinit20_1.BaseActivity;
import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.util.ActivityUtil;

public class MonitorBehaviorActivity extends BaseActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_monitor_behavior;
    }

    @Override
    protected void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.behavior_tab);
        mViewPager = (ViewPager) findViewById(R.id.behavior_viewpager);


    }

    @Override
    protected void baseInit() {
        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.finishActivity(MonitorBehaviorActivity.this);
            }
        });

    }
}
