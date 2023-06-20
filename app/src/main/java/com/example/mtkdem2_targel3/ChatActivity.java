package com.example.mtkdem2_targel3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Token token = new Token( getIntent().getStringExtra("token"));
        String username=getIntent().getStringExtra("username");
        ProfileAPI profileAPI = new ProfileAPI();
        MyProfile myProfile=profileAPI.getuser(token.getToken(),username);
        TextView me=findViewById(R.id.mename);
        me.setText(myProfile.getDisplayName());
    }
}