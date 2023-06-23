package com.example.mtkdem2_targel3;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI
{
@GET("Users/{id}")
Call<MyProfile> getMyprofile(@Header("Authorization") String token, @Path("id") String id);

@POST("Users")
    Call<Void> createUser(@Body MyProfile myProfile);
@POST("Tokens")
Call<Token> getlogin(@Body forlogin forlogin);

@GET("Chats")
    Call<List<Contacts>> getContacts(@Header("Authorization") String authorizationHeader);
}


