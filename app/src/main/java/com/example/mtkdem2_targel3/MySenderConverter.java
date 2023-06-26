package com.example.mtkdem2_targel3;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class MySenderConverter {
    @TypeConverter
    public static Sender fromString(String value) {
        // Convert the string representation to a MyProfile object
        Gson gson = new Gson();
        return gson.fromJson(value, Sender.class);
    }

    @TypeConverter
    public static String toString(Sender sender) {
        // Convert the MyProfile object to a string representation
        Gson gson = new Gson();
        return gson.toJson(sender);
    }
}
