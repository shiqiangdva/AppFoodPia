package com.kevin.appfoodpie.values;

import android.app.Application;
import android.content.Context;

/**
 * Created by kevin on 2016/11/28.
 */
//切记如何使用!!!!!
    //清单文件中加入自己的App
public class MyApp extends Application {
    private static Context mContext;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }
    //对外提供一个获取Context对象的方法
    public static Context getmContext() {
        return mContext;
    }

    public static DaoMaster getDaoMaster(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getmContext(),"Food.db",null);
        daoMaster = new DaoMaster(helper.getWritableDb());
        return daoMaster;
    }

    public static DaoSession getDaoSession(){
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}
