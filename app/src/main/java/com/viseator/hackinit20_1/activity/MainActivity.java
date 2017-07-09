package com.viseator.hackinit20_1.activity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.viseator.hackinit20_1.BaseActivity;
import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.data.DataBean;
import com.viseator.hackinit20_1.data.GameData;
import com.viseator.hackinit20_1.data.GameDataEntity;
import com.viseator.hackinit20_1.fragments.RecordFragment;
import com.viseator.hackinit20_1.util.ActivityUtil;
import com.viseator.hackinit20_1.util.ConvertData;
import com.viseator.hackinit20_1.util.network.ComUtil;
import com.viseator.hackinit20_1.util.network.GetNetworkInfo;
import com.viseator.hackinit20_1.util.network.TcpClient;
import com.viseator.hackinit20_1.util.network.TcpServer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Random;
import java.util.logging.SimpleFormatter;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    public String ipAddress;
    private TcpServer mTcpServer;
    private TcpClient mTcpClient;
    private GameData mGameData;
    private RelativeLayout record, input_text;
    private AnimationDrawable mAnimationDrawable;
    private View toolbar;
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
                    } else {
                        Log.d(TAG, (String) msg.obj);
                        Gson gson = new Gson();
                        DataBean result = gson.fromJson((String) msg.obj, DataBean.class);
                        switch (result.getCode()) {
                            case 1:
                                StringBuilder sb = new StringBuilder();
                                Date date = new Date(result.getTime());
                                sb.append("您的孩子于");
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                                sb.append(simpleDateFormat.format(date));
                                if (result.isIsOpen()) {
                                    sb.append("打开");
                                } else {
                                    sb.append("关闭");
                                }
                                sb.append("了");
                                sb.append(result.getName());
                                sendNotification("孩子动态", sb.toString());
                                saveDataToDataBase(result);
                                break;
                            case 2:

                                showDialog(result.getMessage());
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
            String name = "a" + String.valueOf(i);
            Resources res = getResources();
            int id = res.getIdentifier(name, "drawable", this.getPackageName());
            animationDrawable.addFrame(getDrawable(id), 33);
        }

    }

    private void showDialog(String message) {
        AlertDialog dialog = new AlertDialog.Builder(this).setCustomTitle(null).setMessage
                (message).setPositiveButton("回复", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_record_voice, null);
                Button send = (Button) view.findViewById(R.id.send_voice_btn);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(view);
                final Dialog dialog1 = builder.create();
                dialog1.show();
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText editText = (EditText) (view.findViewById(R.id.edit_text_voice));
                        sendMessage(editText.getText().toString());
                        dialog1.dismiss();
                    }
                });

            }
        }).setNegativeButton("取消", null).create();
        dialog.show();
    }

    private Handler handler = new Handler();

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
        generate();

    }

    private void generate() {
        mGameData.delAll();
        Random random = new Random();
        boolean a1 = false;
        boolean a2 = false;
        boolean a3 = false;
        for (int i = 0; i < 50; i++) {
            int ran1 = random.nextInt(3);
            GameDataEntity gameDataEntity = new GameDataEntity();

            switch (ran1) {
                case 0:
                    gameDataEntity.setName("王者荣耀");
                    a1 = !a1;
                    gameDataEntity.setIsOpen(a1);
                    break;
                case 1:
                    gameDataEntity.setName("阴阳师");
                    a2 = !a2;
                    gameDataEntity.setIsOpen(a2);
                    break;
                case 2:
                    gameDataEntity.setName("qq");
                    a3 = !a3;
                    gameDataEntity.setIsOpen(a3);
                    break;
            }
            int ran2 = random.nextInt(172800000);
            gameDataEntity.setTime(System.currentTimeMillis() - ran2);
            mGameData.addGameData(gameDataEntity.getName(), gameDataEntity.getTime(),
                    gameDataEntity.getIsOpen());
        }
        List<GameDataEntity> list = mGameData.getDataList();

    }

    @Override
    protected void initView() {
        record = (RelativeLayout) findViewById(R.id.monitor_game);
        behavior = (RelativeLayout) findViewById(R.id.monitor_behavior);
        input_text = (RelativeLayout) findViewById(R.id.input_text_layout);
        toolbar = (View) findViewById(R.id.main_toolbar);
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


            }
        });
        input_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_record_voice, null);
//                final Button send = (Button) view.findViewById(R.id.send_voice_btn);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final EditText editText = new EditText(MainActivity.this);
                builder.setView(editText).setPositiveButton("发送", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendMessage(editText.getText().toString());
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

            }
        });
        behavior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(MainActivity.this, MonitorBehaviorActivity.class);
            }
        });
        toolbar.findViewById(R.id.pop_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(MainActivity.this, SettingActivity.class);
            }
        });
        toolbar.findViewById(R.id.pop_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(MainActivity.this, ContactRecordActivity.class);
            }
        });
        toolbar.findViewById(R.id.pop_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(MainActivity.this, RemindActivity.class);
            }
        });

    }

    private void sendMessage(String message) {
        DataBean dataBean = new DataBean();
        dataBean.setCode(4);
        dataBean.setMessage(message);
        Gson gson = new Gson();
        String data = gson.toJson(dataBean, dataBean.getClass());
        mTcpClient.sendRequest(ipAddress, data);
    }

    private void sendNotification(String title, String message) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setContentTitle(title).setContentText(message).setSmallIcon(R.mipmap.starticon);
        Notification notification = builder.build();
        manager.notify(101, notification);
    }


}
