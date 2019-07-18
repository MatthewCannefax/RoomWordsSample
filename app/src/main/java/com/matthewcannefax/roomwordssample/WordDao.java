package com.matthewcannefax.roomwordssample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("SELECT * FROM word_table ORDER BY UPPER(word) ASC")
    LiveData<List<Word>> getAllWords();

    @Query("DELETE FROM word_table")
    void deleteAll();
}
