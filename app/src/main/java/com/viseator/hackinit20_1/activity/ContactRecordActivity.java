package com.viseator.hackinit20_1.activity;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.viseator.hackinit20_1.BaseActivity;
import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.util.ActivityUtil;

public class ContactRecordActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_contact_record;
    }

    @Override
    protected void baseInit() {
    }

    @Override
    protected void initView() {
        findViewById(R.id.back_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.finishActivity(ContactRecordActivity.this);
            }
        });
    }
}
