package com.example.mtkdem2_targel3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MyProfileDao {
    @Query("SELECT * FROM myprofile LIMIT 1")
    MyProfile index();

    @Query("SELECT * FROM myprofile WHERE id= :id LIMIT 1")
    MyProfile get(int id);

    @Insert
    void insert(MyProfile myProfile);

    @Update
    void update(MyProfile myProfile);

    @Delete
    void delete(MyProfile myProfile);
}
