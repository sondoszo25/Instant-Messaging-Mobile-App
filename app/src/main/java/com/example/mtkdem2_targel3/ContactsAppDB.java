package com.example.mtkdem2_targel3;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Contacts.class}, version = 1)
@TypeConverters({MyProfileConverter.class, MessageConverter.class})
public abstract class ContactsAppDB extends RoomDatabase {
 public  abstract  ContactsDao contactsDao();

}
