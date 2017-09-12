package contacts.wl.com.roomdbtest.roomdb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by shl on 2017/9/12.
 */

@Entity
public class Medal {
    @PrimaryKey
    @NonNull
    public String uid;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo(name = "img")
    @NonNull
    public String img;

    @ColumnInfo(name = "count")
    @NonNull
    public int count;
}
