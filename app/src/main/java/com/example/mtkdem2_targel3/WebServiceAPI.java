package com.example.mtkdem2_targel3;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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


    @POST("Chats")
    Call<foraddchat> AddContacts(@Header("Authorization") String authorizationHeader,@Body Sender sender);

    @DELETE("Chats/{id}")
    Call<Void> DeleteContacts(@Header("Authorization") String authorizationHeader,@Path("id")int id);

    @GET("Chats/{id}/Messages")
    Call<List<Message>> getMessages (@Header("Authorization") String authorizationHeader,@Path("id")int id);
    @POST("Chats/{id}/Messages")
    Call<Void> createMessage(@Header("Authorization") String authorizationHeader,@Path("id")int id,@Body sendMsg msg);
    @POST("firebase")
    Call<Void>  sendtoken(@Body Token token);
}
