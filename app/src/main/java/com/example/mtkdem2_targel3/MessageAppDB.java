package com.example.mtkdem2_targel3;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Message.class}, version = 1)
@TypeConverters({MySenderConverter.class})
public abstract class MessageAppDB extends RoomDatabase {
    public  abstract  MessageDao messageDao();

}
