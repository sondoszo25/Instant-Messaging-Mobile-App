package com.example.mtkdem2_targel3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessageDao {
    @Insert
    void insert(List<Message> message);

    @Delete
    void delete(Message... message);

    @Query("SELECT * FROM message")
    List<Message> index();

    @Query("SELECT * FROM message WHERE id=:id")
    Message get(int id);
    @Query("DELETE FROM message")
    void deleteAll();
}
