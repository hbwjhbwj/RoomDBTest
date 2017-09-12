package contacts.wl.com.roomdbtest.roomdb;

import android.arch.persistence.room.Room;

import contacts.wl.com.roomdbtest.MainApplication;

/**
 * Created by shl on 2017/9/12.
 */

public class RoomDB {
    private final static RoomMigration mMigration = new RoomMigration(2, 3);
    private static final RoomDB ourInstance = new RoomDB();
    private MedalDatabase medalDatabase;

    public static RoomDB getInstance() {
        return ourInstance;
    }

    private RoomDB() {
        medalDatabase = Room.databaseBuilder(MainApplication.APP_INSTANCE,
                MedalDatabase.class, "room_medal")
                .addMigrations(mMigration)
                .build();
    }

    public MedalDatabase getMedalDatabase(){
        return medalDatabase;
    }

    public MedalDao getMedalDao() {
        return medalDatabase.medaldao();
    }
}
