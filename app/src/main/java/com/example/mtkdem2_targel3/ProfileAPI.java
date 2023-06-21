package com.example.mtkdem2_targel3;

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
                .baseUrl(MyApp.context.getString(R.string.BaseUrl))
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

}
