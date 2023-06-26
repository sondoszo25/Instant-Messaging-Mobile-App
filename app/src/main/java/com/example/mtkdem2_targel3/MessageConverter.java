package com.example.mtkdem2_targel3;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class MessageConverter {
    @TypeConverter
    public static Message fromString(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value, Message.class);
    }

    @TypeConverter
    public static String toString(Message message) {
        Gson gson = new Gson();
        return gson.toJson(message);
    }
}
