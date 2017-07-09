package com.viseator.hackinit20_1.activity;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.viseator.hackinit20_1.BaseActivity;
import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.data.DataBean;
import com.viseator.hackinit20_1.data.GameData;
import com.viseator.hackinit20_1.fragments.RecordFragment;
import com.viseator.hackinit20_1.util.ActivityUtil;
import com.viseator.hackinit20_1.util.ConvertData;
import com.viseator.hackinit20_1.util.network.ComUtil;
import com.viseator.hackinit20_1.util.network.GetNetworkInfo;
import com.viseator.hackinit20_1.util.network.TcpClient;
import com.viseator.hackinit20_1.util.network.TcpServer;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    public String ipAddress;
    private TcpServer mTcpServer;
    private TcpClient mTcpClient;
    private GameData mGameData;
    private RelativeLayout record, input_text;
    private AnimationDrawable mAnimationDrawable;
    @BindView(R.id.main_imageview)
    ImageView mImageView;
    private RelativeLayout behavior;
    private ImageView voiceView;
    private static final String TAG = "@vir MainActivity";
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case ComUtil.BROADCAST_PORT:
                    String ip = (String) ConvertData.byteToObject((byte[]) msg.obj);
                    if (!ip.equals(GetNetworkInfo.getIp(getApplicationContext())
                    )) {
                        ipAddress = ip;
                        Log.d(TAG, ipAddress);
                    }
                    TcpInit();
                    break;
                case TcpServer.RECEIVE_REQUEST:
                    if (msg.obj.equals("test")) {
                        Log.d(TAG, "tcp done");
                    }else{
                        Log.d(TAG, (String)msg.obj);
                        Gson gson = new Gson();
                        DataBean result = gson.fromJson((String) msg.obj, DataBean.class);
                        switch (result.getCode()) {
                            case 1:
                                saveDataToDataBase(result);
                                break;
                            case 2:
                                Log.d(TAG, result.getMessage());
                                break;
                        }

                    }

                   break;
            }
            return true;
        }

    });
    private ComUtil mComUtil;

    private void addFrames(AnimationDrawable animationDrawable, int n) {
        for (int i = 1; i < n; i++) {
            String name = "a" +String.valueOf(i);
            Resources res = getResources();
            int id = res.getIdentifier(name, "drawable", this.getPackageName());
            animationDrawable.addFrame(getDrawable(id), 33);
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void baseInit() {
        mComUtil = new ComUtil(mHandler);
        mComUtil.startReceiveMsg();
        mComUtil.broadCast(ConvertData.objectToByte(GetNetworkInfo.getIp(this)));
        mGameData = GameData.getInstance(getGameDataEntityDao());

    }

    @Override
    protected void initView() {
        record = (RelativeLayout) findViewById(R.id.monitor_game);
        behavior = (RelativeLayout) findViewById(R.id.monitor_behavior);
        input_text = (RelativeLayout) findViewById(R.id.input_text_layout);
        voiceView = (ImageView) findViewById(R.id.input_voice);
        mAnimationDrawable = new AnimationDrawable();
        addFrames(mAnimationDrawable, 30);
        mImageView.setImageDrawable(mAnimationDrawable);
        mAnimationDrawable.start();
    }

    private void TcpInit() {
        mTcpServer = new TcpServer();
        mTcpServer.startServer(mHandler);
        mTcpClient = new TcpClient();
        mTcpClient.sendRequest(ipAddress, "test");
    }

    private void saveDataToDataBase(DataBean dataBean) {
        mGameData.addGameData(dataBean.getName(), dataBean.getTime(), dataBean.isIsOpen());
    }
    private void initEvent() {
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(MainActivity.this, MonitorGameActivity.class);
            }
        });
        voiceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                RecordFragment recordFragment = new RecordFragment();
                transaction.replace(R.id.relative_layout, recordFragment);

                transaction.commit();

            }
        });
        input_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                RecordFragment recordFragment = new RecordFragment();
                transaction.replace(R.id.relative_layout, recordFragment);
                transaction.commit();
            }
        });
        behavior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(MainActivity.this, MonitorBehaviorActivity.class);
            }
        });
    }

}
