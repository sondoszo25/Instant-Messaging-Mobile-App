package com.example.mtkdem2_targel3;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI
{
@GET("Users/{username}")
Call<MyProfile> getMyprofile(@Header("Authorization") String token, @Path("username") String username);

@POST("Users")
    Call<MyProfile> createUser(@Body MyProfile myProfile);
@POST("Tokens")
Call<Token> getlogin(@Body forlogin forlogin);
}
