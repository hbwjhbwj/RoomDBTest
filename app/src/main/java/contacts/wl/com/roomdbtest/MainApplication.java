package contacts.wl.com.roomdbtest;

import android.app.Application;

import com.facebook.stetho.Stetho;

import contacts.wl.com.roomdbtest.roomdb.RoomDB;

/**
 * Created by shl on 2017/9/12.
 */

public class MainApplication extends Application {

    public static Application APP_INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        APP_INSTANCE = this;
        Stetho.initializeWithDefaults(this);

        //init room
        RoomDB.getInstance();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
//        RoomDB.getInstance().getMedalDatabase().close();
    }
}
