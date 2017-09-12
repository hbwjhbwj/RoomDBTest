package contacts.wl.com.roomdbtest.roomdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by shl on 2017/9/12.
 */


@Database(entities = {Medal.class}, version = 3)
public abstract class MedalDatabase extends RoomDatabase {
    public abstract MedalDao medaldao();
}
