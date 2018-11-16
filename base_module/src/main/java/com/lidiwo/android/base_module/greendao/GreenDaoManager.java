package com.lidiwo.android.base_module.greendao;

import android.text.TextUtils;

import com.lidiwo.android.base_module.base.DefaultApplication;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/10/11 16:03
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class GreenDaoManager {

    private static DaoSession mDaoSession;

    private GreenDaoManager() {
    }

    private static class GreenDaoHolder {
        private final static GreenDaoManager INSTANCE = new GreenDaoManager();
    }

    public static GreenDaoManager getInstance() {
        return GreenDaoHolder.INSTANCE;
    }

    /**
     * 全局初始化一次GreenDao
     */
    public void initGreenDao(String dbname) {
        //第二个参数:为自己需要处理的表，数据库文件存放在应用程序的databases目录下面，
        //如果不保存在应用目录下面， 可以写成/mnt/sdcard/包名/rentings.db的形式，这样即使应用卸载重装后，
        //原来保存的数据依然存在，而且在系统应用设置中清除缓存是无法删除的。

        if(TextUtils.isEmpty(dbname)){
            throw new RuntimeException("Database name is Null");
        }

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(DefaultApplication.getContext(), dbname.concat(".db"), null);
//        DevOpenHelper devOpenHelper=new MyDevOpenHelper(MyApplication.getContext(), "greenDao.db", null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
//        DaoMaster  mDaoMaster = new DaoMaster(devOpenHelper.getEncryptedWritableDb("1236987"));
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            throw new RuntimeException("请先在 Application 中初始化GreenDao");
        }
        return mDaoSession;
    }
}
