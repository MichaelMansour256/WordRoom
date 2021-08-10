package com.example.wordroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.lifecycle.LiveData;

import java.util.List;

@Dao
public interface WordsDao {
    @Insert
    void insert(Words word);
    @Update
    void update(Words word);
    @Delete
    void delete(Words word);
    @Query("Delete From words")
    void deleteAllWords();

    @Query("select * From words")
    LiveData<List<Words>> getAllWords();
}
