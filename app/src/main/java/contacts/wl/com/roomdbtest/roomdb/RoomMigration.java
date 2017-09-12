package contacts.wl.com.roomdbtest.roomdb;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by shl on 2017/9/12.
 */

public class RoomMigration extends Migration {
    /**
     * Creates a new migration between {@code startVersion} and {@code endVersion}.
     *
     * @param startVersion The start version of the database.
     * @param endVersion   The end version of the database after this migration is applied.
     */
    public RoomMigration(int startVersion, int endVersion) {
        super(startVersion, endVersion);
    }

    @Override
    public void migrate(SupportSQLiteDatabase database) {
//        database.execSQL(RENAME_STATEMENT);
//        database.execSQL(CREATION_STATEMENT);
//        database.execSQL(COPY_STATEMENT);
//        database.execSQL(DROP_STATEMENT);

        database.execSQL(RENAME_STATEMENT2);
        database.execSQL(CREATION_STATEMENT2);
        database.execSQL(COPY_STATEMENT2);
        database.execSQL(DROP_STATEMENT2);


        ContentValues medal = new ContentValues();
        medal.put("name", "newOne");
        medal.put("count", 10);
        medal.put("img", "123");
        medal.put("uid", "20000");
        database.insert("medal", SQLiteDatabase.CONFLICT_REPLACE, medal);
    }


    private final String RENAME_STATEMENT =
            "alter table medal rename to medal_old";
    private final String CREATION_STATEMENT =
            "create table if not exists medal ( uid TEXT primary key, name TEXT, img TEXT, count INTEGER not null )";
    private final String COPY_STATEMENT =
            "insert into medal(uid, name, img, count) select uid, name, img, 0 from medal_old";
    private final String DROP_STATEMENT =
            "drop table medal_old";


    private final String RENAME_STATEMENT2 =
            "CREATE TABLE medal_new ( uid TEXT primary key not null, name TEXT not null, img TEXT not null, count INTEGER not null )";
    private final String CREATION_STATEMENT2 =
            "INSERT INTO medal_new (uid, name, img, count) SELECT uid, name, img, 0 FROM medal";
    private final String COPY_STATEMENT2 = "DROP TABLE medal";
    private final String DROP_STATEMENT2 = "ALTER TABLE medal_new RENAME TO medal";
}
