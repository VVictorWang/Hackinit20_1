package com.viseator.hackinit20_1.activity;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.viseator.hackinit20_1.BaseActivity;
import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.adapters.GamerecordAdapter;
import com.viseator.hackinit20_1.data.GameData;
import com.viseator.hackinit20_1.data.GameDataEntity;
import com.viseator.hackinit20_1.util.ActivityUtil;
import com.viseator.hackinit20_1.util.DateUtils;
import com.viseator.hackinit20_1.view.SideView;

import java.util.ArrayList;
import java.util.List;

public class RemindActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<GameDataEntity> mGameDataEntities = new ArrayList<>();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initEvent();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_remind;
    }

    @Override
    protected void baseInit() {

    }

    @Override
    protected void initView() {
        mGameDataEntities = GameData.getInstance().getDataList();
        mRecyclerView = (RecyclerView) findViewById(R.id.remind_list);
        GamerecordAdapter adapter = new GamerecordAdapter(RemindActivity.this,mGameDataEntities);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RemindActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);

    }

    private void initEvent() {
        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.finishActivity(RemindActivity.this);
            }
        });
    }
}
