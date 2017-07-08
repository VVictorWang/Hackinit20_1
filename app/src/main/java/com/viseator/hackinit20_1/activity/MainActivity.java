package com.viseator.hackinit20_1.activity;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.viseator.hackinit20_1.BaseActivity;
import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.adapters.FragmentAdapter;
import com.viseator.hackinit20_1.data.UDPDataPackage;
import com.viseator.hackinit20_1.fragments.TodayDataFragment;
import com.viseator.hackinit20_1.util.ConvertData;
import com.viseator.hackinit20_1.util.network.ComUtil;

public class MainActivity extends BaseActivity {
    public String ipAddress;
    private static final String TAG = "@vir MainActivity";


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case ComUtil.BROADCAST_PORT:
                    UDPDataPackage result = (UDPDataPackage) (ConvertData.byteToObject((byte[]) msg.obj));
                    ipAddress = result.getIpAddress();
                    Log.d(TAG, ipAddress);
                    break;
            }
            return true;
        }

    });
    private ComUtil mComUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void baseInit() {
        mComUtil = new ComUtil(mHandler);
        mComUtil.startReceiveMsg();
        mComUtil.broadCast(ConvertData.objectToByte(new UDPDataPackage(this)));

    }

    @Override
    protected void initView() {

    }
}
