package com.example.mtkdem2_targel3;

import android.app.UiModeManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.registerlink);
        textView.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });


        UiModeManager uiModeManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);

        int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        boolean isNightModeEnabled = nightMode == Configuration.UI_MODE_NIGHT_YES;

        if (isNightModeEnabled) {

            findViewById(R.id.mainidback).setBackgroundResource(R.drawable.blackbackgound);
        } else {
            findViewById(R.id.mainidback).setBackgroundResource(R.drawable.backimg);
        }


        FloatingActionButton settingbutton=findViewById(R.id.settingsbtn);
        settingbutton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        });

        Button loginbutton = findViewById(R.id.loginbuttonid);
        loginbutton.setOnClickListener(v -> {
            EditText passwordEditText = findViewById(R.id.passwordloingid);
            String password = passwordEditText.getText().toString();
            EditText ussernameEditText = findViewById(R.id.usernameloginid);
            String username = ussernameEditText.getText().toString();
            forlogin forlogin = new forlogin(username, password);
            ProfileAPI profileAPI = new ProfileAPI();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Registration");
            profileAPI.gologin(forlogin, new RegistrationCallback() {
                @Override
                public void onRegistrationSuccess() {
                    builder.setMessage("Login successful!");
                    // Set the OK button
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Token token = profileAPI.getToken();
                            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                            intent.putExtra("token", token.getToken());
                            intent.putExtra("username", username);
                            startActivity(intent);
                            finish();
                        }
                    });

                    // Create and show the alert dialog
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                @Override
                public void onRegistrationFailure() {
                    builder.setMessage("Login failed.");
                    // Set the OK button
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
        });
    }


}