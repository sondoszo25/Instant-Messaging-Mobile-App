package com.example.mtkdem2_targel3;

public interface MyProfileCallback {
    void onSuccess(MyProfile myProfile);

    void onFailure(int statusCode);

    void onError(Throwable t);
}
