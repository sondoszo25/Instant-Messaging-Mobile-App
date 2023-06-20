package com.example.mtkdem2_targel3;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileAPI {
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;
 private int flag=0;
    public ProfileAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void postUsername(MyProfile myProfile, RegistrationCallback callback) {
        Call<MyProfile> call = webServiceAPI.createUser(myProfile);
        call.enqueue(new Callback<MyProfile>() {
            @Override
            public void onResponse(Call<MyProfile> call, Response<MyProfile> response) {
                if (response.isSuccessful()) {
                    MyProfile myProfile = response.body();
                    callback.onRegistrationSuccess();
                } else {
                    int statusCode = response.code();
                    callback.onRegistrationFailure();
                }
            }

            @Override
            public void onFailure(Call<MyProfile> call, Throwable t) {
                System.out.println("Request failed: " + t.getMessage());
                callback.onRegistrationFailure();
            }
        });
    }

}
