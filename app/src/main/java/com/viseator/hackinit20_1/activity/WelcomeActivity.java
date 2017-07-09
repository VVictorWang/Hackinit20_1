package com.viseator.hackinit20_1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.viseator.hackinit20_1.R;

/**
 * Created by viseator on 7/9/17.
 *
 * @author Wu Di
 *         Email: viseator@gmail.com
 */

public class WelcomeActivity extends Activity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
