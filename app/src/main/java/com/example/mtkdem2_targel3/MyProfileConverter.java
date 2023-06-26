package com.example.mtkdem2_targel3;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class MyProfileConverter {
    @TypeConverter
    public static MyProfile fromString(String value) {
        // Convert the string representation to a MyProfile object
        Gson gson = new Gson();
        return gson.fromJson(value, MyProfile.class);
    }

    @TypeConverter
    public static String toString(MyProfile myProfile) {
        // Convert the MyProfile object to a string representation
        Gson gson = new Gson();
        return gson.toJson(myProfile);
    }
}
