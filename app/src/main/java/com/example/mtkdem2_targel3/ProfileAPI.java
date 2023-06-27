package com.example.mtkdem2_targel3;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
    private Token token;
    private MyProfile myProfile;
    private String tokentest;

    public String getTokentest() {
        return tokentest;
    }

    public Token getToken() {
        return token;
    }





    public MyProfile getMyProfile() {
        return myProfile;
    }

    private int flag=0;
    public ProfileAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ServerSingelton.getInstance().getServer())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }
    public ProfileAPI(Token token) {
        this.token=token;
        retrofit = new Retrofit.Builder()
                .baseUrl(ServerSingelton.getInstance().getServer())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }
    public void postUsername(MyProfile myProfile, RegistrationCallback callback) {
        Call<Void> call = webServiceAPI.createUser(myProfile);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onRegistrationSuccess();
                } else {
                    int statusCode = response.code();
                    callback.onRegistrationFailure();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Request failed: " + t.getMessage());
                callback.onRegistrationFailure();
            }
        });
    }
    public void gologin(forlogin forLogin, RegistrationCallback callback) {
        Call<Token> call = webServiceAPI.getlogin(forLogin);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    token=response.body();
                    callback.onRegistrationSuccess();
                }
                    else {
                        callback.onRegistrationFailure();
                    }
            }
            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                // Handle failure case (e.g., network error)
                callback.onRegistrationFailure();
            }
        });
    }


    public void getuser(Token token, String username, final MyProfileCallback callback) {
        String authorizationHeader = "Bearer " + token.getToken();
        Call<MyProfile> call = webServiceAPI.getMyprofile(authorizationHeader, username);
        call.enqueue(new Callback<MyProfile>() {
            @Override
            public void onResponse(Call<MyProfile> call, Response<MyProfile> response) {
                if (response.isSuccessful()) {
                     myProfile = response.body();
                    callback.onSuccess(myProfile);
                } else {
                    int statusCode = response.code();
                    callback.onFailure(statusCode);
                }
            }

            @Override
            public void onFailure(Call<MyProfile> call, Throwable t) {
                callback.onError(t);
            }
        });
    }



    public void addcontact(Sender addcontact,MutableLiveData<List<Contacts>> contactListData) {
        String authorizationHeader = "Bearer " + token.getToken();
        Call<foraddchat> call = webServiceAPI.AddContacts(authorizationHeader,addcontact);
        call.enqueue(new Callback<foraddchat>() {
            @Override
            public void onResponse(Call<foraddchat> call, Response<foraddchat> response) {
                if (response.isSuccessful()) {
                    getcontacts(contactListData);
                } else {

                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<foraddchat> call, Throwable t) {
            }
        });
    }

    public void getcontacts(MutableLiveData<List<Contacts>> contactListData) {
        String authorizationHeader = "Bearer " + token.getToken();
        Call<List<Contacts>> call = webServiceAPI.getContacts(authorizationHeader);
        call.enqueue(new Callback<List<Contacts>>() {
            @Override
            public void onResponse(Call<List<Contacts>> call, Response<List<Contacts>> response) {
                if (response.isSuccessful()) {
                   // contactListData.setValue(response.body());
                    ContactsRoomSingelton.getInstance().getContactsDao().deleteAll();
                    ContactsRoomSingelton.getInstance().getContactsDao().insert(response.body());
                    contactListData.setValue(ContactsRoomSingelton.getInstance().getContactsDao().index());
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Contacts>> call, Throwable t) {
            }
        });
    }






    public void deletecontact(int id,MutableLiveData<List<Contacts>> contactListData) {
        String authorizationHeader = "Bearer " + token.getToken();
        Call<Void> call = webServiceAPI.DeleteContacts(authorizationHeader,id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getcontacts(contactListData);
                } else {

                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }




    public void getMessages(MutableLiveData<List<Message>> messageListData,int id) {
        String authorizationHeader = "Bearer " + token.getToken();
        Call<List<Message>> call = webServiceAPI.getMessages(authorizationHeader,id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    List<Message> messages = response.body();
                    Collections.reverse(messages);
                  messageListData.setValue(messages);
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
            }
        });
    }


    public void sendMessages(MutableLiveData<List<Message>> messageListData,int id,sendMsg Msg) {
        String authorizationHeader = "Bearer " + token.getToken();
        Call<Void> call = webServiceAPI.createMessage(authorizationHeader,id,Msg);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                        getMessages(messageListData,id);
                } else {

                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}
