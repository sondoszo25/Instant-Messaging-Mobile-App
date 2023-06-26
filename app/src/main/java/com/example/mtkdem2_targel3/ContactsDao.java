package com.example.mtkdem2_targel3;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactsDao {
    @Insert
    void insert(List<Contacts>  contacts);

    @Delete
    void delete(Contacts... contacts);

    @Query("SELECT * FROM contacts")
    List<Contacts> index();

    @Query("SELECT * FROM contacts WHERE id=:id")
    Contacts get(int id);
    @Query("DELETE FROM contacts")
    void deleteAll();
}
