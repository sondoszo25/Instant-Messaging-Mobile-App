package com.example.mtkdem2_targel3;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {MyProfile.class}, version = 1)
public abstract class MyProfileDB extends RoomDatabase{
    public abstract MyProfileDao profileDao();
}
