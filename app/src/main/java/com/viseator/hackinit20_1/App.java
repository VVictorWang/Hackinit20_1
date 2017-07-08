package com.viseator.hackinit20_1;

import android.app.Application;


import com.viseator.hackinit20_1.data.DaoMaster;
import com.viseator.hackinit20_1.data.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by viseator on 5/29/17.
 * Wu Di
 * Email: viseator@gmail.com
 */

public class App extends Application {
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "game-db");
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
