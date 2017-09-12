package contacts.wl.com.roomdbtest.roomdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by shl on 2017/9/12.
 */

@Dao
public interface MedalDao {
    @Query("SELECT * FROM medal")
    Flowable<List<Medal>> getAll();

//    @Query("SELECT * FROM medal")
    //Observable<List<Medal>> getAllOB();//Not sure how to convert a Cursor to this method's return type

    @Query("SELECT * FROM medal WHERE uid IN (:ids)")
    List<Medal> loadAllByIds(int[] ids);

    @Query("SELECT * FROM medal WHERE name LIKE :name LIMIT 1")
    Medal findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Medal> medals);

    @Delete
    void delete(Medal medal);

    @Query("DELETE FROM medal")
    void deleteAll();

    @Insert
    void insert(Medal medal);


}
