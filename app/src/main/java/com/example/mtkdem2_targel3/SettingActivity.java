package com.example.mtkdem2_targel3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Button applychange=findViewById(R.id.changeSet);
        applychange.setOnClickListener(v -> {
            EditText serverEditText = findViewById(R.id.serverAddress);
          String  server = serverEditText.getText().toString();
          if(!server.equals(""))
          {
              ServerSingelton.getInstance().setServer(server);
          }
            ToggleButton toggleButton = findViewById(R.id.toggleButton);
            boolean isToggleOn = toggleButton.isChecked();

            if (isToggleOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}