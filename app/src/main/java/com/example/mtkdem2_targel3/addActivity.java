package com.example.mtkdem2_targel3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class addActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button cancelButton = findViewById(R.id.cancelButton);
        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        String username = intent.getStringExtra("username");
        cancelButton.setOnClickListener(view ->{
            Intent i = new Intent(this, ChatActivity.class);
            i.putExtra("token", token);
            i.putExtra("username", username);
            startActivity(i);
        });


    }
}